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


    val shoppingList = remember {
        mutableStateListOf<GroceryItem>()
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
                    onAddToShoppingList = { item ->
                        shoppingList.add(item)
                    },
                    onItemClicked = { item ->
                        // Pass the favorite state to the details screen so the heart is correct there too
                        val isFav = favorites.contains(item.id)
                        selectedProduct = item.copy(isFavorite = isFav)
                        currentScreen = Screen.PRODUCT_DETAILS
                    },
                    // 1. Pass the List of IDs
                    favoriteIds = favorites,
                    // 2. Define logic: Add or Remove from the list
                    onToggleFavorite = { item ->
                        if (favorites.contains(item.id)) {
                            favorites.remove(item.id)
                        } else {
                            favorites.add(item.id)
                        }
                    }
                )
            }


            Screen.PROFILE -> {
                ProfileScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }

            Screen.FAVORITES -> {
                // Filter the list to find favorite objects
                val myFavoriteItems = mockGroceryList.filter { favorites.contains(it.id) }

                FavoritesScreen(
                    favoriteItems = myFavoriteItems,
                    // 1. Define what happens when delete is clicked:
                    onRemoveItem = { itemToRemove ->
                        favorites.remove(itemToRemove.id)
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }

            Screen.SHOPPING_LIST -> {
                ShoppingListScreen(
                    shoppingList = shoppingList,
                    onRemoveItem = { item ->
                        shoppingList.remove(item)
                    }
                )
            }

            Screen.PRODUCT_DETAILS -> {
                selectedProduct?.let { product ->
                    ProductDetailsScreen(
                        item = product,
                        onAddToShoppingList = { shoppingList.add(product) },
                        onToggleFavorite = {
                            // 3. LOGIC: Actually add or remove the ID from the list
                            if (favorites.contains(product.id)) {
                                favorites.remove(product.id)
                                selectedProduct = product.copy(isFavorite = false)
                            } else {
                                favorites.add(product.id)
                                selectedProduct = product.copy(isFavorite = true)
                            }
                        },
                        onBack = { currentScreen = Screen.HOME })
                }
            }



        }
    }
}
