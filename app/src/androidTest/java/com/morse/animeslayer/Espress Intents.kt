package com.morse.animeslayer

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Intents {

    @Test
    fun openDualPhone() {
        Intents.intended(IntentMatchers.toPackage("com.google.dual"))
    }


    @Test
    fun lauchIntentAndFakeAResponse() {
        val resultIntent = Intent().apply {
            putExtra("name", " Mohamme Morse ")
        }
        val result = Instrumentation.ActivityResult(20, resultIntent)
        Intents.intending(IntentMatchers.toPackage("com.google.dual")).respondWith(result)
    }

    // كدا انا بعمل موافق لل اني اكتب عادي اما لو عايز يكون رافض هشيل السطر دة عمتا

    @Rule
    val grantPermssionRule = GrantPermissionRule.grant(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @Before
    fun tellAnyOneThatHitIntentAndItTagretOutterPackage() {
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, null)
        Intents.intending(Matchers.not(IntentMatchers.isInternal())).respondWith(result)
    }

    @Test
    fun permissionIntents() {
        Intents.intended(
            Matchers.allOf(
                IntentMatchers.hasAction(Intent.ACTION_CALL) ,
                IntentMatchers.hasData("01119551454")
            )
        )
    }

}