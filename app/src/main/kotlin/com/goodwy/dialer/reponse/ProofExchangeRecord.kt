package com.goodwy.dialer.reponse

data class ProofExchangeRecord(
    val _tags: Tags?,
    val metadata: Map<String, Any>? = emptyMap(),
    val id: String?,
    val createdAt: String?,
    val protocolVersion: String?,
    val state: String?,
    val role: String?,
    val connectionId: String?,
    val threadId: String?,
    val updatedAt: String
)
