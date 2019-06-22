package com.ringov

object Logger {
    private const val GLOBAL_TAG = "Logger"

    fun log(message: String) {
        System.out.println("$GLOBAL_TAG > $message")
    }

    fun log(source: Any, message: String) {
        log("${source::class.java.simpleName}: $message")
    }
}