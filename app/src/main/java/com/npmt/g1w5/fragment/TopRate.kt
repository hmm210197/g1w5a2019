package com.npmt.g1w5.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.npmt.g1w5.R
import com.npmt.g1w5.getJson.GetJSON
import com.npmt.group1week5.movieModel.MovieAdapter
import kotlinx.android.synthetic.main.fragment.*

class TopRate : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment, container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val url = "https://api.themoviedb.org/3/movie/top_rate?api_key=7519cb3f829ecd53bd9b7007076dbe23"
        val movieArrayList = GetJSON(this.context,url).getJsonFromApi()

        Log.i("acs",movieArrayList.size.toString())
        //  set adapter
        rcMovies.layoutManager = LinearLayoutManager(activity)
        val adapter = MovieAdapter(movieArrayList)
        rcMovies.adapter = adapter

    }
}