package com.bhaveshp750.junoassignment.modal

import com.google.gson.annotations.SerializedName

data class HomeDto(
    @SerializedName("all_transactions")
    val allTransactions: List<AllTransaction>,
    @SerializedName("crypto_balance")
    val cryptoBalance: CryptoBalance,
    @SerializedName("crypto_prices")
    val cryptoPrices: List<CryptoPrice>,
    @SerializedName("your_crypto_holdings")
    val yourCryptoHoldings: List<YourCryptoHolding>
)