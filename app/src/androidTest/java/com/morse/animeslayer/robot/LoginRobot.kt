package com.morse.animeslayer.robot

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.morse.animeslayer.R

class LoginRobot {

    fun writeEmail(email: String): LoginRobot {
        Espresso.onView(ViewMatchers.withId(R.id.searchBar))
            .perform(ViewActions.typeText(email))
        return this
    }

    fun writePassword(password: String): LoginRobot {
        Espresso.onView(ViewMatchers.withId(R.id.searchBar))
            .perform(ViewActions.typeText(password))
        return this
    }

    fun pressLogin(): LoginRobot {
        Espresso.onView(ViewMatchers.withId(R.id.searchExtebdedFab))
            .perform(ViewActions.click())
        return this
    }

    fun verifySuccess(): LoginRobot {
        Espresso.onView(ViewMatchers.withId(R.id.cardOfAnimeDescribtion))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )
        return this
    }

}