package com.ringov.generator

import com.ringov.random
import java.util.regex.Pattern

open class Element<T>(private val patterns: List<T>) {
    companion object {
        val WILD_CARD = Pattern.compile("\\[(.*?)]")

        fun matchesList(sourceString: String, wildcard: Pattern): List<String> {
            val matcher = wildcard.matcher(sourceString)
            val list = ArrayList<String>()
            while (matcher.find()) {
                list.add(matcher.group())
            }
            return list
        }
    }

    constructor(pattern: T) : this(listOf(pattern))

    private val subElement = HashMap<String, Element<*>>()

    fun generate(toStringMapper: (T) -> String): String {
        var result = toStringMapper(patterns.random())
        val wildcards = matchesList(result, WILD_CARD)
        for (w in wildcards) {
            val e = subElement[w.trimBraces()]
            if (e != null) {
                val randomElement = e.generate()
                result = result.replace(w, randomElement)
            }
        }
        return result
    }

    fun generate(): String = generate { it.toString() }

    fun addSub(key: String, element: Element<*>) {
        subElement[key] = element
    }

    private fun String.trimBraces() = this.subSequence(1, this.length - 1)
}