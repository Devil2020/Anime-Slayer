package com.morse.common.network.common

import androidx.annotation.DrawableRes

data class ResponseStateErrorData(
    val id: Int,
    @DrawableRes val errorDrawableRes: Int,
    var errorTitle: String,
    var errorMessage: String
)