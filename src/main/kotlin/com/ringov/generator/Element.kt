package com.ringov.generator

import com.ringov.random
import java.util.regex.Pattern

open class Element(private val patterns: List<String>) {
    companion object {
        private val WILD_CARD = Pattern.compile("\\[(.*?)]")

        private fun matchesList(sourceString: String, wildcard: Pattern): List<String> {
            val matcher = wildcard.matcher(sourceString)
            val list = ArrayList<String>()
            while (matcher.find()) {
                list.add(matcher.group())
            }
            return list
        }
    }

    constructor(pattern: String) : this(listOf(pattern))

    private val subElement = HashMap<String, MutableList<Element>>()

    fun generate(): String {
        var result = patterns.random()
        val wildcards = matchesList(result, WILD_CARD)
        for (w in wildcards) {
            val list = subElement[w.trimBraces()]
            if (list != null) {
                val randomElement = list.random()
                val e = randomElement.generate()
                result = result.replace(w, e)
            }
        }
        return result
    }

    fun addSub(key: String, element: Element) {
        val list = subElement[key]
        if (list == null) {
            subElement[key] = mutableListOf(element)
        } else {
            list.add(element)
            subElement[key] = list
        }
    }

    private fun String.trimBraces() = this.subSequence(1, this.length - 1)
}