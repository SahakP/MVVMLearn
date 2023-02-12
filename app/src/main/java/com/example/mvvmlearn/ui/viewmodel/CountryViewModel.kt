package com.example.mvvmlearn.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmlearn.data.model.CountryModel
import com.example.mvvmlearn.data.repo.CountryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val countryRepo: CountryRepo
) : ViewModel() {

    companion object {
        const val TAG = "CountryViewModel"
    }

    val countryList = ArrayList<CountryModel>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = true
        getAllCountries()
    }

    fun getAllCountries() {
        val call = countryRepo.getAll()

        call.enqueue(object : Callback<ArrayList<CountryModel>> {

            override fun onResponse(
                call: Call<ArrayList<CountryModel>>,
                response: Response<ArrayList<CountryModel>>
            ) {
                val lis = response.body() as ArrayList<CountryModel>
                countryList.clear()
                countryList.addAll(lis)
                isLoading.value = false
            }

            override fun onFailure(call: Call<ArrayList<CountryModel>>, t: Throwable) {
                errorMessage.value = t.message
                countryList.clear()
                isLoading.value = false
               // Log.e(TAG, "error", t)
            }

        })


    }
}