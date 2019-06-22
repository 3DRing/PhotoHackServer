package com.ringov

import java.io.File

class ImageModifier {
    fun modify(file: File): ModifiedImage {
        return ModifiedImage(file)
    }

    class ModifiedImage(val sourceFile: File)
}