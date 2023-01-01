package ru.duremika.kotlinrestfulapi.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "products")
data class Product(
    @Id
    val id: String,
    var name: String,
    var price: Long,
    var quantity: Int,
    val createdAt: Date,
    var updatedAt: Date?,
)