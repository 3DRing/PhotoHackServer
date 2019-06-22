package com.ringov

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    companion object {
        private const val PING = "/ping"
        private const val VERSION = "/version"
    }

    private val config = Config()

    @RequestMapping(PING)
    internal fun ping(): String {
        return "ping"
    }


    @RequestMapping(VERSION)
    internal fun version(): String {
        return config.getVersion()
    }
}