package com.example.prototype.features.users.ui

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.prototype.R

@EpoxyModelClass(layout = R.layout.loading_layout)
abstract class LoadingEpoxyModel : EpoxyModelWithHolder<LoadingEpoxyModel.Holder>() {

    class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {

        }
    }
}