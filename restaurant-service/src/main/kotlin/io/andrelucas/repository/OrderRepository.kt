package io.andrelucas.repository

import io.andrelucas.business.Order

interface OrderRepository {

    fun save(order: Order)

    fun findById(id: String): Order?

    fun findAll(): List<Order>

    fun deleteById(id: String)
}