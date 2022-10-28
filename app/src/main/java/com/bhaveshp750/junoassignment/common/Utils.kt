package com.bhaveshp750.junoassignment.common

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

fun ImageView.loadImage(url: String?) {
    val imageLoader = ImageLoader.Builder(this.context)
        .components { add(SvgDecoder.Factory()) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(500)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}

fun String.doesEndWithNumber() : Boolean {
    val regex = """.*[0-9]""".toRegex()
    return if (this.isEmpty()) false
    else regex.matches(this)

}