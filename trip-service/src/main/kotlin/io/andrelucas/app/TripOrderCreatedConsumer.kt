package io.andrelucas.app

import io.andrelucas.business.Trip
import java.util.UUID

class TripOrderCreatedConsumer(private val tripService: TripService) {

    fun consume(orderEventMessage: OrderEventMessage){
        val orderId = UUID.fromString(orderEventMessage.orderId)
        val restaurantId = UUID.fromString(orderEventMessage.restaurantId)
        val customerId = UUID.fromString(orderEventMessage.customerId)

        val newTrip = Trip.create(orderId, restaurantId, customerId)

        // The trip is created as PENDING
        tripService.createTrip(newTrip)

        // Here there is a lot of ways if there is an error in the trip creation. Examples:
        // 1. We can send a message to a dead letter queue
        // 2. We can send a message to a retry queue and create a retry policy

        // If the trip is created successfully, we can publish a message to a trip-created topic
        //  to notify other interested services
    }
}