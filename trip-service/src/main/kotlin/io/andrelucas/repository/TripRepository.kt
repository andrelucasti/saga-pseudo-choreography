package io.andrelucas.repository

import io.andrelucas.business.Trip

interface TripRepository {
    fun save(trip: Trip)
}