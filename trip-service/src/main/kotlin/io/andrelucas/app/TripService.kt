package io.andrelucas.app

import io.andrelucas.business.Trip
import io.andrelucas.business.TripStatus
import io.andrelucas.repository.TripRepository
import java.util.UUID

class TripService (private val tripRepository: TripRepository) {

    fun createTrip(trip: Trip) {
        tripRepository.save(trip)
    }

    fun findTripByOrderId(orderId: UUID) : Trip {
        return Trip(UUID.randomUUID(), orderId, UUID.randomUUID(), null, UUID.randomUUID(), TripStatus.PENDING)
    }

    fun cancel(trip: Trip) {
        TODO("Not yet implemented")
    }

    fun confirm(trip: Trip) {
        TODO("Not yet implemented")
    }
}