package com.example.mvvmlearn.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmlearn.R
import com.example.mvvmlearn.data.model.CountryModel
import com.example.mvvmlearn.databinding.FragmentCountriesListBinding
import com.example.mvvmlearn.ui.adapter.CountryListAdapter
import com.example.mvvmlearn.ui.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentCountriesList : Fragment(R.layout.fragment_countries_list),
    CountryListAdapter.CountryClickListener {

    private var _binding: FragmentCountriesListBinding? = null
    private val binding get() = _binding!!
    private var mAdapter: CountryListAdapter? = null
    private val mCountryViewModel by viewModels<CountryViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountriesListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onClick(country: CountryModel) {
        Log.e("SAHAK", "" + country.name)
    }

    private fun selectCountry(country: CountryModel) {
        val action =
            FragmentCountriesListDirections.actionFragmentCountriesListToFragmentDetails(country)
        binding.body.findNavController().navigate(action)
    }

    private fun initViews() {
        binding.updateBtn.setOnClickListener {
            mCountryViewModel.getAllCountries()
        }

        mAdapter = CountryListAdapter(requireContext(), ArrayList())
        mAdapter?.setListener { selectCountry -> selectCountry(selectCountry) }
        binding.countryRecyclerview.adapter = mAdapter
        binding.countryRecyclerview.layoutManager = LinearLayoutManager(activity)


        mCountryViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            isLoading.let {
                binding.progress.visibility = if (isLoading) View.VISIBLE else View.GONE
                binding.body.visibility = if (!isLoading) View.VISIBLE else View.GONE
                if (!isLoading) {
                    if (mCountryViewModel.countryList.isNotEmpty()) {
                        mAdapter?.setItems(mCountryViewModel.countryList)
                        mAdapter?.notifyDataSetChanged()
                    } else {
                       // Log.e("SAHAK", "" + mCountryViewModel.errorMessage.value)
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}



