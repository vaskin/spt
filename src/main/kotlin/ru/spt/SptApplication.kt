package ru.spt

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SptApplication

fun main(args: Array<String>) {
    SpringApplication.run(SptApplication::class.java, *args)
}
