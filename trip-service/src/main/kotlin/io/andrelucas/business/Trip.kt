package io.andrelucas.business

import java.util.UUID

data class Trip(val tripId: UUID,
                val orderId: UUID,
                val restaurantId: UUID,
                val driverId: UUID?, val customerId: UUID, val status: TripStatus) {
    companion object {
        fun create(orderId: UUID, restaurantId: UUID, customerId: UUID): Trip {
            return Trip(UUID.randomUUID(), orderId, restaurantId, null, customerId, TripStatus.PENDING)
        }
    }
}

enum class TripStatus {
    PENDING, CREATED, CONFIRMED, CANCELLED
}