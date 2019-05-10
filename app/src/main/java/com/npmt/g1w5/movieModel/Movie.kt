package com.npmt.group1week5.movieModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val overview : String,
    val poster_path : String,
    val release_date : String,
    val title : String,
    val video : Boolean,
    val vote_average : Float
): Parcelable