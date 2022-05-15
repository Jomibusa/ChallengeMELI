package com.jomibusa.challengemeli.interfaces

import com.jomibusa.challengemeli.data.model.Item
import com.jomibusa.challengemeli.util.EndPointList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Jomibusa
 */

interface IApiService {

    /**
     * Método que se va a encargar de realizar el consumo junto a Retrofit y retornará un modelo de
     * tipo 'Item' del producto a consultar
     * @param nameItem Nombre del item a buscar y ser pasado al endpoint para su respectivo consumo
     */
    @GET(EndPointList.GET_ITEMS)
    fun getItems(@Query("q") nameItem: String): Call<Item>

    companion object {

        /**
         * @param BASE_URL Contiene la url base donde se realizan los consumos de cada endpoint
         */
        var BASE_URL = "https://api.mercadolibre.com/"

        /**
         * Función que retorna la construcción de Retrofit que nos permitirá realizar los consumos
         */
        fun create(): IApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(IApiService::class.java)
        }
    }

}