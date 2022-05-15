package com.jomibusa.challengemeli.data.network

import android.util.Log
import com.jomibusa.challengemeli.data.model.Item
import com.jomibusa.challengemeli.interfaces.IApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Jomibusa
 */

class RetrofitManager {

    private val TAG = RetrofitManager::class.java.simpleName

    private var apiService: Call<Item>? = null

    interface IOnDetailFetched {
        fun onSuccess(item: Item)
        fun onFailure()
        fun noResults()
    }

    /**
     * Función utilizada para realizar el consumo al API de mercado libre por medio de Retrofit,
     * donde se busca obtener un modelo que contiene los resultados del item a buscar
     * @param nameItem Es el item a buscar en formato de String, el cual es utilizado para realizar
     * el consumo
     * @param listener Es la interfaz utilizada para dar respeusta al presenter de lo sucedido al
     * momento de realizar el consumo
     */
    fun getListItems(nameItem: String, listener: IOnDetailFetched) {
        apiService = IApiService.create().getItems(nameItem)
        apiService?.enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>?, response: Response<Item>?) {
                response?.let {
                    if (it.isSuccessful) {
                        if (it.body() != null) {
                            val item = response.body()
                            processData(item, listener)
                        } else {
                            Log.d(TAG, "onResponse body is NULL")
                            listener.onFailure()
                        }
                    } else {
                        Log.d(TAG, "onResponse is not Successful")
                        listener.onFailure()
                    }
                }
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                listener.onFailure()
                Log.d(TAG, "onFailure ${t.message}")
            }
        })
    }

    /**
     * Función para procesar la data obtenida del consumo, para de esta forma saber si el resultado
     * aún siendo exitoso tuvo resultados o por el contrario no se encontraron resultados
     * @param item es el modelo del resultado obtenido del consumo
     * @param listener es la interfaz utilizada para dar respuesta a la data procesada
     */
    private fun processData(item: Item?, listener: IOnDetailFetched) {
        item?.let {
            if (it.results.isNotEmpty()) {
                Log.d(TAG, "processData -> onSuccess")
                listener.onSuccess(it)
            } else {
                Log.d(TAG, "processData -> noResults")
                listener.noResults()
            }
        }
        Log.d(TAG, "onResponse $item")
    }

    /**
     * Con esta función se busca cancelar la petición que se está ejecutando en ese momento
     */
    fun cancelRequest() {
        apiService?.cancel()
    }

}