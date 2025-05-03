package com.goodwy.dialer.reponse

data class ConnectionResponse(
    val success: Boolean,
    val response: List<Connection>
)
