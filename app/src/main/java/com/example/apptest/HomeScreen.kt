package com.example.apptest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptest.ui.theme.AppTestTheme
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAddToShoppingList: (GroceryItem) -> Unit,
    onItemClicked: (GroceryItem) -> Unit,
    favoriteIds: List<Int>,              // <--- 1. Receive the list of favorite IDs
    onToggleFavorite: (GroceryItem) -> Unit // <--- 2. Receive the toggle action
) {

    var searchText by rememberSaveable { mutableStateOf("") }

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
            // 3. Determine if this specific item is a favorite
            val isFavorite = favoriteIds.contains(item.id)

            GroceryItemCard(
                item = item,
                isFavorite = isFavorite, // <--- Pass the state
                onToggleFavorite = { onToggleFavorite(item) }, // <--- Pass the action
                onAddToShoppingList = { onAddToShoppingList(item) },
                onCardClick = { onItemClicked(item) }
            )
            Spacer(Modifier.height(8.dp))
        }

        item {
            Spacer(Modifier.height(16.dp))
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
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onAddToShoppingList: () -> Unit,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
    ) {
        // Main Row: Holds everything in one horizontal line
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically // <--- This forces everything to be vertically centered
        ) {

            // 1. THE IMAGE (Small)
            if (item.imageRes != null) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            } else {
                // Fallback Grey Box if no image
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingBag,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 2. TEXT (Name & Category)
            // .weight(1f) means: "Take up all the empty space in the middle"
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = item.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            // 3. RIGHT SIDE (Price + Buttons)
            // These will sit firmly on the right side
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${item.price} kr",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 4.dp)
                )

                IconButton(onClick = onToggleFavorite) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorit",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }

                IconButton(onClick = onAddToShoppingList) {
                    Icon(
                        imageVector = Icons.Default.AddShoppingCart,
                        contentDescription = "Læg i kurv"
                    )
                }
            }
        }
    }
}

// Preview
@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun HomeScreenPreview() {
    AppTestTheme {
        HomeScreen(
            onAddToShoppingList = {},
            onItemClicked = {},
            favoriteIds = listOf(1), // Preview with item 1 selected
            onToggleFavorite = {}
        )
    }
}

// Used for Favorites Screen (kept as is)
@Composable
fun GridGroceryCard(
    item: GroceryItem,
    onDeleteClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.height(200.dp)
    ) {
        Column {
            // Image / Icon Area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xFFEEEEEE)),
                contentAlignment = Alignment.Center
            ) {
                // --- NEW IMAGE LOGIC ---
                if (item.imageRes != null) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.name,
                        contentScale = ContentScale.Crop, // Makes image fill the box
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Icon(
                        Icons.Default.ShoppingBag,
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(40.dp)
                    )
                }
                // -----------------------

                // Delete Button (Keep existing logic)
                if (onDeleteClick != null) {
                    IconButton(
                        onClick = onDeleteClick,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Slet",
                            tint = Color.Red
                        )
                    }
                }

                // Price Badge (Keep existing logic)
                androidx.compose.material3.Surface(
                    color = Color(0xFF1E1E1E).copy(alpha = 0.8f),
                    shape = RoundedCornerShape(topStart = 8.dp),
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Text(
                        text = "${item.price} kr",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 12.sp
                    )
                }
            }

            // Info area (Keep existing)
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = item.name, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(text = item.category, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}