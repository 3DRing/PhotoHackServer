package com.ringov

import java.awt.Rectangle
import java.util.*
import java.util.regex.Pattern

fun <T> List<T>.random(): T {
    val index = Random().nextInt(size)
    return get(index)
}

val endMarkPattern = Pattern.compile("[\\!\\.\\?] .")
fun String.capitilizeSentences(): String {
    val matches = Utils.matchesList(this, endMarkPattern)
    var result = this
    for (m in matches) {
        result = result.replace(m, m.reversed().capitalize().reversed())
    }
    return result.capitalize()
}
