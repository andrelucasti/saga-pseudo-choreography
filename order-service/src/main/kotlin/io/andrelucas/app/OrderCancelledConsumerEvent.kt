package io.andrelucas.app

class OrderCancelledConsumerEvent(private val orderService: OrderService) {

    fun consume(orderCancelledEventMessage: OrderCancelledEventMessage) {
        orderService.cancelOrder(orderCancelledEventMessage)
    }
}