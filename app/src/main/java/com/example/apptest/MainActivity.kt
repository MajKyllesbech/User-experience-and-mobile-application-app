package com.example.apptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.apptest.ui.theme.AppTestTheme
import com.example.apptest.BottomBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTestTheme {
                // This is the main entry point for our app
                GroceryApp()
            }
        }
    }
}

// --- This enum defines our screens ---
enum class Screen {
    WELCOME,
    HOME,
    PRODUCT_DETAILS,
    FAVORITES,
    SHOPPING_LIST,
    PROFILE
}

/**
 * This is the main composable for your app.
 * It holds the state of which screen is currently visible.
 */
@Composable
fun GroceryApp() {
    var currentScreen by rememberSaveable { mutableStateOf(Screen.WELCOME) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentScreen != Screen.WELCOME) {
                BottomBar(
                    currentScreen = currentScreen,
                    onScreenSelected = { currentScreen = it }
                )
            }
        }
    ) { innerPadding ->

        when (currentScreen) {
            Screen.WELCOME -> {
                WelcomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    onHomeClicked = {
                        currentScreen = Screen.HOME
                    }
                )
            }

            Screen.HOME -> {
                HomeScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }

            Screen.PROFILE -> {
                // ProfileScreen()
            }

            Screen.FAVORITES -> {
                // FavoritesScreen()
            }

            Screen.SHOPPING_LIST -> {
                // ShoppingListScreen()
            }

            Screen.PRODUCT_DETAILS -> {
                // ProductDetailsScreen()
            }
        }
    }
}
