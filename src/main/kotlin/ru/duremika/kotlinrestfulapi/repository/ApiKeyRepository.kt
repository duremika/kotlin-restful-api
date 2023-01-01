package ru.duremika.kotlinrestfulapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.duremika.kotlinrestfulapi.entity.ApiKey

interface ApiKeyRepository: JpaRepository<ApiKey, String> {
}