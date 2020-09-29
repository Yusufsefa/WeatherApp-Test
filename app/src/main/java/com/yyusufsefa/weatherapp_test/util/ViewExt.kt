package com.yyusufsefa.weatherapp_test.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yyusufsefa.weatherapp_test.R

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {

    url?.let {
        val options = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load("http://openweathermap.org/img/wn/" + url + ".png")
            .into(this)
    }

}

fun placeholderProgressBar(context: Context?): CircularProgressDrawable {
    return CircularProgressDrawable(context!!).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android.downloadUrl")
fun downloadImage(view: ImageView, url: String?) {
    view.downloadFromUrl(url, placeholderProgressBar(view.context))
}