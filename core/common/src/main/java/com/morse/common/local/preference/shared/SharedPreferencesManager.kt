package com.morse.common.local.preference.shared

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import com.expertapps.base.extensions.decryptAES
import com.expertapps.base.extensions.encryptAES
import okhttp3.internal.toLongOrDefault
import javax.inject.Inject

class SharedPreferencesManager private constructor(private val sharedPrefsInstance: SharedPreferences) {

    companion object {
        private val LOCK = Any()

        @Volatile
        var sharedPreferences: SharedPreferences? = null

        fun builder(context: Context) = sharedPreferences ?: synchronized(LOCK) {
            PreferenceManager.getDefaultSharedPreferences(
                context
            ).apply {
                sharedPreferences = this
            }
        }
    }

    fun isExist (key : String) : Boolean {
        return sharedPrefsInstance.contains(key)
    }

    fun putBoolean(tag: String, value: Boolean, secure: Boolean = false) {
        sharedPrefsInstance.edit(true) { putBoolean(tag, value) }
    }

    fun getBoolean(tag: String, defValue: Boolean): Boolean {
        return sharedPrefsInstance.getBoolean(tag, defValue)
    }

    fun putString(tag: String, str: String, secure: Boolean = false) {
        if (secure) {
            kotlin.runCatching {
                sharedPrefsInstance.edit(true) { putString(tag, str.encryptAES()) }
            }
        } else {
            sharedPrefsInstance.edit(true) { putString(tag, str) }
        }
    }

    fun getString(tag: String, defStr: String?, secure: Boolean = false): String? {
        return if (secure) {
            kotlin.runCatching {
                sharedPrefsInstance.getString(tag, defStr)?.decryptAES()
            }.getOrDefault(defStr)
        } else {
            sharedPrefsInstance.getString(tag, defStr)
        }

    }

    fun putInt(tag: String, value: Int, secure: Boolean = false) {
        if (secure) {
            sharedPrefsInstance.edit(true) { putString(tag, value.toString().encryptAES()) }
        } else {
            sharedPrefsInstance.edit(true) { putInt(tag, value) }
        }
    }

    fun getInt(tag: String, defValue: Int, secure: Boolean = false): Int? {
        return if (secure) {
            kotlin.runCatching {
                sharedPrefsInstance.getString(tag, defValue.toString())?.let {
                    Integer.parseInt(
                        it.decryptAES()
                    )
                }
            }.getOrDefault(defValue)
        } else {
            sharedPrefsInstance.getInt(tag, defValue)
        }
    }

    fun putLong(tag: String, value: Long, secure: Boolean = false) {
        if (secure) {
            sharedPrefsInstance.edit(true) { putString(tag, value.toString().encryptAES()) }
        } else {
            sharedPrefsInstance.edit(true) { putLong(tag, value) }
        }
    }

    fun getLong(tag: String, defValue: Long, secure: Boolean = false): Long? {
        return if (secure) {
            kotlin.runCatching {
                sharedPrefsInstance.getString(tag, defValue.toString())?.decryptAES()?.toLongOrDefault(0)
            }
                .getOrDefault(defValue)
        } else {
            sharedPrefsInstance.getLong(tag, defValue)
        }
    }

    fun remove(tag: String, secure: Boolean = false, commit: Boolean = false) {
        sharedPrefsInstance.edit(commit) { remove(tag) }
    }

    fun clearPreferences(commit: Boolean = false) {
        sharedPrefsInstance.edit(commit) { clear() }
    }

}