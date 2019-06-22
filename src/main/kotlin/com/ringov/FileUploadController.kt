package com.ringov

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File


@RestController
class FileUploadController {
    @RequestMapping(value = "/upload", method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.MULTIPART_FORM_DATA_VALUE))
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<Object> {
        Logger.log(file.originalFilename + " " + file.contentType)
        return ResponseEntity("File is uploaded successfully" as Object, HttpStatus.OK)
    }
}