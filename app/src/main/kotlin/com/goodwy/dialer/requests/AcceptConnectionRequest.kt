package com.goodwy.dialer.requests

import com.goodwy.dialer.utils.Constants

data class AcceptConnectionRequest(
    val invitationUrl: String=Constants.invitationUrl,
    val walletSecret: String="test",
    val label: String
)
