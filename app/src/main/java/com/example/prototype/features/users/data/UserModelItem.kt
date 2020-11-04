package com.example.prototype.features.users.data

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.prototype.R

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_holder_user_row)
abstract class UserModelItem : EpoxyModelWithHolder<UserModelItem.Holder>() {

    @EpoxyAttribute
    lateinit var user: User

    class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {
        }

    }
}
