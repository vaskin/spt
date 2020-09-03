package ru.spt.dto

import javax.validation.constraints.Size

data class DocumentRequestDto(
    @field:Size(max = 13, min = 13)
    val seller: String,
    @field:Size(max = 13, min = 13)
    val customer: String,
    val products: List<ProductDto>
)