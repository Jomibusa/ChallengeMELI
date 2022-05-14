package com.jomibusa.challengemeli.data.network

import android.util.Log
import com.jomibusa.challengemeli.data.model.Item
import com.jomibusa.challengemeli.interfaces.IApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitManager {

    private val TAG = RetrofitManager::class.java.simpleName

    fun getListItems(nameItem: String, listener: (List<Item>?) -> Unit) {
        val IApiService = IApiService.create().getItems(nameItem)
        IApiService.enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>?, response: Response<List<Item>>?) {
                if (response?.body() != null) {
                    listener(response.body())
                    Log.d(TAG, "onResponse ${response.body()}")
                } else {
                    Log.d(TAG, "onResponse body is NULL")
                    listener(null)
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                listener(null)
                Log.d(TAG, "onFailure ${t.message}")
            }
        })
    }

}