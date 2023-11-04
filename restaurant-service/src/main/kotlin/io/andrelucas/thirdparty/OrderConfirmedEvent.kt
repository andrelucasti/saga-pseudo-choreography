package io.andrelucas.thirdparty

interface OrderConfirmedEvent {

    fun publish(orderConfirmedEventMessage: OrderConfirmedEventMessage)
}