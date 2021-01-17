package com.batch.product

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportResource

@EnableAutoConfiguration(exclude = [BatchAutoConfiguration::class])
@SpringBootApplication
class ProductIntakeApplication

fun main(args: Array<String>) {
	runApplication<ProductIntakeApplication>(*args)
}
