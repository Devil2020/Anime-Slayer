package com.morse.animeslayer.learn

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryOwner


/*
class LearnViewModelFactory(
    private val githubApi: GithubApi,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return DetailViewModel(githubApi, handle) as T
    }
}
*/

class LearnViewModel (val savedStateHandle: SavedStateHandle) : ViewModel() {

    val message : String by lazy {
        savedStateHandle.get<String>("message") ?: "Empty"
    }

    /* There is an function called at last to remove every thing exist in this class .*/
    override fun onCleared() {
        super.onCleared()
    }

}