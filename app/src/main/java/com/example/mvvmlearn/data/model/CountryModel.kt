package com.example.mvvmlearn.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CountryModel(
    val name: String?,
    val flag: String?,
    val region: String?,
    val capital: String?
    ): Parcelable
