package com.goodwy.dialer.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenericResponse(
    @Json(name = "success")
    val success: Boolean,

    @Json(name = "message")
    val message: String? = null,

    @Json(name = "data")
    val data: Any? = null  // Or use specific type if known
)
