package com.ringov

import org.springframework.core.io.FileSystemResource
import org.springframework.http.*
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*
import org.springframework.util.StringUtils.cleanPath
import org.springframework.web.client.RestTemplate
import java.io.IOException


@RestController
class FileUploadController {

    companion object {
        private const val FILE_STORE_URL = "https://api.anonymousfiles.io/"
        private const val OUTPUT_STORE_URL = "https://anonymousfiles.io/f/"

        private val TMP_FILE_DIR: String = System.getProperty("java.io.tmpdir")
    }

    @RequestMapping(value = "/upload", method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.MULTIPART_FORM_DATA_VALUE))
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<Object> {
        Logger.log(file.originalFilename + " " + file.contentType + ", " + file.name)

        val newFile = multipartToFile(file, generateName())
        val response = buildFileRequest(newFile)
        newFile.delete()
        Logger.log("Tmp file removed: ${!newFile.exists()}")

        Logger.log(response.toString())

        return ResponseEntity(buildResponse(newFile.name) as Object, HttpStatus.OK)
    }

    @Throws(IllegalStateException::class, IOException::class)
    fun multipartToFile(multipart: MultipartFile, fileName: String): File {
        val filename = cleanPath(multipart.originalFilename)
        val dotIndex = filename.lastIndexOf(".")
        val extension = filename.subSequence(dotIndex, filename.length)
        val convertFile = File(TMP_FILE_DIR, fileName + extension)
        multipart.transferTo(convertFile)

        Logger.log("Tmp file created: ${convertFile.exists()}")

        return convertFile
    }

    private fun generateName(): String {
        return UUID.randomUUID().toString()
    }

    private fun buildFileRequest(file: File): ResponseEntity<String> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA

        val body = LinkedMultiValueMap<String, Any>()
        body.add("file", FileSystemResource(file))

        val requestEntity = HttpEntity<MultiValueMap<String, Any>>(body, headers)

        val restTemplate = RestTemplate()
        return restTemplate.postForEntity<String>(FILE_STORE_URL, requestEntity, String::class.java)
    }

    private fun buildResponse(fileName: String): String {
        return OUTPUT_STORE_URL + fileName
    }
}