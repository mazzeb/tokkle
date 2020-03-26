package io.bargmann.tokkle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TokkleApplication

fun main(args: Array<String>) {
	runApplication<TokkleApplication>(*args)
}
