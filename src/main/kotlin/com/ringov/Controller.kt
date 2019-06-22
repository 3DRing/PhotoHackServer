package com.ringov

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    companion object {
        private const val PING = "/ping"
    }

    @RequestMapping(PING)
    internal fun ping(): String {
        return "ping"
    }
}