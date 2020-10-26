package com.example.prototype.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.prototype.R

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