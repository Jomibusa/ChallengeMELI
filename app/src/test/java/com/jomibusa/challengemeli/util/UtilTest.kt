package com.jomibusa.challengemeli.util

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UtilTest {

    @Test
    fun validateReplaceUrl() {
        val urlToChange = "http://http2.mlstatic.com/D_796537-MLA31003306196_062019-I.jpg"
        val expectedURl = "https://http2.mlstatic.com/D_796537-MLA31003306196_062019-I.jpg"
        assertEquals(expectedURl, Util.replaceUrl(urlToChange))
    }

    @Test
    fun validateParseToCurrencyFormat() {
        val price = 499900.0
        val expectedPrice = "499.900"
        assertEquals(expectedPrice, Util.parseToCurrencyFormat(price))
    }

}