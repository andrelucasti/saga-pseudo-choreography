package io.andrelucas.app

data class OrderEventMessage(val orderId: String,
                             val restaurantId: String,
                             val customerId: String,
                             val status: String)