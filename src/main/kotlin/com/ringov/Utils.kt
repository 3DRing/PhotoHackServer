package com.ringov

import java.util.regex.Pattern

object Utils {
    fun matchesList(sourceString: String, wildcard: Pattern): List<String> {
        val matcher = wildcard.matcher(sourceString)
        val list = ArrayList<String>()
        while (matcher.find()) {
            list.add(matcher.group())
        }
        return list
    }

    fun splitByWords(text: String, parts: Int): List<String> {
        val words = text.split(" ")
        val list = ArrayList<String>()
        var partIndex = 0
        val canonSize = words.size / parts
        var wordIndex = 0
        while (partIndex < parts) {

            val sb = StringBuilder()
            while (wordIndex <= (canonSize * (partIndex + 1)) && wordIndex < words.size) {
                sb.append(words[wordIndex]).append(" ")
                wordIndex++
            }
            list.add(sb.toString().trim())
            partIndex++
        }
        return list
    }
}