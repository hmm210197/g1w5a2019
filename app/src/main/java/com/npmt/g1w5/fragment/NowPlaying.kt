package com.npmt.g1w5.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.npmt.g1w5.R
import com.npmt.g1w5.getJson.GetJSON
import com.npmt.group1week5.movieModel.MovieAdapter
import kotlinx.android.synthetic.main.fragment.*

class NowPlaying : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val url = "https://api.themoviedb.org/3/movie/now_playing?api_key=7519cb3f829ecd53bd9b7007076dbe23"
        val movieArrayList = GetJSON(this.context,url).getJsonFromApi()

        //  set adapter
        val adapter = MovieAdapter(movieArrayList)
        rcMovies.adapter = adapter

        return inflater.inflate(R.layout.fragment, container,false)
    }
}