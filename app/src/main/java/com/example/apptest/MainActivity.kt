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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.compose.viewModel


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

    val viewModel: GroceryViewModel = viewModel()

    var selectedProduct by rememberSaveable { mutableStateOf<GroceryItem?>(null) }

    // This list holds the IDs of the items we like
    val favorites = rememberSaveable(
        saver = listSaver(
            save = { stateList -> stateList.toList() },
            restore = { it.toMutableStateList() }
        )
    ) {
        mutableStateListOf<Int>()
    }


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
                    modifier = Modifier.padding(innerPadding),
                    onAddToShoppingList = { viewModel.addToShoppingList(it) },
                    onItemClicked = {
                        viewModel.selectProduct(it)
                        currentScreen = Screen.PRODUCT_DETAILS
                    },
                    favoriteIds = viewModel.favoriteIds,
                    onToggleFavorite = { viewModel.toggleFavorite(it) }
                )
            }




            Screen.SHOPPING_LIST -> {
                ShoppingListScreen(
                    shoppingList = viewModel.shoppingList,
                    onRemoveItem = { item ->
                        viewModel.removeFromShoppingList(item)
                    },
                    onClearAll = {
                        viewModel.clearShoppingList()
                    }
                )
            }

            Screen.FAVORITES -> {
                FavoritesScreen(viewModel = viewModel())
            }

            Screen.PROFILE -> {
                ProfileScreen()
            }

            Screen.PRODUCT_DETAILS -> {

                val product = viewModel.selectedProduct
                    ?: return@Scaffold   // 👈 VIGTIG LINJE

                ProductDetailsScreen(
                    item = product,
                    isFavorite = viewModel.isFavorite(product),
                    onToggleFavorite = {
                        viewModel.toggleFavorite(product)
                    },
                    onAddToShoppingList = {
                        viewModel.addToShoppingList(product)
                    },
                    onBack = {
                        currentScreen = Screen.HOME
                    }
                )

            }

        }
    }
}
