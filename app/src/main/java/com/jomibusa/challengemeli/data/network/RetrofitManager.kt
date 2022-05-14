package com.jomibusa.challengemeli.data.network

import android.util.Log
import com.jomibusa.challengemeli.data.model.Item
import com.jomibusa.challengemeli.interfaces.IApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitManager {

    private val TAG = RetrofitManager::class.java.simpleName

    fun getListItems(nameItem: String, listener: (Item?) -> Unit) {
        val apiService = IApiService.create().getItems(nameItem)
        apiService.enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>?, response: Response<Item>?) {
                if (response?.body() != null) {
                    listener(response.body())
                    Log.d(TAG, "onResponse ${response.body()}")
                } else {
                    Log.d(TAG, "onResponse body is NULL")
                    listener(null)
                }
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                listener(null)
                Log.d(TAG, "onFailure ${t.message}")
            }
        })
    }

}