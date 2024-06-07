package com.oceanbrasil.rmdeadoralivegame

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int): Call<Character>

}