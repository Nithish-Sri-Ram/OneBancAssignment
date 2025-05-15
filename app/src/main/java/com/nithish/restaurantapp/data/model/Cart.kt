package com.nithish.restaurantapp.data.model



data class Cart(
    val items: MutableList<CartItem> = mutableListOf(),
    val cgst: Double = 0.025,
    val sgst: Double = 0.025
) {
    fun addItem(item: CartItem) {
        val existingItemIndex = items.indexOfFirst { it.itemId == item.itemId }
        if (existingItemIndex != -1) {
            items[existingItemIndex].quantity += 1
        } else {
            items.add(item)
        }
    }

    fun removeItem(itemId: String) {
        val existingItemIndex = items.indexOfFirst { it.itemId == itemId }
        if (existingItemIndex != -1) {
            val item = items[existingItemIndex]
            if (item.quantity > 1) {
                item.quantity -= 1
            } else {
                items.removeAt(existingItemIndex)
            }
        }
    }

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