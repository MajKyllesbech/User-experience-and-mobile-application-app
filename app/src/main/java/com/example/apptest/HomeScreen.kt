package com.example.apptest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.KebabDining
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apptest.ui.theme.AppTestTheme

/**
 * This is the Home Screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAddToShoppingList: (GroceryItem) -> Unit,
    onItemClicked: (GroceryItem) -> Unit
) {

    var searchText by rememberSaveable { mutableStateOf("") }

    // We use the mock list from our new 'GroceryData.kt' file
    val filteredItems = mockGroceryList.filter {
        it.name.contains(searchText, ignoreCase = true)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // "Hello" Greeting
        item {
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Hej, bruger!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(16.dp))
        }

        // Search Bar
        item {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Søg efter fødevarer...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp)
            )
            Spacer(Modifier.height(24.dp))
        }

        // Categories
        item {
            Text(
                text = "Kategorier",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CategoryItem(icon = Icons.Default.Grass, label = "Produce")
                CategoryItem(icon = Icons.Default.Egg, label = "Dairy")
                CategoryItem(icon = Icons.Default.KebabDining, label = "Meat")
                CategoryItem(icon = Icons.Default.BakeryDining, label = "Bakery")
            }
            Spacer(Modifier.height(24.dp))
        }

        // Popular Items title
        item {
            Text(
                text = "Populære varer",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(16.dp))
        }

        // List of Grocery Items
        items(filteredItems) { item ->
            GroceryItemCard(
                item = item,
                onActionClick = { onAddToShoppingList(item) },
                actionIcon = Icons.Default.AddShoppingCart,
                actionDescription = "Tilføj",
                onCardClick = { onItemClicked(item) }
            )
            Spacer(Modifier.height(8.dp))
        }

        item {
            Spacer(Modifier.height(16.dp)) // Add space at the bottom
        }
    }
}


// --- Reusable Composables ---

@Composable
fun CategoryItem(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.padding(16.dp)
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(text = label, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun GroceryItemCard(
    item: GroceryItem,
    onActionClick: () -> Unit,
    actionIcon: ImageVector,
    actionDescription: String,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = onCardClick
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(item.name, fontWeight = FontWeight.Bold)
                Text(item.category, style = MaterialTheme.typography.bodySmall)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("${item.price} kr", modifier = Modifier.padding(end = 8.dp))
                IconButton(onClick = onActionClick) {
                    Icon(actionIcon, contentDescription = actionDescription)
                }
            }
        }
    }
}

// --- Preview ---
@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun HomeScreenPreview() {
    AppTestTheme {
        HomeScreen(
            onAddToShoppingList = {},
            onItemClicked = {}
        )
    }
}