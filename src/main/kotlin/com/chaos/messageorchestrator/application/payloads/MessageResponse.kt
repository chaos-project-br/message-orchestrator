package com.chaos.messageorchestrator.application.payloads

import com.chaos.messageorchestrator.domain.entities.Destination
import java.time.LocalDateTime

data class MessageResponse(
    private val creationDate: LocalDateTime,
    private val destination: Destination
)
