package io.andrelucas.app

import io.andrelucas.business.CheckProductAvailability
import io.andrelucas.thirdparty.*
import java.util.UUID

class RestaurantOrderService(private val checkProductAvailability: CheckProductAvailability,
                             private val orderConfirmedEvent: OrderConfirmedEvent,
                             private val orderCancelledEvent: OrderCancelledEvent,
                             private val orderService: OrderService
) {

    fun createOrder(orderEventMessage: OrderEventMessage) {
        val restaurantId = UUID.fromString(orderEventMessage.restaurantId)
        val orderId = UUID.fromString(orderEventMessage.orderId)
        val products = orderEventMessage.products

        val allProductIsAvailable = checkProductsAvailable(products, restaurantId)

        if (allProductIsAvailable) {
            // open transaction, for example with Spring @Transactional above this method
            // with this, if any exception occurs, the transaction will be rolled back

            // with another example is using ktor with exposed, we can use transaction { } block to open a transaction
            try {
                orderService.create(orderEventMessage)
            } catch (e: Exception) {
                throw Exception("Error saving order", e)
            }

            // if any exception occurs here,
            // the transaction will be rolled back and the order will not be published to the event bus
            orderConfirmedEvent.publish(
                OrderConfirmedEventMessage(
                    orderId.toString(),
                    restaurantId.toString(),
                    products.map { OrderItemsEventMessage(it.id, it.quantity) },
                )
            )

        } else {
            orderCancelledEvent.publish(
                OrderCancelledEventMessage(orderId.toString())
            )
        }
    }

    private fun checkProductsAvailable(
        products: List<OrderItems>,
        restaurantId: UUID
    ) = products.stream()
        .allMatch { product ->
            checkProductAvailability.execute(
                restaurantId,
                UUID.fromString(product.id),
                product.quantity
            )
        }
}