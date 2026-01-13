package com.example.apptest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    favorites: List<GroceryItem>,
    onItemClicked: (GroceryItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Mine Favoritter",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (favorites.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = Color.LightGray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Du har ingen favoritter endnu", color = Color.Gray)
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favorites.chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        rowItems.forEach { item ->
                            GridGroceryCard(
                                item = item,
                                modifier = Modifier.weight(1f),
                                onClick = { onItemClicked(item) }
                            )
                        }
                        if (rowItems.size == 1) {
                            Spacer(Modifier.weight(1f))
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }
    }
}
