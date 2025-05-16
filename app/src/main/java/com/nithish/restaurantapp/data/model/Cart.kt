package com.nithish.restaurantapp.data.model

data class Cart(
    val items: MutableList<CartItem> = mutableListOf(),
    val cgst: Double = 0.025,
    val sgst: Double = 0.025
) {

    fun getNetTotal(): Double {
        return items.sumOf { it.itemPrice * it.quantity }
    }

    fun getTaxAmount(): Double {
        return getNetTotal() * (cgst + sgst)
    }

    fun getGrandTotal(): Double {
        return getNetTotal() + getTaxAmount()
    }

    fun getTotalItems(): Int {
        return items.sumOf { it.quantity }
    }
}