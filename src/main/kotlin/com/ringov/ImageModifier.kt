package com.ringov

import com.ringov.generator.Generator
import java.awt.Color
import java.awt.GradientPaint
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO


class ImageModifier {
    companion object {
        private val random = Random()
    }

    fun modify(file: File): ModifiedImage {
        val image = cropImage(getImage(file))
        val rectangle = getRectangle(image)
        val graphics = getGraphic(image)
        draw(graphics, rectangle)
        graphics.dispose()
        return ModifiedImage(image)
    }

    private fun cropImage(src: BufferedImage): BufferedImage {
        val size = Math.min(src.width, src.height)

        val offsetXLimit = src.width - size
        val offsetYLimit = src.height - size

        val offsetX = if (offsetXLimit > 0) random.nextInt(offsetXLimit) else 0
        val offsetY = if (offsetYLimit > 0) random.nextInt(offsetYLimit) else 0

        return src.getSubimage(0, 0, size - 1, size - 1)
    }

    private fun getRectangle(image: BufferedImage): Rectangle = Rectangle(0, 0, image.width, image.height)

    private fun draw(graphics: Graphics2D, rectangle: Rectangle) {
        val blackOpacityColor = Color(random.nextInt(60),
                random.nextInt(60), random.nextInt(60), 255)
        val bottomRectLine = 0.4
        val bottomRect = Rectangle(rectangle.x, (rectangle.height - (rectangle.height * bottomRectLine)).toInt(),
                rectangle.width, (rectangle.height * bottomRectLine).toInt())

        drawForegroundGradient(graphics, rectangle, bottomRect, blackOpacityColor)
        TextPaint.drawTextInRectangle(graphics, Generator.generate(), bottomRect)
    }

    private fun drawForegroundGradient(graphics: Graphics2D, rectangle: Rectangle,
                                       bottomRect: Rectangle, color: Color) {

        val gradientRect = Rectangle(bottomRect.x, bottomRect.y + 1, bottomRect.x, rectangle.height + 1)
        graphics.fillGradientRect(buildLinearGradient(gradientRect, color), bottomRect)
    }

    private fun buildLinearGradient(rect: Rectangle, color: Color) =
            GradientPaint(rect.x.toFloat(), rect.y.toFloat(),
                    Color(color.red, color.green, color.blue, 0), rect.x.toFloat(),
                    rect.height.toFloat(), color)

    private fun getGraphic(image: BufferedImage): Graphics2D = image.createGraphics()

    private fun getImage(file: File): BufferedImage = ImageIO.read(file)

    class ModifiedImage(private val image: BufferedImage) {
        fun writeToFile(file: File) {
            ImageIO.write(image, file.extension, file)
        }
    }

    private fun Graphics2D.fillGradientRect(gradient: GradientPaint, rect: Rectangle) {
        val paintTmp = this.paint
        this.paint = gradient
        fillRect(rect.x, rect.y, rect.width, rect.height)
        this.paint = paintTmp
    }

    private fun Graphics2D.fillColoredRect(color: Color, rect: Rectangle) {
        val colorTmp = this.color
        this.color = color
        fillRect(rect.x, rect.y, rect.width, rect.height)
        this.color = colorTmp
    }
}