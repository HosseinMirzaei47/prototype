package com.example.prototype.core.utils

import com.example.prototype.core.resource.Resource
import retrofit2.HttpException
import kotlin.math.E

inline fun <T> safeApiCall(responseFunction: () -> T): Resource<T> {
    return try {
        Resource.success(responseFunction.invoke())
    } catch (e: HttpException) {
        e.printStackTrace()
        Resource.error(e.message(), null)
    } catch (e: Exception) {
        e.printStackTrace()
        Resource.error(e.message, null)
    }
}