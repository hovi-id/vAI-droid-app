package com.goodwy.dialer.reponse

data class ProofRequestStatusResponse(
    val status: Boolean=false,
    val schemaId: String?,
    val issuerDid: String?,
    val credDefId: String?,
    val data: Data?
)


