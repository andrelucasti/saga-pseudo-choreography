package io.andrelucas.thirdparty

interface OrderCreateEvent {

    fun publish(orderEventMessage: OrderEventMessage)
}