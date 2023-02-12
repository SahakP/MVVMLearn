package com.example.mvvmlearn.data.api

import com.example.mvvmlearn.data.model.CountryModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET("all")
    fun getCountry(): Call<ArrayList<CountryModel>>
}