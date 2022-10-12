package com.example.test10.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.test10.R

fun ImageView.setImage(url: String?) {
    if(!url.isNullOrEmpty()) {
        Glide.with(context).load(url).placeholder(R.color.black).dontAnimate().error(R.color.black).into(this)
    } else {
        setImageResource(R.color.black)
    }
}