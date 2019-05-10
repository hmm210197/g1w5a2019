package com.npmt.group1week5.jsonModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class General(
    val dates : Dates,
    val page : Int,
    val results : List<Results>,
    val total_pages : Int,
    val total_results : Int
):Parcelable