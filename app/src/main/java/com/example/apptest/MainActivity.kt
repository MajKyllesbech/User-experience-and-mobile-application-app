package com.example.apptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp // <--- This fixes the 'dp' error
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.apptest.ui.theme.AppTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTestTheme {
                MainAppStructure()
            }
        }
    }
}

@Composable
fun MainAppStructure() {
    val navController = rememberNavController()

    // We use this to check which screen is currently visible
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    // Define the items for the Bottom Bar
    val items = listOf("Home", "Cart", "Favorites", "Profile")
    val icons = listOf(Icons.Default.Home, Icons.Default.ShoppingCart, Icons.Default.Favorite, Icons.Default.Person)

    Scaffold(
        bottomBar = {
            // ONLY show the bottom bar if we are NOT on the Welcome screen
            if (currentRoute != "Welcome") {
                NavigationBar {
                    items.forEachIndexed { index, screen ->
                        NavigationBarItem(
                            icon = { Icon(icons[index], contentDescription = screen) },
                            label = { Text(screen) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen } == true,
                            onClick = {
                                navController.navigate(screen) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Welcome", // Start at Welcome Screen
            modifier = Modifier.padding(innerPadding)
        ) {
            // 1. Welcome Screen Route
            composable("Welcome") {
                WelcomeScreen(
                    onHomeClicked = {
                        // When button clicked, go to Home and clear Welcome from history
                        navController.navigate("Home") {
                            popUpTo("Welcome") { inclusive = true }
                        }
                    }
                )
            }

            // 2. Home Screen Route
            composable("Home") {
                HomeScreen()
            }

            // 3. Placeholders for other screens (we will build these later)
            composable("Cart") { Text(text = "Cart Page", modifier = Modifier.padding(16.dp)) }
            composable("Favorites") { Text(text = "Favorites Page", modifier = Modifier.padding(16.dp)) }
            composable("Profile") { Text(text = "Profile Page", modifier = Modifier.padding(16.dp)) }
        }
    }
}