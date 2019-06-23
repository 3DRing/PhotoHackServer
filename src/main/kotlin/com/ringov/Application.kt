package com.ringov

import com.ringov.generator.Element
import com.ringov.generator.Generator
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val gen = Generator()
            System.out.print(gen.generate())
            //SpringApplication.run(Application::class.java, *args)
        }
    }
}