package com.goodwy.dialer.reponse

data class AuthResponse(
    val id: String?,
    val accessToken: String?,
    val did: String?,
    val seed: String?,
    val webhookUrl: String?
)
