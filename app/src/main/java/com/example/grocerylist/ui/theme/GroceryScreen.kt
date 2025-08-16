package com.example.grocerylist.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.grocerylist.model.GroceryItem
import com.example.grocerylist.viewmodel.GroceryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceryApp(vm: GroceryViewModel = viewModel()) {
    MaterialTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Household Grocery Listing") }) },
            floatingActionButton = {
                FloatingActionButton(onClick = { vm.addItem() }) { Text("+") }
            }
        ) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(12.dp)
            ) {
                AddItemForm(vm)
                Spacer(Modifier.height(8.dp))
                GroceryLists(vm)
            }
        }
    }
}

@Composable
fun AddItemForm(vm: GroceryViewModel) {
    OutlinedTextField(
        value = vm.newItemName,
        onValueChange = { vm.newItemName = it },
        label = { Text("Item name") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
    Spacer(Modifier.height(6.dp))
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        val priceLabels = listOf("Flipkart", "Big Basket", "Swiggy", "DMart", "Amazon", "Jio Mart")

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            priceLabels.chunked(2).forEach { rowItems ->
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    rowItems.forEachIndexed { idx, label ->
                        val globalIdx = priceLabels.indexOf(label)
                        OutlinedTextField(
                            value = vm.newPrices[globalIdx],
                            onValueChange = { s ->
                                vm.newPrices[globalIdx] = s.filter { ch -> ch.isDigit() || ch == '.' }
                            },
                            label = { Text(label) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
    Spacer(Modifier.height(6.dp))
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Button(onClick = { vm.addItem() }) {
            Text("Add Item")
        }
    }
}

@Composable
private fun GroceryLists(vm: GroceryViewModel) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { SectionHeader("To Buy") }
        items(
            vm.items.filter { !it.completed },
            key = { groceryItem -> groceryItem.id }
        ) { item ->
            GroceryRow(item = item, onCheckedChange = { isChecked ->
                vm.toggleCompleted(item, isChecked)
            })
        }

        item {
            Spacer(Modifier.height(16.dp))
            SectionHeader("Completed")
        }
        items(
            vm.items.filter { it.completed },
            key = { groceryItem -> groceryItem.id }
        ) { item ->
            GroceryRow(item = item, onCheckedChange = { isChecked ->
                vm.toggleCompleted(item, isChecked)
            })
        }
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium)
    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
}

@Composable
private fun GroceryRow(
    item: GroceryItem,
    onCheckedChange: (Boolean) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    // Define your desired price labels here or pass them as a parameter
    val priceLabels = listOf("F", "B", "S", "D", "A", "J")

    Card(onClick = { expanded = !expanded }) {
        Column(Modifier.fillMaxWidth().padding(12.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Checkbox(
                    checked = item.completed,
                    onCheckedChange = { isChecked -> onCheckedChange(isChecked) }
                )

                OutlinedTextField(
                    value = item.name,
                    onValueChange = { newText -> item.name = newText },
                    label = { Text("Item") },
                    modifier = Modifier.weight(1.4f),
                    singleLine = true
                )

                Row(
                    Modifier.weight(2.6f),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Ensure item.prices has enough elements for your labels
                    priceLabels.forEachIndexed { idx, priceLabels ->
                        if (idx < item.prices.size) {
                            OutlinedTextField(
                                value = item.prices[idx],
                                onValueChange = { s ->
                                    item.prices[idx] = s.filter { ch -> ch.isDigit() || ch == '.' }
                                },
                                label = { Text(priceLabels) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                singleLine = true,
                                modifier = Modifier.width(80.dp)
                            )
                        }
                    }
                }
                if (expanded) {
                    Spacer(Modifier.height(8.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        OutlinedButton(onClick = {
                            item.name = ""
                            item.prices.indices.forEach { i -> item.prices[i] = "" }
                        }) {
                            Text("Clear")
                        }
                    }
                }
            }
        }
    }
}
