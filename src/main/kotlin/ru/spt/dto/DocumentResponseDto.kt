package ru.spt.dto

import java.util.*

data class DocumentResponseDto(
    val documentId: UUID = UUID.randomUUID()
)