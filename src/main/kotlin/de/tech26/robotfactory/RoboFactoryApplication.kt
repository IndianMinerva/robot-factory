package de.tech26.robotfactory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RoboFactoryApplication

fun main(args: Array<String>) {
	runApplication<RoboFactoryApplication>(*args)
}