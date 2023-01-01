package ru.duremika.kotlinrestfulapi.service

import ru.duremika.kotlinrestfulapi.model.CreateProductRequest
import ru.duremika.kotlinrestfulapi.model.ListProductRequest
import ru.duremika.kotlinrestfulapi.model.ProductResponse
import ru.duremika.kotlinrestfulapi.model.UpdateProductRequest

interface ProductService {
    fun create(request: CreateProductRequest): ProductResponse
    fun get(id: String): ProductResponse
    fun update(id: String, request: UpdateProductRequest): ProductResponse
    fun delete(id: String)
    fun list(request: ListProductRequest): List<ProductResponse>
}