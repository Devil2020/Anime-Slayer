package com.expertapps.base.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.expertapps.base.ActionBarConfig

open class BaseActivityViewModel : ViewModel() {

    val actionBarConfig = MutableLiveData(ActionBarConfig())

    fun setActionBar(newActionBarConfig: ActionBarConfig?) {
        actionBarConfig.value = newActionBarConfig
    }

    fun getActionBar(): ActionBarConfig {
        return ActionBarConfig() /* return the default action bar so that every fragment has an
         immutable version of the actionBarConfig*/
    }

}