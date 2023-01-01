package ru.duremika.kotlinrestfulapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.duremika.kotlinrestfulapi.entity.Product

interface ProductRepository: JpaRepository<Product, String> {
}