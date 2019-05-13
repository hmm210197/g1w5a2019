package com.npmt.g1w5.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.npmt.g1w5.R
import com.npmt.g1w5.getJson.GetJSON
import kotlinx.android.synthetic.main.fragment.*

class TopRate : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment, container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val url = "https://api.themoviedb.org/3/movie/top_rated?api_key=7519cb3f829ecd53bd9b7007076dbe23"
        GetJSON().getJsonFromApi(url,rcMovies,this.activity,this.context)
    }
}