package com.example.apptest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingListScreen(
    shoppingList: List<GroceryItem>,
    onRemoveItem: (GroceryItem) -> Unit
) {
    if (shoppingList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Din indkøbsliste er tom 🛒")
        }
    } else {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(shoppingList) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(item.name)
                        IconButton(onClick = { onRemoveItem(item) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Fjern")
                        }
                    }
                }
            }
        }
    }
}
