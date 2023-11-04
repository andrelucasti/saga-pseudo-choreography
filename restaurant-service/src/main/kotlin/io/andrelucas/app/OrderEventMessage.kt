package io.andrelucas.app

data class OrderEventMessage(val orderId: String,
                             val restaurantId: String,
                             val products: List<OrderItems>,
                             val customerId: String,
                             val status: String)




data class OrderItems(val id: String, val quantity: Int)

