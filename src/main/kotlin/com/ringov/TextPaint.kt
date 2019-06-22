package com.ringov

import java.awt.Font
import java.awt.Graphics2D
import java.awt.Rectangle
import java.lang.Math.abs

object TextPaint {

    fun drawTextInRectangle(g: Graphics2D, text: String, rect: Rectangle) {
        val font = scaleFontToFitTextInRent(g.font, g, text, rect)
        val metrics = g.getFontMetrics(font)
        val x = rect.x + (rect.width - metrics.stringWidth(text)) / 2
        val y = rect.y + (rect.height - metrics.height) / 2 + metrics.ascent
        g.font = font
        g.drawString(text, x, y)
    }

    private fun scaleFontToFitTextInRent(font: Font, g: Graphics2D, text: String, rect: Rectangle): Font {
        var result = font
        var isTextFit = false
        val widthThreshold = rect.width / 10
        while (!isTextFit && result.size > 1) {
            val textWidth = g.getFontMetrics(result).stringWidth(text)
            isTextFit = abs(rect.width - textWidth) <= widthThreshold
            if (!isTextFit) {
                val newSize = when {
                    textWidth > rect.width -> result.size - 1f
                    else -> result.size + 1f
                }
                result = result.deriveFont(newSize)
            }

        }
        return result
    }
}
