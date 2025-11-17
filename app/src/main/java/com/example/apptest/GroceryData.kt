package com.example.apptest

//Data Model & Mock Data
data class GroceryItem(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double
)

val mockGroceryList = listOf(
    GroceryItem(1, "Apples", "Produce", 2.99),
    GroceryItem(2, "Milk", "Dairy", 3.49),
    GroceryItem(3, "Bread", "Bakery", 2.79),
    GroceryItem(4, "Chicken", "Meat", 8.99),
    GroceryItem(5, "Cereal", "Pantry", 4.50),
    GroceryItem(6, "Eggs", "Dairy", 3.20),
    GroceryItem(7, "Bananas", "Produce", 1.50)
)