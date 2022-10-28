package com.bhaveshp750.junoassignment.modal

import com.google.gson.annotations.SerializedName

data class YourCryptoHolding(
    @SerializedName("current_bal_in_token")
    val currentBalanceInToken: String,
    @SerializedName("current_bal_in_usd")
    val currentBalanceInUSD: String,
    val logo: String,
    val title: String
)