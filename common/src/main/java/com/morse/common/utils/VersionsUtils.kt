package com.morse.common.utils

import android.os.Build

class VersionsUtils {
    companion object {
        fun isAfter25(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
        }

        fun isAfter24(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
        }

        fun isAfter23(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        }

        fun isAfter22(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
        }

        fun isAfter21(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        }

        fun isAfter20(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH
        }

        fun isAfter19(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
        }

        fun isAfter18(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2
        }

        fun isAfter17(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
        }

        fun isAfter16(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
        }

        fun isAfter14(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH
        }

        fun isAfter13(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2
        }

        fun isAfter11(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
        }

        fun isAfter9(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD
        }

        fun isAfter8(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO
        }

        fun isAfter5(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR
        }
    }
}