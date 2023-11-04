package io.andrelucas.app

class OrderConfirmedConsumer(private val orderService: OrderService) {

    fun consume(orderConfirmedEventMessage: OrderConfirmedEventMessage) {
        orderService.confirmOrder(orderConfirmedEventMessage)
    }
}