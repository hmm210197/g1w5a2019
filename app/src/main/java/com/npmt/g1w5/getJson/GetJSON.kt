package com.npmt.g1w5.getJson

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.npmt.group1week5.jsonModel.General
import com.npmt.group1week5.movieModel.Movie
import okhttp3.*
import java.io.IOException

class GetJSON(val context: Context?, val url : String) {

   fun getJsonFromApi():ArrayList<Movie>{

       //   get json from url
       Log.i("url: ",url)
       var jsonString : String? = null
       val client = OkHttpClient()
       val request = Request.Builder().url(url).build()
       client.newCall(request).enqueue(object: Callback {
           override fun onFailure(call: Call, e: IOException) {
               Log.i("load fail",e.stackTrace.toString())
           }
           override fun onResponse(call: Call, response: Response) {
               Log.i("response","")
               if(response.isSuccessful){
                   //  take json string from api when connection success. otherwise take it from local file in assetts
                   jsonString = response.body()!!.string()
               }
           }
       })
       if(jsonString == null){
           Log.i("Null json string","Null!!!!!")
           jsonString = context?.assets?.open("movies.json")?.bufferedReader().use{it?.readText()}
       }

       // parse json to class
       val movies = Gson().fromJson(jsonString, General::class.java)
       var movie = ArrayList<Movie>()
       movies.results.forEach{ movie?.add(Movie(it.overview,it.poster_path,it.release_date,it.title,it.video,it.vote_average)) }

       return movie
   }

}