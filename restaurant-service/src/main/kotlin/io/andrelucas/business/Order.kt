package io.andrelucas.business

data class Order(val id: String,
                 val customerId: String,
                 val restaurantId: String,
                 val products: List<OrderItem>,
                 val status: OrderStatus)




data class OrderItem(val productId: String, val quantity: Int)

enum class OrderStatus {
    CONFIRMED, DELIVERED, CANCELLED
}