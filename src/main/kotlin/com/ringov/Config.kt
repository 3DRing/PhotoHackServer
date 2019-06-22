package com.ringov

import java.io.File
import java.io.FileInputStream
import java.util.*
import kotlin.collections.HashMap

class Config {

    companion object {
        private const val BUILD_NUMBER_FILE_NAME = "build_number.txt"
    }

    private var buildNumber = 0

    init {
        init()
    }

    private fun init() {
        readBuildNumber()
    }

    private fun readBuildNumber() {
        if (buildNumber > 0) {
            return
        }
        val buildNumberFile = File(BUILD_NUMBER_FILE_NAME)
        if (buildNumberFile.exists()) {
            buildNumber = buildNumberFile.readText().trim().toInt()
        }
    }

    fun getVersion(): String = buildNumber.toString()
}