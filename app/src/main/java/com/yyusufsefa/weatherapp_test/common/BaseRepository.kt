package com.yyusufsefa.weatherapp_test.common

import com.yyusufsefa.weatherapp_test.util.Result
import retrofit2.Response

open class BaseRepository {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): com.yyusufsefa.weatherapp_test.util.Result<T> {

        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Result.success(body)!!
                }
            }
            return error(response.message(), response.code())
        } catch (e: Exception) {
            return error(e.message ?: e.toString(), e.hashCode())
        }
    }

    private fun <T> error(message: String, errorCode: Int): Result<T> {
        return Result.error("Network call has failed for a following reason: $message")
    }
}