package com.ringov

import java.awt.Color
import java.awt.Graphics2D
import java.awt.Rectangle
import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage


class ImageModifier {
    fun modify(file: File): ModifiedImage {
        val image = getImage(file)
        val rectangle = getRectangle(image)
        val graphics = getGraphic(image)
        draw(graphics, rectangle)
        graphics.dispose()
        return ModifiedImage(image)
    }

    private fun getRectangle(image: BufferedImage): Rectangle = Rectangle(0, 0, image.width, image.height)

    private fun draw(graphics: Graphics2D, rectangle: Rectangle) {
        val blackOpacityColor = Color(0, 0, 0, 150)

        val bottomRectLine = 0.4
        val bottomRect = Rectangle(rectangle.x, (rectangle.height - (rectangle.height * bottomRectLine)).toInt(),
                rectangle.width, (rectangle.height * bottomRectLine).toInt())
        graphics.fillColoredRect(blackOpacityColor, bottomRect)
        TextPaint.drawTextInRectangle(graphics, "SUCH PHOTO EFFECT", bottomRect)
    }

    private fun getGraphic(image: BufferedImage): Graphics2D = image.createGraphics()

    private fun getImage(file: File): BufferedImage = ImageIO.read(file)

    class ModifiedImage(private val image: BufferedImage) {
        fun writeToFile(file: File) {
            ImageIO.write(image, file.extension, file)
        }
    }

    private fun Graphics2D.fillColoredRect(color: Color, rect: Rectangle) {
        val colorTmp = this.color
        this.color = color
        fillRect(rect.x, rect.y, rect.width, rect.height)
        this.color = colorTmp
    }
}