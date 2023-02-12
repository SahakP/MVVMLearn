package com.example.mvvmlearn.data.api

import com.example.mvvmlearn.util.Config
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
    var retrofitService: CountryApi? = null

    @Singleton
    @Provides
    fun getInstance(): CountryApi {
        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofitService = retrofit.create(CountryApi::class.java)
        }
        return retrofitService!!
    }
}