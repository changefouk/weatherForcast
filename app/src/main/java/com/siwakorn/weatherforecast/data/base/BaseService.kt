package com.siwakorn.weatherforecast.data.base

import com.siwakorn.weatherforecast.common.network.exception.ResponseDataErrorException
import com.siwakorn.weatherforecast.util.extension.isSuccess
import kotlinx.coroutines.flow.*

abstract class BaseService<RESPONSE, RESULT> where RESPONSE : BaseResponse {

    abstract suspend fun callApi(): RESPONSE

    abstract fun mapper(from: RESPONSE): RESULT

    fun execute(): Flow<RESULT> =
        flow { emit(callApi()) }
            .map { validateResponse(it) }
            .map { mapper(it) }
            .catch { e: Throwable -> throw e }

    /**
     * if don't need to validate response == null overide it
     * */
    protected fun validateResponse(response: RESPONSE): RESPONSE {
        if (response.code?.toIntOrNull()?.isSuccess() == false) {
            throw ResponseDataErrorException(
                responseErrorMessage = response.message ?: "",
                _responseErrorCode = response.code.toIntOrNull() ?: 0
            )
        }
        return response
    }

}