package com.apps.fullandroidcourseclassa.breakingbadapi.service

import com.apps.fullandroidcourseclassa.breakingbadapi.model.BBCharacter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BreakingBadService {

    @GET("characters")
    suspend fun getCharacters():List<BBCharacter>
}
object BreakingBadNetwork{
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.breakingbadapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(BreakingBadService::class.java)
}