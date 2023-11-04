package io.andrelucas.app

import java.util.UUID

data class OrderRequest(val restaurantId: UUID, val customerId: UUID, val products: List<ProductOrderRequest>)

data class ProductOrderRequest(val productId: UUID, val quantity: Int)
