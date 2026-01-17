package com.example.apptest

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class GroceryViewModel : ViewModel() {


    // SHOPPING LIST

    private val _shoppingList = mutableStateListOf<GroceryItem>()
    val shoppingList: List<GroceryItem> = _shoppingList

    fun addToShoppingList(item: GroceryItem) {
        _shoppingList.add(item)
    }

    fun removeFromShoppingList(item: GroceryItem) {
        _shoppingList.remove(item)
    }

    fun clearShoppingList() {
        _shoppingList.clear()
    }


    // SELECTED PRODUCT

    var selectedProduct: GroceryItem? = null
        private set

    fun selectProduct(item: GroceryItem) {
        selectedProduct = item
    }


    //  FAVORITES

    private val _favoriteIds = mutableStateListOf<Int>()
    val favoriteIds: List<Int> = _favoriteIds

    fun toggleFavorite(item: GroceryItem) {
        if (_favoriteIds.contains(item.id)) {
            _favoriteIds.remove(item.id)
        } else {
            _favoriteIds.add(item.id)
        }
    }

    fun isFavorite(item: GroceryItem): Boolean {
        return _favoriteIds.contains(item.id)
    }
}
