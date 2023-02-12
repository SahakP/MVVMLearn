package com.example.mvvmlearn.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvvmlearn.R
import com.example.mvvmlearn.data.model.CountryModel
import com.example.mvvmlearn.databinding.FragmentDetailsBinding
import com.example.mvvmlearn.util.SVGUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetails : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var selectCountry: CountryModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        InitViews()
    }

    fun InitViews() {
        selectCountry = FragmentDetailsArgs.fromBundle(requireArguments()).selectedCountry
        binding.name.text = selectCountry.name
        binding.capital.text = selectCountry.capital
        binding.region.text = selectCountry.region
        if (selectCountry.flag != null) {
            SVGUtil.fetchSvg(requireContext(), selectCountry.flag!!, binding.flag)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}