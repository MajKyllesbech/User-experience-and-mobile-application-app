package com.example.apptest

// Data Model
data class GroceryItem(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    val imageRes: Int? = null // Optional: if you want to map specific images later
)

// A much longer list of mock data
val mockGroceryList = listOf(
    // Big Offer Item (Index 0)
    GroceryItem(1, "Arla - Minimælk", "Dairy", 11.95),

    // Suggested Grid Items
    GroceryItem(2, "Kims Chips", "Snacks", 18.00),
    GroceryItem(3, "Gulerødder", "Produce", 9.95),
    GroceryItem(4, "Rugbrød", "Bakery", 21.50),
    GroceryItem(5, "Hakket Oksekød", "Meat", 45.00),
    GroceryItem(6, "Bananer (Bunt)", "Produce", 12.00),
    GroceryItem(7, "Coca Cola 1.5L", "Drinks", 22.00),
    GroceryItem(8, "Æg (10 stk)", "Dairy", 24.50),
    GroceryItem(9, "Smør", "Dairy", 19.95),
    GroceryItem(10, "Pasta Penne", "Pantry", 8.50),
    GroceryItem(11, "Tomatsauce", "Pantry", 14.00),
    GroceryItem(12, "Agurk", "Produce", 6.00),
    GroceryItem(13, "Kyllingebryst", "Meat", 55.00),
    GroceryItem(14, "Appelsinjuice", "Drinks", 16.50),
    GroceryItem(15, "Havregryn", "Pantry", 10.95),
    GroceryItem(16, "Solsikkeolie", "Pantry", 18.00),
    GroceryItem(17, "Toiletpapir", "Household", 32.00),
    GroceryItem(18, "Opvasketabs", "Household", 49.00),
    GroceryItem(19, "Rødvin", "Wine", 75.00),
    GroceryItem(20, "Frosne Ærter", "Frozen", 11.00),
    GroceryItem(21, "Pizza Ristorante", "Frozen", 29.00),
    GroceryItem(22, "Chokoladekiks", "Snacks", 15.00),
    GroceryItem(23, "Kaffe (500g)", "Pantry", 54.00),
    GroceryItem(24, "Sukker", "Pantry", 12.00),
    GroceryItem(25, "Hvedemel", "Pantry", 11.00),
    GroceryItem(26, "Tandpasta", "Personal Care", 19.95),
    GroceryItem(27, "Shampoo", "Personal Care", 34.00),
    GroceryItem(28, "Hundemad", "Pets", 89.00),
    GroceryItem(29, "Kattegodbidder", "Pets", 15.00),
    GroceryItem(30, "Knækbrød", "Bakery", 14.50),
    GroceryItem(31, "Avocado (2 stk)", "Produce", 20.00),
    GroceryItem(32, "Blåbær", "Produce", 25.00)
)