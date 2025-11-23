package com.example.apptest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptest.ui.theme.AppTestTheme

// Define specific colors from your Figma (approximate)
val BadgeYellow = Color(0xFFFDD835)
val BadgeGreen = Color(0xFF4CAF50)
val PriceBlack = Color(0xFF1E1E1E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var searchText by rememberSaveable { mutableStateOf("") }

    // Split data: 1 big offer item, and the rest for the grid
    val offerItem = mockGroceryList.firstOrNull() // The Milk
    val suggestedItems = mockGroceryList.drop(1)  // The rest (Chips, Carrots, etc.)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Header & Search
        item {
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Dagligvarer", // Danish for "Groceries"
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(16.dp))

            // Search Bar
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Søg") }, // "Search"
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.Tune, contentDescription = "Filter") }, // Filter sliders
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = MaterialTheme.colorScheme.primary
                )
            )
        }

        // 2. "Tilbud" (Offer) Section
        item {
            SectionHeader(title = "Tilbud", actionText = "Se alt") // "Offer", "See all"

            if (offerItem != null) {
                BigOfferCard(item = offerItem)
            }
        }

        // 3. "Forslået" (Suggested) Section - Grid of 2 columns
        item {
            SectionHeader(title = "Forslået", actionText = "")
        }

        // We create rows of 2 items each manually since we are inside a LazyColumn
        items(suggestedItems.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowItems.forEach { item ->
                    GridGroceryCard(
                        item = item,
                        modifier = Modifier.weight(1f)
                    )
                }
                // If a row has only 1 item, add a spacer to keep alignment
                if (rowItems.size == 1) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }

        item { Spacer(Modifier.height(80.dp)) } // Bottom spacing for navigation bar
    }
}

// --- Component: Big Offer Card (Like the Milk in Figma) ---
@Composable
fun BigOfferCard(item: GroceryItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth().height(320.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Placeholder for Image (Replace with actual Image composable when you have assets)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFFE0E0E0)), // Grey placeholder
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Image, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(64.dp))
            }

            // "Tilbud" Badge
            Surface(
                color = BadgeYellow,
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.padding(16.dp).align(Alignment.TopStart)
            ) {
                Text(
                    text = "TILBUD",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Heart Icon
            IconButton(
                onClick = {},
                modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
            ) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorite", tint = Color.Gray)
            }

            // Eco Badge (Floating near bottom of image)
            Surface(
                color = BadgeGreen,
                shape = CircleShape,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 180.dp) // Position it overlapping image/text
            ) {
                Text(
                    text = "Øko",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontSize = 12.sp
                )
            }

            // Info Section
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = item.name, // e.g. "Arla - Minimælk"
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Økologisk", // "Organic"
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(Modifier.height(8.dp))

                // Price Pill
                Surface(
                    color = PriceBlack,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "${item.price} kr",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// --- Component: Smaller Grid Card ---
@Composable
fun GridGroceryCard(item: GroceryItem, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.height(200.dp)
    ) {
        Column {
            // Image Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xFFEEEEEE)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.ShoppingBag, contentDescription = null, tint = Color.LightGray)

                // Price Badge
                Surface(
                    color = PriceBlack.copy(alpha = 0.8f),
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

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = item.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, actionText: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        if (actionText.isNotEmpty()) {
            Text(text = actionText, color = MaterialTheme.colorScheme.primary, fontSize = 14.sp)
        }
    }
}