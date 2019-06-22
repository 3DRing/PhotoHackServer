package com.ringov

import java.awt.Rectangle

val Rectangle.xRight: Int
    get() = x + width
val Rectangle.yBottom: Int
    get() = y + height

