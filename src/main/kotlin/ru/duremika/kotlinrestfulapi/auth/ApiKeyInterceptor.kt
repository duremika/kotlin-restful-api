package ru.duremika.kotlinrestfulapi.auth

import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import ru.duremika.kotlinrestfulapi.error.UnauthorizedException
import ru.duremika.kotlinrestfulapi.repository.ApiKeyRepository
import java.lang.Exception

@Component
class ApiKeyInterceptor(val repository: ApiKeyRepository) : WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val xApiKey = request.getHeader("X-Api-Key") ?: throw UnauthorizedException()
        if (!repository.existsById(xApiKey)) {
            throw UnauthorizedException(xApiKey)
        }
    }

    override fun postHandle(request: WebRequest, model: ModelMap?){}

    override fun afterCompletion(request: WebRequest, ex: Exception?){}
}