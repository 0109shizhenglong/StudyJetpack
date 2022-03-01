package com.example.jetpackmvvm.state

import android.security.identity.ResultData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackmvvm.network.AppException
import com.example.jetpackmvvm.network.BaseResponse
import com.example.jetpackmvvm.network.ExceptionHandle
import java.lang.Error

/**
 *自定义结果集封装类
 */
sealed class ResultState<out T> {
    companion object {
        fun <T> onAppSuccess(data: T): ResultState<T> = Success(data)
        fun <T> onAppLoading(loadingMessage: String): ResultState<T> = Loading(loadingMessage)
        fun <T> onAppError(error: AppException): ResultState<T> = Error(error)
    }

    data class Loading(val loadingMessage: String) : ResultState<Nothing>()

    data class Success<out T>(val data: T) : ResultState<T>()

    data class Error(val error: AppException) : ResultState<Nothing>()
}

/**
 * 处理返回值
 */
fun <T> MutableLiveData<ResultState<T>>.parseResult(result: BaseResponse<T>) {
    value = when {
        result.isSuccess() -> {
            ResultState.onAppSuccess(result.getResponseData())
        }
        else -> {
            ResultState.onAppError(AppException(result.getResponseCode(), result.getResponseMsg()))
        }
    }
}

/**
 * 不处理返回值，直接返回请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.parseResult(result: T) {
    value = ResultState.onAppSuccess(result)
}

/**
 * 异常转换异常处理
 */
fun <T> MutableLiveData<ResultState<T>>.paresException(e: Throwable) {
    this.value = ResultState.onAppError(ExceptionHandle.handleException(e))
}