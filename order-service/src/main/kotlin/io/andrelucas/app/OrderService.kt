package io.andrelucas.app

import io.andrelucas.business.Order
import io.andrelucas.business.OrderItem
import io.andrelucas.business.OrderRepository
import io.andrelucas.business.OrderStatus
import io.andrelucas.thirdparty.*

class OrderService(private val orderRepository: OrderRepository,
                   private val orderCreateEvent: OrderCreateEvent,
                   private val orderUpdateStatusEvent: OrderUpdateStatusEvent) {

    fun createOrder(orderRequest: OrderRequest) {

        val order = Order.create(
            orderRequest.restaurantId,
            orderRequest.customerId,
            orderRequest.products.map { OrderItem(it.productId, it.quantity) }
        )

        // open transaction, for example with Spring @Transactional above this method
        // with this, if any exception occurs, the transaction will be rolled back

        // with another example is using ktor with exposed, we can use transaction { } block to open a transaction
        try {
            orderRepository.save(order)
        } catch (e: Exception) {
            throw Exception("Error saving order", e)
        }

        // if any exception occurs here, the transaction will be rolled back and the order will not be published to the event bus
        orderCreateEvent.publish(
            OrderEventMessage(
                order.orderId.toString(),
                order.restaurantId.toString(),
                order.customerId.toString(),
                order.products.map { OrderItemMessage(it.productId.toString(), it.quantity) }
            )
        )
    }

    fun confirmOrder(orderConfirmedEventMessage: OrderConfirmedEventMessage) {
        val order = orderRepository.findById(orderConfirmedEventMessage.orderId)
        val orderConfirmed = Order.confirm(order)

        orderRepository.update(orderConfirmed)

        orderUpdateStatusEvent.publish(
            OrderUpdateStatusEventMessage(
                orderConfirmed.orderId.toString(),
                OrderStatus.CONFIRMED.name
            )
        )
    }


    fun cancelOrder(orderCancelledEventMessage: OrderCancelledEventMessage) {
        val order = orderRepository.findById(orderCancelledEventMessage.orderId)
        val orderCancelled = Order.cancel(order)

        orderRepository.update(orderCancelled)

        orderUpdateStatusEvent.publish(
            OrderUpdateStatusEventMessage(
                orderCancelled.orderId.toString(),
                OrderStatus.CANCELLED.name
            )
        )
    }
}