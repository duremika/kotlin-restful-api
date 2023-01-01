package ru.duremika.kotlinrestfulapi.auth

import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component("X-Api-Key_Configuration")
class ApiKeyConfiguration(val apiKeyInterceptor: ApiKeyInterceptor): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addWebRequestInterceptor(apiKeyInterceptor)
    }
}