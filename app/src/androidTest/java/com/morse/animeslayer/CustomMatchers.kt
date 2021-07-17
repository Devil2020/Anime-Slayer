package com.morse.animeslayer

import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textfield.TextInputLayout
import com.morse.animeslayer.domain.AnimeListResponse
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object CustomMatchers {

    fun withAnimeAsSafeTypeMatcher(anime: AnimeListResponse.Anime): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("error happened here")
            }

            override fun matchesSafely(item: View?): Boolean {
                val root = item?.findViewById<View>(R.id.animeListRoot)
                return if (root != null) {
                    if (root.tag is AnimeListResponse) {
                        return anime == root.tag
                    } else false
                } else return false
            }
        }
    }

    fun withErrorAsBoundedMatcher () : Matcher<View>{
        return object :BoundedMatcher<View , TextInputLayout>(TextInputLayout::class.java){
            override fun describeTo(description: Description?) {
                description?.appendText("error happened here")
            }

            override fun matchesSafely(item: TextInputLayout?): Boolean {
                return item?.error == "* - Can`t Logged in here ."
            }
        }
    }

}