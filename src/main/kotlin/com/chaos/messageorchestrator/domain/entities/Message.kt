package com.chaos.messageorchestrator.domain.entities

import java.time.LocalDateTime

open class Message(
    private val author: Author,
    private val date: LocalDateTime? = LocalDateTime.now(),
    private val content: String,
    private val destination: Destination
)

enum class Destination {
    TELEGRAM, EMAIL, COMMAND
}