package com.jomibusa.challengemeli.util

import java.text.DecimalFormat
import java.text.NumberFormat

object Util {

    fun replaceUrl(url: String): String {
        return url.replaceFirst("http", "https")
    }

    fun parseToCurrencyFormat(price: Double): String {
        val formatter: NumberFormat = DecimalFormat("#,###")
        return formatter.format(price)
    }

}