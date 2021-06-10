package com.morse.common.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

suspend fun isNetworkConnected(): Boolean {
    val command = "ping -c 1 google.com"
    return try {
        withContext(Dispatchers.IO) { Runtime.getRuntime().exec(command).waitFor() == 0 }
    } catch (ioException: IOException) {
        return false
    } catch (interruptedException: InterruptedException) {
        return false
    }
}