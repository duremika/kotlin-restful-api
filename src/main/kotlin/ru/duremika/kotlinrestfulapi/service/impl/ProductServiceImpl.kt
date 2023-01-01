package ru.duremika.kotlinrestfulapi.service.impl

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.duremika.kotlinrestfulapi.entity.Product
import ru.duremika.kotlinrestfulapi.error.NotFoundException
import ru.duremika.kotlinrestfulapi.model.CreateProductRequest
import ru.duremika.kotlinrestfulapi.model.ListProductRequest
import ru.duremika.kotlinrestfulapi.model.ProductResponse
import ru.duremika.kotlinrestfulapi.model.UpdateProductRequest
import ru.duremika.kotlinrestfulapi.repository.ProductRepository
import ru.duremika.kotlinrestfulapi.service.ProductService
import ru.duremika.kotlinrestfulapi.validation.ValidationUtil
import java.util.Date

@Service
class ProductServiceImpl(
    val repository: ProductRepository,
    val validationUtil: ValidationUtil
) : ProductService {
    override fun create(request: CreateProductRequest): ProductResponse {
        validationUtil.validate(request)

        val product = Product(
            id = request.id!!,
            name = request.name!!,
            price = request.price!!,
            quantity = request.quantity!!,
            createdAt = Date(),
            updatedAt = null,
        )
        repository.save(product)
        return productToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product: Product = repository.findByIdOrNull(id) ?: throw NotFoundException(id)
        return productToProductResponse(product)
    }

    override fun update(id: String, request: UpdateProductRequest): ProductResponse {
        val product: Product = repository.findByIdOrNull(id) ?: throw NotFoundException(id)
        validationUtil.validate(request)
        product.apply {
            name = request.name!!
            price = request.price!!
            quantity = request.quantity!!
            updatedAt = Date()
        }
        repository.save(product)
        return productToProductResponse(product)
    }

    override fun delete(id: String) {
        val product: Product = repository.findByIdOrNull(id) ?: throw NotFoundException(id)
        repository.delete(product)
    }

    override fun list(request: ListProductRequest): List<ProductResponse> {
        val page: Page<Product> = repository.findAll(PageRequest.of(request.page, request.size))
        val products: List<Product> = page.toList()

        return products.map { productToProductResponse(it) }
    }

    private fun productToProductResponse(product: Product) =
        ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt,
        )

}