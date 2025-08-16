package com.example.grocerylist.model

data class GroceryItem(
    val id: Long = System.currentTimeMillis(),
    var name: String = "",
    // Five price columns (leave blank if you only want four)
    var prices: MutableList<String> = MutableList(6) { "" },
    var completed: Boolean = false
)
