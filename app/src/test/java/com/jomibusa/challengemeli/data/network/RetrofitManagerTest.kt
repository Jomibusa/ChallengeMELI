package com.jomibusa.challengemeli.data.network

import com.jomibusa.challengemeli.interfaces.IApiService
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RetrofitManagerTest {

    @Test
    fun doConsumeWithRetrofitAndGetTheModel() {
        val callResponse = IApiService.create().getItems("computadora")

        try {
            val response = callResponse.execute()
            val item = response.body()

            assertThat(response.isSuccessful && item != null, CoreMatchers.equalTo(true))
            assertThat(item?.results?.isNotEmpty(), CoreMatchers.equalTo(true))

        } catch (e: Exception) {
            println("Error $e")
        }
    }

    @Test
    fun doConsumeWithNotValidWordWithRetrofitAndGetEmptyResult() {
        val callResponse = IApiService.create().getItems("asjfsdk12vbsdv12")

        try {
            val response = callResponse.execute()
            val item = response.body()

            assertThat(response.isSuccessful && item != null, CoreMatchers.equalTo(true))
            assertThat(item?.results?.isEmpty(), CoreMatchers.equalTo(true))

        } catch (e: Exception) {
            println("Error $e")
        }
    }

}