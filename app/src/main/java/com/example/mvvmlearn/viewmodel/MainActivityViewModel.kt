package com.example.mvvmlearn.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmlearn.data.model.CountryModel


class MainActivityViewModel: ViewModel() {

    lateinit var liveDataList: MutableLiveData<List<CountryModel>?>
    init {
    liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>?> {
        return liveDataList
    }
    /*fun makeAPICall(){
       val retroInstance =  RetroInstance.getRetroInstance()
        val retorService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retorService.getCountryList()
        call.enqueue(object : Callback<List<CountryModel>>{
            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
               liveDataList.postValue(null)
            }

        })

    }*/
}