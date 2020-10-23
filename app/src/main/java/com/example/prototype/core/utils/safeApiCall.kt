package com.example.prototype.core.utils

import com.example.prototype.core.resource.Resource
import retrofit2.HttpException

inline fun <T> safeApiCall(responseFunction: () -> T): Resource<T> {
    return try {
        Resource.success(responseFunction.invoke())
    } catch (e: HttpException) {
        Resource.error(e.code().toString(), null)
    } catch (e: Exception) {
        Resource.error(e.message, null)
    }
}