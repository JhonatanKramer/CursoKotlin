package com.cursoKotlin.cursoKotlin.advice

import com.cursoKotlin.cursoKotlin.exception.PromocaoNotFoundException
import com.cursoKotlin.cursoKotlin.model.ErrorMessage
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// classe responsavel por monitorar os envios de Json e devolver um erro mais amigavel
@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(JsonParseException::class)
    fun JsonParseExceptionHandler(servletRequest:HttpServletRequest, servletResponse: HttpServletResponse,exception: Exception): ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage("JSON ERROR", exception.message ?:"invalid json"), HttpStatus.BAD_REQUEST )
    }
    @ExceptionHandler(PromocaoNotFoundException::class)
    fun PromocaoExceptionHandler(servletRequest:HttpServletRequest, servletResponse: HttpServletResponse,exception: Exception): ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Promocao Não Localizada", exception.message !!), HttpStatus.NOT_FOUND )

    }

}