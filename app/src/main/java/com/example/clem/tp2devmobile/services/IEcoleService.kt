package com.example.clem.tp2devmobile.services

import com.example.clem.tp2devmobile.models.EcolePrimaire
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Clem on 02/02/2018.
 */

interface IEcoleService {

    @GET("/getEcolePrimaire")
    fun getEcolesPrimaire(): Call<List<EcolePrimaire>>

}