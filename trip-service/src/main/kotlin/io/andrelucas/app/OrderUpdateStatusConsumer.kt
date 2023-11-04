package io.andrelucas.app

import io.andrelucas.business.OrderStatus
import java.util.UUID

class OrderUpdateStatusConsumer(private val tripService: TripService) {

    fun consumer(orderUpdateEventMessage: OrderUpdateEventMessage) {
        val orderId = UUID.fromString(orderUpdateEventMessage.orderId)

        // if the status not exists, the OrderStatus.valueOf will throw an exception and the message
        // will be sent to a dead letter queue or a retry queue with a retry policy

        val orderStatus = OrderStatus.valueOf(orderUpdateEventMessage.status)

        // Here we could check if the trip exists, if not, we could send a message to a dead letter queue
        // or a retry queue with a retry policy to try again later
        val trip = tripService.findTripByOrderId(orderId)

        when (orderStatus) {
            OrderStatus.CANCELLED ->  tripService.cancel(trip)
            OrderStatus.CONFIRMED ->  tripService.confirm(trip)
        }
    }
}