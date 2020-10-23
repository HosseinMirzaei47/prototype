package com.example.prototype.features.users.data

import android.content.Context
import com.example.prototype.core.resource.Resource
import com.example.prototype.core.resource.Status
import com.example.prototype.core.utils.safeApiCall
import com.example.prototype.features.users.services.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val context: Context,
    private val service: UserApi
) {

    suspend fun getAllUsers(): Resource<List<User>> {
        var resource = Resource<List<User>>(Status.ERROR, null, null)

        /*if (NetworkHelper.isOnline(MyApplication())) {*/
        val request = safeApiCall { service.getAllUsers("2") }

        if (request.status == Status.SUCCESS) {
            resource = Resource.success(request.data?.users)
        } else if (request.status == Status.ERROR) {
            resource = Resource.error("Network Error", null)
        }
        /*}*/

        return resource

    }

}