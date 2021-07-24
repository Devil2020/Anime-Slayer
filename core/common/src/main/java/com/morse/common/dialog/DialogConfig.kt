package com.expertapps.base.dialog

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DialogConfig(
    var messageTitle : String ?= "",
    var messageBody: String,
    var positiveButtonName: String? = null,
    var negativeRightButtonName: String? = "Ok",
    var negativeLeftButtonName: String? = null
) : Parcelable