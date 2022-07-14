package com.chaos.messageorchestrator.application.payloads

import com.chaos.messageorchestrator.domain.entities.Destination
import com.chaos.messageorchestrator.domain.entities.Message
import java.time.LocalDateTime

data class MessageResponse(
    private val creationDate: LocalDateTime?,
    private val destination: Destination
) {
    constructor(message: Message) : this(
        creationDate = message.date,
        destination = message.destination
    )
}
