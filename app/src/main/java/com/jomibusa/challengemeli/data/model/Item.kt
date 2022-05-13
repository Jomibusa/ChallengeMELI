package com.jomibusa.challengemeli.data.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("results") val results: List<Results>
)

data class Results(
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("available_quantity") val quantity: Int,
    @SerializedName("thumbnail") val imageItem: String,
    @SerializedName("attributes") val attributes: List<Attributes>,
    @SerializedName("accepts_mercadopago") val mercadoPago: Boolean
)

data class Attributes(
    @SerializedName("name") val name: String,
    @SerializedName("value_name") val valueName: String,
)
