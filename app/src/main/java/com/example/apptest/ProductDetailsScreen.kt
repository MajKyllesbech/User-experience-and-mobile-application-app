package com.example.apptest

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder


@Composable
fun ProductDetailsScreen(
    item: GroceryItem,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onAddToShoppingList: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "${item.price} kr",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = item.description,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(24.dp))

        IconButton(onClick = onToggleFavorite) {
            Icon(
                imageVector = if (isFavorite)
                    Icons.Filled.Favorite
                else
                    Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorit"
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onAddToShoppingList,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tilføj til indkøbsliste")
        }

        Spacer(Modifier.height(8.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tilbage")
        }
    }
}
