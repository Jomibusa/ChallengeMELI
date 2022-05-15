package com.jomibusa.challengemeli.data.network

import android.util.Log
import com.jomibusa.challengemeli.data.model.Item
import com.jomibusa.challengemeli.interfaces.IApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitManager {

    private val TAG = RetrofitManager::class.java.simpleName

    interface IOnDetailFetched {
        fun onSuccess(item: Item)
        fun onFailure()
        fun noResults()
    }

    fun getListItems(nameItem: String, listener: IOnDetailFetched) {
        val apiService = IApiService.create().getItems(nameItem)
        apiService.enqueue(object : Callback<Item> {
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

    private fun processData(item: Item?, listener: IOnDetailFetched) {
        item?.let {
            if (it.results.isNotEmpty()) {
                listener.onSuccess(it)
            } else {
                listener.noResults()
            }
        }
        Log.d(TAG, "onResponse $item")
    }

}