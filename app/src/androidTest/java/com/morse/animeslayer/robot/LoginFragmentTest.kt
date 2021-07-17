package com.morse.animeslayer.robot

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    @Test
    fun testLogin() {
        LoginRobot()
            .writeEmail("ayayahia")
            .writePassword("12345678")
            .pressLogin()
            .verifySuccess ()
    }

}