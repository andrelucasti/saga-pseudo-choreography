package io.andrelucas.business

data class Product(val id: String,
                   val name: String,
                   val price: Double,
                   val description: String,
                   val image: String,
                   val restaurantId: String)