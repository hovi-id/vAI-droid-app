package com.goodwy.dialer.api

import com.goodwy.dialer.models.GenericResponse
import com.goodwy.dialer.models.ServerResponse
import com.goodwy.dialer.reponse.*
import com.goodwy.dialer.requests.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("wallet/create")
    fun createWallet(@Body post: WalletRequest): Call<ApiResponse<AuthResponse>>

    @POST("wallet/connections/accept")
    fun acceptConnection(@Header("Authorization") token: String, @Body post: AcceptConnectionRequest): Call<ApiResponse<Connection>>

    @GET("wallet/connections")
    fun getAllConnections(@Header("Authorization") token: String, @Query("walletSecret") walletSecret: String): Call<ConnectionResponse>

    @POST("wallet/proof/send-request")
    fun sendProofRequest(@Header("Authorization") token: String, @Body post: ProofRequest): Call<ApiResponse<ProofDetail>>


    @GET("wallet/proof/status")
    fun getProofRequestStatus(@Header("Authorization") token: String, @Query("walletSecret") walletSecret: String, @Query("proofRecordId") proofRecordId: String): Call<ProofRequestStatusResponse>

}


