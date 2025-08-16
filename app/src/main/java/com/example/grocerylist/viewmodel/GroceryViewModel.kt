package com.example.grocerylist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.grocerylist.model.GroceryItem

class GroceryViewModel : ViewModel() {

    // Source of truth for the UI
    val items = mutableStateListOf<GroceryItem>()

    // "Add item" form state
    var newItemName by mutableStateOf("")
    var newPrices = mutableStateListOf("", "", "", "", "", "")

    val columns: List<String> = List(6) { "" }

    fun addItem() {
        if (newItemName.isBlank()) return
        items.add(
            0,
            GroceryItem(
                name = newItemName.trim(),
                prices = newPrices.toMutableList()
            )
        )
        newItemName = ""
        for (i in 0 until newPrices.size) newPrices[i] = ""
    }

    fun toggleCompleted(item: GroceryItem, checked: Boolean) {
        val index = items.indexOf(item)
        if (index != -1) {
            items[index] = items[index].copy(completed = !item.completed)
        }
        // The item is mutated in place; Compose will recompose because we’re using state lists.
        // If you prefer immutability, replace the object in the list.
    }

    fun toBuyItems(): List<GroceryItem> = items.filter { !it.completed }
    fun completedItems(): List<GroceryItem> = items.filter { it.completed }

    fun delete(item: GroceryItem) {
        items.remove(item)
    }
}