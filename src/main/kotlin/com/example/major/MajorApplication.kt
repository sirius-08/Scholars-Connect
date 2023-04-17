package com.example.major

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MajorApplication

fun main(args: Array<String>) {
    runApplication<MajorApplication>(*args)
}