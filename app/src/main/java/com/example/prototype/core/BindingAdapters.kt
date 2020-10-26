package com.example.prototype.core

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.prototype.R
import com.example.prototype.core.resource.Resource
import com.example.prototype.core.resource.Status

@BindingAdapter("imageSource")
fun setImageUrl(imageView: ImageView, imageSource: String?) {

    imageSource?.let {
        Glide.with(imageView.context)
            .load(imageSource)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.default_profile_picture)
            )
            .into(imageView)
    }
}

@BindingAdapter("visibleOnResult")
fun visibleOnResult(view: View, resource: Resource<*>?) {
    view.isVisible = resource?.status == Status.SUCCESS
}