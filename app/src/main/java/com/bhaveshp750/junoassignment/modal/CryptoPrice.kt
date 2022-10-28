package com.bhaveshp750.junoassignment.modal

import com.google.gson.annotations.SerializedName

data class CryptoPrice(
    @SerializedName("current_price_in_usd")
    val currentPriceInUSD: String,
    val logo: String,
    val title: String
)