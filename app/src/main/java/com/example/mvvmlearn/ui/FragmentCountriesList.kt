package com.example.mvvmlearn.ui

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmlearn.R
import com.example.mvvmlearn.data.model.CountryModel
import com.example.mvvmlearn.data.repo.CountryRepo

import com.example.mvvmlearn.databinding.FragmentCountriesListBinding
import com.example.mvvmlearn.util.SVGUtil
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentCountriesList : Fragment(R.layout.fragment_countries_list), CountryListAdapter.CountryClickListener {

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
        val view = binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val action = FragmentCountriesListDirections.actionFragmentCountriesListToFragmentDetails()
        binding.buttonGo.setOnClickListener {
            findNavController().navigate(action)
        }
        initViews()
    }

    override fun onClick(country: CountryModel) {
        Log.e("SAHAK", "" + country.name)
        //click open action
    }

    private fun initViews() {
        mAdapter = CountryListAdapter(requireContext(), ArrayList())
        mAdapter?.setListener(this)
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
                        Log.e("SAHAK", "" + mCountryViewModel.errorMessage.value)
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



