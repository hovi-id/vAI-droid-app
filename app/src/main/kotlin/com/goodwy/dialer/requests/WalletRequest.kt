package com.goodwy.dialer.requests

data class WalletRequest(
    val phone_number: String,
    val secret: String="test"
)
