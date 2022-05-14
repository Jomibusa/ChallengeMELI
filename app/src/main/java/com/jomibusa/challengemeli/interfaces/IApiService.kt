package com.jomibusa.challengemeli.interfaces

import com.jomibusa.challengemeli.data.model.Item
import com.jomibusa.challengemeli.util.EndPointList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {

    @GET(EndPointList.GET_ITEMS)
    fun getItems(@Query("q") nameItem: String): Call<List<Item>>

    companion object {

        var BASE_URL = "https://api.mercadolibre.com/"

        fun create(): IApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(IApiService::class.java)
        }
    }

}