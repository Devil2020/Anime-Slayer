package com.expertapps.base.dialog

import androidx.annotation.IntegerRes

interface BaseDialogListener {

    fun onBaseDialogButtonClicked (@IntegerRes buttonId  : Int)

}