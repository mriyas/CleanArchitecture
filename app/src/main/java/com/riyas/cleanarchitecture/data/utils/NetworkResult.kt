package com.riyas.cleanarchitecture.data.utils

sealed class NetWorkResult<out T>(val status: NetworkStatus, open val data: T?, val message: String?) {

    data class Success<out T>(override val data: T?) :
        NetWorkResult<T>(status = NetworkStatus.SUCCESS, data = data, message = null)

    data class Error<out T>(override val data: T? = null, val exception: String) :
        NetWorkResult<T>(status = NetworkStatus.ERROR, data = data, message = exception)

    data object Loading : NetWorkResult<Nothing>(status = NetworkStatus.LOADING, data = null, message = null)

    class Idle<out T> : NetWorkResult<T>(status = NetworkStatus.IDLE, data = null, message = null)
}



