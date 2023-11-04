package io.andrelucas.repository

import io.andrelucas.business.Product

interface ProductRepository {
    fun save(product: Product)
    fun findById(id: String): Product?
    fun findAll(): List<Product>
    fun deleteById(id: String)
    fun update(product: Product)
}