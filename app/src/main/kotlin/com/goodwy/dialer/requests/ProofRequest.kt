package com.goodwy.dialer.requests

data class ProofRequest(
    val connectionId: String,
    val walletSecret: String="test"
)
