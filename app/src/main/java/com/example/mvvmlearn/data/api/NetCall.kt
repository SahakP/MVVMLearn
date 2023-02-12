package com.example.mvvmlearn.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetCall {
    val BASE_URL = "https://restcountries.com/v2/"

    var retrofitService : CountryApi?= null


    @Singleton
    @Provides
    fun getInstance(): CountryApi {
        if(retrofitService==null){
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofitService = retrofit.create(CountryApi::class.java)
        }
            return retrofitService!!
    }
}