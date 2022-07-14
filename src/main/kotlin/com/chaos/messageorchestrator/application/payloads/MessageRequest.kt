package com.chaos.messageorchestrator.application.payloads

import com.chaos.messageorchestrator.domain.entities.Author
import com.chaos.messageorchestrator.domain.entities.Destination
import com.chaos.messageorchestrator.domain.entities.Message

data class MessageRequest(
    private val author: Author,
    private val content: String,
    private val destination: Destination
) : Message(
    author = author,
    content = content,
    destination = destination
)
