package com.chaos.messageorchestrator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessageOrchestratorApplication

fun main(args: Array<String>) {
	runApplication<MessageOrchestratorApplication>(*args)
}
