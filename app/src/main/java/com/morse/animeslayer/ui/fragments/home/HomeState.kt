package com.morse.animeslayer.ui.fragments.home

import com.morse.animeslayer.domain.Anime

sealed class HomeState {

    object isLoading : HomeState()

    data class isError(val exception: Exception) : HomeState()

    data class finishLoadingCurrentSeason(val listOfAnime: List<Anime>) : HomeState()

    data class finishLoadingSchedules(val listOfAnime: List<Anime>) : HomeState()

}