package ru.duremika.kotlinrestfulapi.controller

import jakarta.validation.ConstraintViolationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.duremika.kotlinrestfulapi.error.NotFoundException
import ru.duremika.kotlinrestfulapi.error.UnauthorizedException
import ru.duremika.kotlinrestfulapi.model.WebResponse

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(ConstraintViolationException::class)
    fun validationHandler(exception: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = exception.message!!
        )
    }

    @ExceptionHandler(NotFoundException::class)
    fun notFound(exception: NotFoundException): WebResponse<String> {
        return WebResponse(
            code = 404,
            status = "NOT FOUND",
            data = "product with id '${exception.id}' not found"
        )
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun unauthorized(exception: UnauthorizedException): WebResponse<String> {
        return WebResponse(
            code = 401,
            status = "UNAUTHORIZED",
            data = if (exception.xApiKey == null) "Please put your X-Api-Key"
                    else "X-Api-Key: '${exception.xApiKey}' is invalid"
        )
    }
}