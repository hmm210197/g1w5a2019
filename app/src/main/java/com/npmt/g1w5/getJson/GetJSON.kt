package com.npmt.g1w5.getJson

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import com.npmt.group1week5.jsonModel.General
import com.npmt.group1week5.movieModel.Movie
import com.npmt.group1week5.movieModel.MovieAdapter
import okhttp3.*
import java.io.IOException

class GetJSON{

   fun getJsonFromApi(url : String,recycler : RecyclerView, activity : FragmentActivity?,context : Context?){

       var movie : ArrayList<Movie> = ArrayList()

       //   get json from url
       lateinit var jsonString : String
       val client = OkHttpClient()
       val request = Request.Builder().url(url).build()
       client.newCall(request).enqueue(object: Callback {
           override fun onFailure(call: Call, e: IOException) {
               Log.i("load fail",e.stackTrace.toString())
           }
           override fun onResponse(call: Call, response: Response) {
               if(response.isSuccessful){
                   jsonString = response.body()!!.string()
                   val movies = Gson().fromJson(jsonString, General::class.java)
                   movies.results.forEach{ movie.add(Movie(it.overview,it.poster_path,it.release_date,it.title,it.video,it.vote_average)) }

                   activity?.runOnUiThread(
                       Runnable {
                           recycler.layoutManager = LinearLayoutManager(activity)
                           val adapter = MovieAdapter(movie)
                           recycler.adapter = adapter
                       }
                   )
               }else{
                   jsonString = context?.assets?.open("movies.json")?.bufferedReader().use{it!!.readText()}
                   throw IOException("Unexpected code " + response)
               }
           }
       })
   }

}