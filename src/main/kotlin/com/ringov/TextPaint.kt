package com.ringov

import java.awt.Font
import java.awt.Graphics2D
import java.awt.Rectangle

object TextPaint {

    fun drawTextInRectangle(g: Graphics2D, text: String, rect: Rectangle) {
        var lineCount = 1
        if (text.length > 50) {
            lineCount = 3
        } else if (text.length > 20) {
            lineCount = 2
        }
        val strings = if (lineCount > 1) {
            Utils.splitByWords(text, lineCount)
        } else {
            emptyList()
        }

        if (strings.isEmpty()) {
            val font = scaleFontToFitTextInRent(g.font, g, text, rect)
            val metrics = g.getFontMetrics(font)
            val x = rect.x + (rect.width - metrics.stringWidth(text)) / 2
            val y = rect.y + (rect.height - metrics.height) / 2 + metrics.ascent
            g.font = font
            g.drawString(text, x, y)
        } else {
            var longest = 0
            var size = strings[0].length
            for (i in 0 until strings.size) {
                if (strings[i].length > size) {
                    size = strings[i].length
                    longest = i
                }
            }

            val font = scaleFontToFitTextInRent(g.font, g, strings[longest], rect)
            val metrics = g.getFontMetrics(font)
            val x = (rect.x + rect.width * 0.03).toInt()
            val y = rect.y + (rect.height - metrics.height) / 2 + metrics.ascent
            g.font = font

            drawString(g, strings, x, y)
        }
    }

    private fun drawString(g: Graphics2D, strings: List<String>, x: Int, y: Int) {
        var tmpY = y
        val lineHeight = g.fontMetrics.height
        for (line in strings) {
            g.drawString(line, x, tmpY)
            tmpY += lineHeight
        }
    }

    private fun scaleFontToFitTextInRent(font: Font, g: Graphics2D, text: String, rect: Rectangle): Font {
        var result = font
        var isTextFit = false
        val widthThreshold = rect.width / 10
        var iterations = 0
        while (!isTextFit && result.size > 1 && iterations < 256) {
            val textWidth = g.getFontMetrics(result).stringWidth(text)
            isTextFit = (rect.width - textWidth) in 0..widthThreshold
            if (!isTextFit) {
                val newSize = when {
                    textWidth > rect.width -> result.size - 1f
                    else -> result.size + 1f
                }
                result = result.deriveFont(newSize)
            }
            iterations++
        }
        return result
    }
}
