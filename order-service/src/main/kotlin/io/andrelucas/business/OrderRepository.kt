package io.andrelucas.business

interface OrderRepository {

    fun save(order: Order)
    fun findById(id: String): Order
    fun update(order: Order)
}