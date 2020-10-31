package com.example.prototype.features.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prototype.core.resource.Resource
import com.example.prototype.features.home.data.AuthRepository
import com.example.prototype.features.home.data.AuthRequest
import com.example.prototype.features.home.data.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @ViewModelInject constructor(
    private val loginUseCase: LoginUser
) : ViewModel() {

    private val _loginResult = MutableLiveData<Resource<LoginResponse>>()
    val loginResult: MutableLiveData<Resource<LoginResponse>> get() = _loginResult

    fun loginUser(authRequest: AuthRequest) {
        _loginResult.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            /*_loginResult.postValue(loginUseCase(authRequest))*/
        }
    }

    class LoginUser @Inject constructor(
        private val authRepository: AuthRepository
    ) : UseCase<AuthRequest, Resource<LoginResponse>>(Dispatchers.IO) {
        override suspend fun execute(parameters: AuthRequest): Resource<LoginResponse> {
            return authRepository.loginUser(parameters)
        }
    }

}

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameters: P): Resource<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Resource.success(it)
                }
            }
        } catch (e: Exception) {
            Resource.error(e.message, null)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}
