package com.morse.animeslayer.ui.fragments.home

sealed class HomeIntents {

    object GetCurrentSeason : HomeIntents()

    data class GetSchedules(val year: String, val season: String) : HomeIntents()

}
