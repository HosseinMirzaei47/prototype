package com.example.prototype.core.utils

import com.example.prototype.core.resource.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineUseCaseNoParameter<out R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(): Resource<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute().let {
                    Resource.success(it)
                }
            }
        } catch (e: Exception) {
            Resource.error(e.message, null)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(): R
}