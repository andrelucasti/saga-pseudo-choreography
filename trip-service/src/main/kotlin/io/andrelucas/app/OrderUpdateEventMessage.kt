package io.andrelucas.app

data class OrderUpdateEventMessage(val orderId: String, val status: String)