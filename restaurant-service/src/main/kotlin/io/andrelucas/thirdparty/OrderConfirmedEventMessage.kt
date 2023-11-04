package io.andrelucas.thirdparty

data class OrderConfirmedEventMessage(
    val orderId: String,
    val restaurantId: String,
    val products: List<OrderItemsEventMessage>
)

data class OrderItemsEventMessage(val id: String, val quantity: Int)
