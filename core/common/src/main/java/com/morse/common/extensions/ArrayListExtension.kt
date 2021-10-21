package com.morse.common.extensions

fun <T : Any> ArrayList<T>.addOnlyNotExist(input: T) {
    if (!contains(input)) {
        this.add(input)
    }
}

fun <T : Any> ArrayList<T>.removeOnlyExist(input: T) {
    if (contains(input)) {
        remove(input)
    }
}