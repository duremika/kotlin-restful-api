package ru.duremika.kotlinrestfulapi.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class ApiKey(@Id val id: String)
