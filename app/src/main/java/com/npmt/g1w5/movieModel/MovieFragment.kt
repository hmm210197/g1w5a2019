package com.npmt.g1w5.movieModel

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.google.gson.Gson
import com.npmt.group1week5.jsonModel.General
import com.npmt.group1week5.movieModel.Movie
import com.npmt.group1week5.movieModel.MovieAdapter
import kotlinx.android.synthetic.main.activity_movie_list.*
import okhttp3.*
import java.io.IOException

class MovieFragment:Fragment(){
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //  get information from saved instance state from MovieListActivity
        val list = savedInstanceState?.getString("choose")
        val url = when(list){
            "NOWPLAYING"->"https://api.themoviedb.org/3/movie/now_playing?api_key=7519cb3f829ecd53bd9b7007076dbe23"
            "TOPRATTE"->"https://api.themoviedb.org/3/movie/top_rate?api_key=7519cb3f829ecd53bd9b7007076dbe23"
            else -> "Unknow"
        }

        //  get json from api
        lateinit var jsonString : String
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("load fail",e.stackTrace.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful){
                    //  take json string from api when connection success. otherwise take it from local file in assetts
                    jsonString = response.body()?.string() ?: context!!.assets.open("movies.json").bufferedReader().use { it.readText() }
                }
            }
        })

        // parse json to class
        var movies = Gson().fromJson(jsonString, General::class.java)
        var movie = ArrayList<Movie>()
        movies.results.forEach{ movie?.add(Movie(it.overview,it.poster_path,it.release_date,it.title,it.video,it.vote_average)) }

        //  set adapter
        val adapter = MovieAdapter(movie)
        rcMovies.adapter = adapter
    }
}