package com.farhad.jollyjournal.data.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<Result<T>> = flow {
    val response = apiCall.invoke()
    if (response.isSuccessful) {
        emit(Result.success(response.body()!!))
    } else {
        emit(Result.failure<T>(Exception("API call failed")))
    }
}.catch { e ->
    emit(Result.failure<T>(e))
}