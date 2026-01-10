package com.example.apptest

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProductDetailsScreen(
    item: GroceryItem,
    onAddToShoppingList: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(item.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text("Kategori: ${item.category}")
            Spacer(Modifier.height(8.dp))
            Text("Pris: ${item.price} kr", fontWeight = FontWeight.SemiBold)
        }

        Column {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onAddToShoppingList
            ) {
                Text("Tilføj til indkøbsliste")
            }

            Spacer(Modifier.height(8.dp))

            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onBack
            ) {
                Text("Tilbage")
            }
        }
    }
}
