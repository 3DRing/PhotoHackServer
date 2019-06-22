package com.ringov

import java.awt.Rectangle
import java.util.*

val Rectangle.xRight: Int
    get() = x + width
val Rectangle.yBottom: Int
    get() = y + height


fun <T> List<T>.random(): T {
    val index = Random().nextInt(size)
    return get(index)
}
