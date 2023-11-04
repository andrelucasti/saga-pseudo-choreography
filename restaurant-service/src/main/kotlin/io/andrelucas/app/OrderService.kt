package io.andrelucas.app

import io.andrelucas.business.Order
import io.andrelucas.business.OrderItem
import io.andrelucas.business.OrderStatus
import io.andrelucas.repository.OrderRepository
import java.util.*

class OrderService(private val orderRepository: OrderRepository) {

    fun create(orderEventMessage: OrderEventMessage){
        val restaurantId = UUID.fromString(orderEventMessage.restaurantId)
        val orderId = UUID.fromString(orderEventMessage.orderId)
        val customerId = UUID.fromString(orderEventMessage.customerId)
        val products = orderEventMessage.products

        val order = Order(
            orderId.toString(),
            customerId.toString(),
            restaurantId.toString(),
            products.map { product ->
                OrderItem(
                    product.id,
                    product.quantity
                )
            },
            OrderStatus.CONFIRMED
        )

        orderRepository.save(order)
    }
}