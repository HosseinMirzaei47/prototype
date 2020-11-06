package com.example.prototype.features.users.ui

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.prototype.features.users.data.User
import com.example.prototype.features.users.data.UserModelItem_

class UsersEpoxyController : PagedListEpoxyController<User>() {

    private var isError: Boolean = false

    private var error: String? = ""
        set(value) {
            field = value?.let {
                isError = true
                it
            } ?: run {
                isError = false
                null
            }
            if (isError) {
                requestModelBuild()
            }
        }

    private var isLoading = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    override fun buildItemModel(currentPosition: Int, item: User?): EpoxyModel<*> {
        item?.let {
            return UserModelItem_()
                .id("user $currentPosition")
                .user(it)
        } ?: run {
            return LoadingEpoxyModel_()
                .id("loading")
        }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        when {
            isError -> {
                super.addModels(
                    models.plus(
                        //Error View Model
                        ErrorEpoxyModel_()
                            .id("Error")
                            .errorStr(error)
                    ).filter { it !is LoadingEpoxyModel_ }
                )
            }
            isLoading -> {
                super.addModels(
                    models.plus(
                        //Error View Model
                        LoadingEpoxyModel_()
                            .id("loading")
                    ).distinct()
                )
            }
            else -> {
                super.addModels(models.distinct())
            }

        }
    }

    override fun onExceptionSwallowed(exception: RuntimeException) {
    }

}
