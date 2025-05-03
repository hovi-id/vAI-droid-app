package com.goodwy.dialer.reponse

data class ApiResponse<T>(
    val success: Boolean,
    val response: T
)
