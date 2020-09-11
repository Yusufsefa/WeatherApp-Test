package com.yyusufsefa.weatherapp_test.util


data class Result<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T): Result<T>? {
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

        fun <T> loading(data: T? = null): Result<T> {
            return Result(
                Status.LOADING,
                data,
                null
            )
        }
    }


    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}