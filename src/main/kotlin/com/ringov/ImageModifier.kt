package com.ringov

import java.awt.Graphics2D
import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage


class ImageModifier {
    fun modify(file: File): ModifiedImage {
        val image = getImage(file)
        val graphics = getGraphic(image)
        draw(graphics)
        return ModifiedImage(image)
    }

    private fun draw(graphics: Graphics2D) {
        graphics.font = graphics.font.deriveFont(30f)
        graphics.drawString("Hello World!", 100, 100)
        graphics.dispose()
    }

    private fun getGraphic(image: BufferedImage): Graphics2D = image.createGraphics()

    private fun getImage(file: File): BufferedImage = ImageIO.read(file)

    class ModifiedImage(private val image: BufferedImage) {
        fun writeToFile(file: File) {
            ImageIO.write(image, file.extension, file)
        }
    }
}