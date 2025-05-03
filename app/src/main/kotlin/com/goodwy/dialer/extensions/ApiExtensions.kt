package com.goodwy.dialer.extensions

import com.goodwy.dialer.helpers.ApiErrorHandler

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}

suspend fun <T> safeApiCall(block: suspend () -> T): ApiResult<T> {
    return try {
        ApiResult.Success(block())
    } catch (e: Exception) {
        ApiResult.Error(ApiErrorHandler.handleApiError(e))
    }
}
