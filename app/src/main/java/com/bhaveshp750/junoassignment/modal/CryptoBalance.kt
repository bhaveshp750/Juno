package com.bhaveshp750.junoassignment.modal

import com.google.gson.annotations.SerializedName

data class CryptoBalance(
    @SerializedName("current_bal_in_usd")
    val currentBalanceInUSD: String,
    val subtitle: String,
    val title: String
)