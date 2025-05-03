package com.goodwy.dialer.reponse

data class Connection(
    val _tags: Tags?,
    val metadata: Map<String?, Any>?,
    val connectionTypes: List<String>?,
    val previousDids: List<String>?,
    val previousTheirDids: List<String>?,
    val id: String?,
    val createdAt: String?,
    val did: String?,
    val invitationDid: String?,
    val theirLabel: String?,
    val state: String?,
    val role: String?,
    val autoAcceptConnection: Boolean?,
    val threadId: String?,
    val imageUrl: String?,
    val protocol: String?,
    val outOfBandId: String?,
    val updatedAt: String?,
    val theirDid: String?
)
