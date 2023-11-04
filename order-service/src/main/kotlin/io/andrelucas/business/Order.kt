package io.andrelucas.business

import java.util.UUID

data class Order(val orderId:UUID, val restaurantId: UUID, val customerId: UUID, val products: List<OrderItem>, val status: OrderStatus) {
    companion object {
        fun create(restaurantId: UUID, customerId: UUID, products: List<OrderItem>): Order {
            return Order(UUID.randomUUID(), restaurantId, customerId, products, OrderStatus.PENDING)
        }

        fun confirm(order: Order): Order {
            return Order(order.orderId, order.restaurantId, order.customerId, order.products, OrderStatus.CONFIRMED)
        }

        fun cancel(order: Order): Order {
            return Order(order.orderId, order.restaurantId, order.customerId, order.products, OrderStatus.CANCELLED)
        }
    }
}

data class OrderItem(val productId: UUID, val quantity: Int)

enum class OrderStatus {
    PENDING, CREATED, CONFIRMED, CANCELLED
}