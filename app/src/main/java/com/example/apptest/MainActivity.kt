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
    HOME
}

/**
 * This is the main composable for your app.
 * It holds the state of which screen is currently visible.
 */
@Composable
fun GroceryApp() {
    // This state variable tracks which screen to show.
    var currentScreen by rememberSaveable { mutableStateOf(Screen.WELCOME) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        // This 'when' statement is our navigation logic
        when (currentScreen) {
            Screen.WELCOME -> {
                // Show the WelcomeScreen (from WelcomeScreen.kt)
                WelcomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    onHomeClicked = {
                        // When the button is clicked,
                        // change the state to HOME
                        currentScreen = Screen.HOME
                    }
                )
            }
            Screen.HOME -> {
                // Show the HomeScreen (from HomeScreen.kt)
                HomeScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}