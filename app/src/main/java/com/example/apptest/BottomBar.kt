package com.example.apptest
//package com.example.apptest.ui.components

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
import com.example.apptest.Screen


@Composable
fun BottomBar(
    currentScreen: Screen,
    onScreenSelected: (Screen) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentScreen == Screen.HOME,
            onClick = { onScreenSelected(Screen.HOME) },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Hjem") }
        )
        NavigationBarItem(
            selected = currentScreen == Screen.FAVORITES,
            onClick = { onScreenSelected(Screen.FAVORITES) },
            icon = { Icon(Icons.Default.Favorite, null) },
            label = { Text("Favoritter") }
        )
        NavigationBarItem(
            selected = currentScreen == Screen.SHOPPING_LIST,
            onClick = { onScreenSelected(Screen.SHOPPING_LIST) },
            icon = { Icon(Icons.Default.ShoppingCart,null) },
            label = { Text("Indkøbsliste") }
        )
        NavigationBarItem(
            selected = currentScreen == Screen.PROFILE,
            onClick = { onScreenSelected(Screen.PROFILE) },
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Profil") }
        )
    }
}
