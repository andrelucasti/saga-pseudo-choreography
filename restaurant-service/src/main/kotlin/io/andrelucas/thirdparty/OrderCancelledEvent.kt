package io.andrelucas.thirdparty

interface OrderCancelledEvent {

    fun publish(orderCancelledEventMessage: OrderCancelledEventMessage)
}