package com.yyusufsefa.weatherapp_test.util


data class Result<out T>(val status: Status, val data: T?, val messaga: String?) {

    companion object {
        fun <T> succes(data: T): Result<T>? {
            return Result(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(message: String, data: T? = null): Result<T> {
            return Result(
                Status.ERROR,
                data,
                message
            )
        }

    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }


}