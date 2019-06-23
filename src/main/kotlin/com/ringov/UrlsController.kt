package com.ringov

import com.ringov.data.Url
import com.ringov.data.UrlRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UrlsController(private val urlRepository: UrlRepository) {

    @RequestMapping(value = "/publish", method = arrayOf(RequestMethod.POST))
    fun publishUrl(@RequestParam("url") url: String) {
        urlRepository.save(Url(url = url))
    }

    @RequestMapping(value = "/feed", method = arrayOf(RequestMethod.GET))
    fun getUrls(): List<String> = urlRepository.findAll().map(Url::url)
}
