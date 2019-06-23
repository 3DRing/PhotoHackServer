package com.ringov

import java.util.regex.Pattern

class Utils {
    companion object {
        fun matchesList(sourceString: String, wildcard: Pattern): List<String> {
            val matcher = wildcard.matcher(sourceString)
            val list = ArrayList<String>()
            while (matcher.find()) {
                list.add(matcher.group())
            }
            return list
        }
    }
}