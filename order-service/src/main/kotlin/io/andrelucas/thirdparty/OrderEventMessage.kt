package io.andrelucas.thirdparty

data class OrderEventMessage(val orderId: String, val restaurantId: String, val customerId: String, val products: List<OrderItemMessage>)

data class OrderItemMessage(val productId: String, val quantity: Int)