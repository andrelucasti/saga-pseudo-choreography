package io.andrelucas.app

class RestaurantOrderCreatedConsumer (private val restaurantOrderService: RestaurantOrderService) {
    fun consume(orderEventMessage: OrderEventMessage) {
        restaurantOrderService.createOrder(orderEventMessage)
    }
}