package com.morse.common.extensions

import android.content.Context
import android.graphics.BitmapFactory

fun Int.createBitmap(context: Context) = BitmapFactory.decodeResource(
    context.resources,
    this
)