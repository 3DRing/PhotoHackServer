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

    private val imageModifier = ImageModifier()

    @RequestMapping(value = "/upload", method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.MULTIPART_FORM_DATA_VALUE))
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        Logger.log(file.originalFilename + " " + file.contentType + ", " + file.name)

        val extension = getExtension(file.originalFilename)
        val fileName = generateName() + extension
        val initialTmpFile = multipartToFile(file, fileName)

        val modifiedImage = imageModifier.modify(initialTmpFile)
        val modifiedImageFile = prepareTmpFile(generateName() + extension)

        modifiedImage.writeToFile(modifiedImageFile)
        initialTmpFile.delete()

        val response = buildFileRequest(modifiedImageFile)
        modifiedImageFile.delete()

        Logger.log("Initial tmp file removed: ${!initialTmpFile.exists()}")
        Logger.log("Modified tmp file removed: ${!modifiedImageFile.exists()}")

        Logger.log(response.toString())

        return ResponseEntity(buildResponse(modifiedImageFile.name), HttpStatus.OK)
    }

    @Throws(IllegalStateException::class, IOException::class)
    fun multipartToFile(multipart: MultipartFile, fileName: String): File {
        val convertFile = prepareTmpFile(fileName)
        multipart.transferTo(convertFile)

        Logger.log("Tmp file created: ${convertFile.exists()}")

        return convertFile
    }

    private fun getExtension(fileName: String): String {
        val filename = cleanPath(fileName)
        val dotIndex = filename.lastIndexOf(".")
        return filename.subSequence(dotIndex, filename.length).toString()
    }

    private fun prepareTmpFile(name: String): File {
        val file = File(TMP_FILE_DIR, name)
        val created = file.createNewFile()
        Logger.log("File ${file.name} created: $created")
        return file
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