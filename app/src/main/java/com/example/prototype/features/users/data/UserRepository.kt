package com.example.prototype.features.users.data

import android.content.Context
import com.example.prototype.MyApplication
import com.example.prototype.core.resource.Resource
import com.example.prototype.core.resource.Status
import com.example.prototype.core.utils.NetworkHelper
import com.example.prototype.core.utils.ServiceBuilder
import com.example.prototype.core.utils.safeApiCall
import com.example.prototype.features.users.services.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val context: Context,
    private val service: UserApi
) {

    suspend fun getAllUsers(): Resource<List<User>> {
        var resource = Resource<List<User>>(Status.ERROR, null, null)

        if (NetworkHelper.isOnline(MyApplication.app)) {
            val request =
                safeApiCall { ServiceBuilder.buildService(UserApi::class.java).getAllUsers("2") }

            if (request.status == Status.SUCCESS) {
                resource = Resource.success(request.data?.data)
            } else if (request.status == Status.ERROR) {
                println("jalil something went Wrong!!!!")
            }
        }

        return resource
    }

}