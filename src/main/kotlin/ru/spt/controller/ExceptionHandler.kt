package ru.spt.controller

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import ru.spt.dto.ErrorDto

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrorDto> {
        val result: List<String> = (ex.cause as? MissingKotlinParameterException)?.path?.map {
            "В запросе не передан обязательный параметр ${it.fieldName}"
        } ?: listOf(ex.message ?: "bad request")
        return ResponseEntity(ErrorDto(message = result.joinToString()), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleArgumentNotValidException(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ErrorDto> {
        val message = ex.bindingResult.allErrors.joinToString {
            "${it.defaultMessage} [${(it as FieldError).field}]"
        }
        return ResponseEntity(ErrorDto(message = message), HttpStatus.BAD_REQUEST)
    }
}