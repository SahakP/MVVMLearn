package com.example.mvvmlearn.data.repo

import com.example.mvvmlearn.data.api.CountryApi
import javax.inject.Inject


class CountryRepo @Inject constructor(private val countryApi: CountryApi) {
    fun getAll() = countryApi.getCountry()
}