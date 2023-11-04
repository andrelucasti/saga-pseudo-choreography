package io.andrelucas.business

import io.andrelucas.repository.ProductRepository
import java.util.UUID

class CheckProductAvailability(private val productRepository: ProductRepository) {

    fun execute(restaurantId: UUID, productId: UUID, quantity: Int): Boolean {

        return true
    }
}