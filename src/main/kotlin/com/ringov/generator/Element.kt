package com.ringov.generator

import com.ringov.Utils
import com.ringov.random
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.HashMap

open class Element(private val patterns: List<String>, private val capsAllowed: Boolean = false) {
    companion object {
        private val WILD_CARD = Pattern.compile("\\[(.*?)]")

        private val rand = Random()
    }

    constructor(pattern: String) : this(listOf(pattern))

    private val subElement = HashMap<String, MutableList<Element>>()

    fun generate(): String {
        var result = patterns.random()
        if (capsAllowed) {
            if (rand.nextBoolean()) {
                result = result.toUpperCase()
            }
        }
        val wildcards = Utils.matchesList(result, WILD_CARD)
        for (w in wildcards) {
            val list = subElement[w.trimBraces()]
            if (list != null) {
                val randomElement = list.random()
                val e = randomElement.generate()
                result = result.replace(w, e, true)
                result = result.trim()
            }
        }
        return result
    }

    fun addSub(key: String, element: Element): Element {
        val list = subElement[key]
        if (list == null) {
            subElement[key] = mutableListOf(element)
        } else {
            list.add(element)
            subElement[key] = list
        }
        return this
    }

    private fun String.trimBraces() = this.subSequence(1, this.length - 1)
}