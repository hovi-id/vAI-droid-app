package com.goodwy.dialer.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true) // Moshi annotation
data class ServerResponse(
    @Json(name = "alive") // Maps JSON "alive" to this property
    val alive: Boolean
)
