package com.example.apptest

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomBar(
    currentScreen: Screen,
    onScreenSelected: (Screen) -> Unit
) {
    NavigationBar {
        // Hjem knap
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentScreen == Screen.HOME,
            onClick = { onScreenSelected(Screen.HOME) }
        )

        // Indkøbsliste knap
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
            label = { Text("Cart") },
            selected = currentScreen == Screen.SHOPPING_LIST,
            onClick = { onScreenSelected(Screen.SHOPPING_LIST) }
        )

        // Favoritter knap
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = currentScreen == Screen.FAVORITES,
            onClick = { onScreenSelected(Screen.FAVORITES) }
        )

        // Profil knap
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = currentScreen == Screen.PROFILE,
            onClick = { onScreenSelected(Screen.PROFILE) }
        )
    }
}