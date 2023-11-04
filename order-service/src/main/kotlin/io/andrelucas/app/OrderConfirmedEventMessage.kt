package io.andrelucas.app

data class OrderConfirmedEventMessage(val orderId: String, val items: List<OrderItemEventMessage>)

data class OrderItemEventMessage(val productId: String, val quantity: Int)