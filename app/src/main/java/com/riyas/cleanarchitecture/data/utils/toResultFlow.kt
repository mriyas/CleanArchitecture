package com.riyas.cleanarchitecture.data.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> toResultFlow(
    isOnline: Boolean,
    call: suspend () -> NetWorkResult<T?>
): Flow<NetWorkResult<T>> {
    return flow {
        emit(NetWorkResult.Loading(true))
        if (isOnline) {
            val c = call.invoke()
            c.let { response ->
                try {
                    println("response${response.data}")
                    emit(NetWorkResult.Success(response.data))
                } catch (e: Exception) {
                    emit(NetWorkResult.Error(null, e.toString()))
                }
            }
        } else {
            emit(NetWorkResult.Error(exception = "No connectivity"))
        }
    }
}
