package com.goodwy.dialer.helpers

import java.io.IOException
import retrofit2.HttpException

object ApiErrorHandler {
    fun handleApiError(exception: Exception): String {
        return when (exception) {
            is IOException -> "Network error. Check your connection."
            is HttpException -> when (exception.code()) {
                401 -> "Unauthorized. Please log in again."
                404 -> "Resource not found."
                in 500..599 -> "Server error. Try again later."
                else -> "HTTP error: ${exception.message}"
            }
            else -> "Unknown error: ${exception.message}"
        }
    }
}
