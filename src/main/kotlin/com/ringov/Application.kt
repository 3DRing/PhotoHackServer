package com.ringov

import com.ringov.generator.Generator
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            System.out.print(Generator.generate().capitilizeSentences())
            //SpringApplication.run(Application::class.java, *args)
        }
    }
}