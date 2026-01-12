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
    Column {
        Text(item.name)

        IconButton(onClick = onToggleFavorite) {
            Icon(
                imageVector = if (isFavorite)
                    Icons.Filled.Favorite
                else
                    Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorit"
            )
        }

        Button(onClick = onAddToShoppingList) {
            Text("Tilføj til indkøbsliste")
        }

        Button(onClick = onBack) {
            Text("Tilbage")
        }
    }
}
