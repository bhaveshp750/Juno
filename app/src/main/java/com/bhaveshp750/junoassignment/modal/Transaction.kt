package com.bhaveshp750.junoassignment.modal

import com.google.gson.annotations.SerializedName

data class Transaction(
    val title: String,
    @SerializedName("txn_amount")
    val txnAmount: String,
    @SerializedName("txn_logo")
    val txnLogo: String,
    @SerializedName("txn_sub_amount")
    val txnSubAmount: String,
    @SerializedName("txn_time")
    val txnTime: String
)