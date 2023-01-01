package ru.duremika.kotlinrestfulapi.config

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import ru.duremika.kotlinrestfulapi.entity.ApiKey
import ru.duremika.kotlinrestfulapi.repository.ApiKeyRepository

@Component
class ApiKeyConfiguration(private val repository: ApiKeyRepository): ApplicationRunner {

    private val apiKey = "SECRET"

    override fun run(args: ApplicationArguments?) {
        if (!repository.existsById(apiKey)) {
            repository.save(ApiKey(apiKey))
        }
    }
}