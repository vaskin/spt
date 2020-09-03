package ru.spt.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.spt.dto.DocumentRequestDto
import ru.spt.dto.DocumentResponseDto
import javax.validation.Valid

@RestController
@RequestMapping("/documents")
class DocumentController {

    @PostMapping
    fun createDocument(@RequestBody @Valid request: DocumentRequestDto): DocumentResponseDto {
        return DocumentResponseDto()
    }
}