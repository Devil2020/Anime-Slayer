package com.expertapps.base.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Base64
import java.net.URLDecoder
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

private const val algorithm = "AES"
private const val tokenKey = "fqJfdzGDvfwbedsKSUGty3VZ9taXxMVw"
private const val padding = "AES/CBC/PKCS5Padding"
private const val ivSize = 16


const val fullDateWithTasSeperatorKey = "yyyy-MM-dd'T'HH:mm:ss"
const val fullDateKey = "MMM. dd, yyyy hh:mm a"
const val dateKey = "MMM dd, yyyy"
const val timeKey = "hh:mm a"


fun String.encryptAES(): String {
    val tokenBytes = tokenKey.toByteArray(Charsets.UTF_8)
    val secretKey = SecretKeySpec(tokenBytes, algorithm)

    val ivByteArray = ByteArray(ivSize)
    val iv = IvParameterSpec(ivByteArray)

    val cipher = Cipher.getInstance(padding)
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)

    val cipherText = cipher.doFinal(toByteArray(Charsets.UTF_8))
    val ivAndCipherText = getCombinedArray(ivByteArray, cipherText)

    return Base64.encodeToString(ivAndCipherText, Base64.NO_WRAP)
}

fun String.decryptAES(): String {
    val tokenBytes = tokenKey.toByteArray(Charsets.UTF_8)
    val secretKey = SecretKeySpec(tokenBytes, algorithm)

    val ivAndCipherText = Base64.decode(this, Base64.NO_WRAP)
    val cipherText = Arrays.copyOfRange(ivAndCipherText, ivSize, ivAndCipherText.size)

    val ivByteArray = Arrays.copyOfRange(ivAndCipherText, 0, ivSize)
    val iv = IvParameterSpec(ivByteArray)

    val cipher = Cipher.getInstance(padding)
    cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)

    return cipher.doFinal(cipherText).toString(Charsets.UTF_8)
}

fun String.isValidImageUrl(locale: Locale): Boolean {

    return this.toLowerCase(locale).endsWith("jpg") || this.toLowerCase(locale).endsWith("png")
}

private fun getCombinedArray(one: ByteArray, two: ByteArray): ByteArray {
    val combined = ByteArray(one.size + two.size)
    for (i in combined.indices) {
        combined[i] = if (i < one.size) one[i] else two[i - one.size]
    }
    return combined
}

fun List<String>.generateMyPrivileges(): String {
    return this.reduceRight { s: String, s1: String ->
        "⚫  $s .\n⚫  $s1 ."
    }
}

fun String.convertDate(locale: Locale): String {
    if (this.isNotEmpty()) {
        val parser = SimpleDateFormat(fullDateWithTasSeperatorKey, locale)
        val formatter = SimpleDateFormat(dateKey)
        return formatter.format(parser.parse(this))
    }
    return ""
}

fun String.convertTime(locale: Locale): String {
    if (this.isNotEmpty()) {
        val parser = SimpleDateFormat(fullDateWithTasSeperatorKey, locale)
        val formatter = SimpleDateFormat(timeKey)
        return formatter.format(parser.parse(this))
    }
    return ""
}

fun String.convertDateWithTime(locale: Locale = Locale.ENGLISH): String {
    if (this.isNotEmpty()) {
        val parser = SimpleDateFormat(fullDateWithTasSeperatorKey, locale)
        val formatter = SimpleDateFormat(fullDateKey ,locale)
        return formatter.format(parser.parse(this))
    }
    return ""
}

private fun String.decodeString() =
    URLDecoder.decode(this, "utf-8")

private fun String.encodeString() =
    URLEncoder.encode(this, "utf-8")

fun String.decode(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this.decodeString(), Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(this.decodeString())
    }
}

/*fun String.toDate(): Date {
    return SimpleDateFormat(Constants.fullDateKey, Locale.getDefault()).parse(this)
}

fun String.toDateWithTime(): Date {
    return SimpleDateFormat(Constants.fullDateWithTasSeperatorKey, Locale.getDefault()).parse(this)
}*/