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
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    // For now, let's just pretend the user has favorited the first 4 items
    // Later we will make this real!
    val favoriteItems = mockGroceryList.take(4)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA)) // Light background
            .padding(horizontal = 16.dp)
    ) {
        // Header
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Mine Favoritter",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (favoriteItems.isEmpty()) {
            // Empty State (Show this if no favorites)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
            // Grid of Favorites
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favoriteItems.chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        rowItems.forEach { item ->
                            // We reuse the GridCard from HomeScreen!
                            // Make sure GridGroceryCard is "public" in HomeScreen.kt
                            GridGroceryCard(
                                item = item,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        // Spacer for odd-numbered rows
                        if (rowItems.size == 1) {
                            Spacer(Modifier.weight(1f))
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(80.dp)) } // Space for bottom bar
            }
        }
    }
}