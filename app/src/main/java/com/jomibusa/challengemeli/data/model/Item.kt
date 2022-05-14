package com.jomibusa.challengemeli.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Item(
    @SerializedName("results") val results: List<Results>
)

@Parcelize
data class Results(
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("available_quantity") val quantity: Int,
    @SerializedName("thumbnail") val imageItem: String,
    @SerializedName("attributes") val attributes: List<Attributes>?,
    @SerializedName("accepts_mercadopago") val mercadoPago: Boolean
) : Parcelable

@Parcelize
data class Attributes(
    @SerializedName("name") val name: String?,
    @SerializedName("value_name") val valueName: String?,
) : Parcelable
