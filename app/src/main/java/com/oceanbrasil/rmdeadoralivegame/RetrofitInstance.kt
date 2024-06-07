package com.oceanbrasil.rmdeadoralivegame

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//https://rickandmortyapi.com/api/character/500
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: RickAndMortyApi by lazy {
        retrofit.create(RickAndMortyApi::class.java)
    }

}