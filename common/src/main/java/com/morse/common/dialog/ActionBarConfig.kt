package com.expertapps.base

import com.morse.common.R

data class ActionBarConfig(
    var actionBarTitle: String? = "",
    var actionBarShown: Boolean = true,
    var displayShowHomeEnabled: Boolean = false,
    var displayHomeAsUpEnabled: Boolean = false,
    var upIndicator: Int = R.drawable.ic_back ,
    var logoUrlFromNetwork : String ?= "" ,
)
