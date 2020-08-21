package com.jordivilagut.fintracking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class FintrackingApplication

fun main(args: Array<String>) {
	runApplication<FintrackingApplication>(*args)
}
