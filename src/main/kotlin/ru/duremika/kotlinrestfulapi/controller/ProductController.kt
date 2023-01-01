package ru.duremika.kotlinrestfulapi.controller

import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import ru.duremika.kotlinrestfulapi.model.*
import ru.duremika.kotlinrestfulapi.service.ProductService

@RestController
@RequestMapping("/api/products")
class ProductController(val service: ProductService) {
    @PostMapping(produces = [APPLICATION_JSON_VALUE], consumes = [APPLICATION_JSON_VALUE])
    fun create(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        val productResponse = service.create(body)

        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @GetMapping("/{id_product}", produces = [APPLICATION_JSON_VALUE])
    fun get(@PathVariable("id_product") id: String): WebResponse<ProductResponse> {
        val productResponse = service.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @PutMapping("/{id_product}", produces = [APPLICATION_JSON_VALUE], consumes = [APPLICATION_JSON_VALUE])
    fun update(
        @PathVariable("id_product") id: String,
        @RequestBody body: UpdateProductRequest
    ): WebResponse<ProductResponse> {
        val productResponse = service.update(id, body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @DeleteMapping("/{id_product}", produces = [APPLICATION_JSON_VALUE])
    fun delete(@PathVariable("id_product") id: String): WebResponse<String> {
        service.delete(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "'$id' DELETED"
        )
    }

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    fun list(
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("size", defaultValue = "10") size: Int
    ): WebResponse<List<ProductResponse>> {
        val request = ListProductRequest(page = page, size = size)
        val productResponse = service.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }
}