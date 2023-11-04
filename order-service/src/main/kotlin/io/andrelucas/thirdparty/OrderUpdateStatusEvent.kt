package io.andrelucas.thirdparty

interface OrderUpdateStatusEvent {

    fun publish(orderUpdateStatusEventMessage: OrderUpdateStatusEventMessage)
}