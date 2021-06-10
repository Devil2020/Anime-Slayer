package com.expertapps.connect.utils

import android.text.TextUtils
import com.morse.common.constants.Constants

object InputsValidator {

     fun isEmailValid (email : String) : Boolean {
        val certificateNumberRegex = Regex(Constants.email_regex)
        return certificateNumberRegex.matches(email)
    }

     fun isPasswordValid (password : String) : Boolean {
        return !TextUtils.isEmpty(password)
    }

    fun isConfirmPasswordValid (currentPassword : String , newPassword : String) : Boolean {
        return TextUtils.equals(currentPassword , newPassword) and isPasswordValid(newPassword)
    }


}