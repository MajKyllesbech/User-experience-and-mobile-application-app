package com.example.apptest

// Data Model
data class GroceryItem(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    val description: String = "",
    val imageRes: Int? = null
)


// Al mock data i vores app, ideelt set ville vi have reele varer igennem vores app.
val mockGroceryList = listOf(
    // Big Offer Item (Index 0)
    GroceryItem(1, "Arla - Minimælk", "Dairy", 11.95, "Arla minimælk"),

    // Suggested Grid Items
    GroceryItem(2, "Kims Chips", "Snacks", 18.00, "Kims"),
    GroceryItem(3, "Gulerødder", "Produce", 9.95, "Økologiske gulerødder"),
    GroceryItem(4, "Rugbrød", "Bakery", 21.50, "Bagerns bedste rugbrød"),
    GroceryItem(5, "Hakket Oksekød", "Meat", 45.00, "Hakket Oksekød 4-7% fedt"),
    GroceryItem(6, "Bananer (Bunt)", "Produce", 12.00, "5 bananer"),
    GroceryItem(7, "Coca Cola 1.5L", "Drinks", 22.00, "Coca Cola 1,5 L"),
    GroceryItem(8, "Æg (10 stk)", "Dairy", 24.50, "Æg fra en kylling"),
    GroceryItem(9, "Smør", "Dairy", 19.95, "Smør fra kærgaarden"),
    GroceryItem(10, "Pasta Penne", "Pantry", 8.50, "Pasta penne 500g"),
    GroceryItem(11, "Tomatsauce", "Pantry", 14.00, "Tomatsauce, til pizza mm"),
    GroceryItem(12, "Agurk", "Produce", 6.00, "Agurker"),
    GroceryItem(13, "Kyllingebryst", "Meat", 55.00, "Kyllingebryst 450g"),
    GroceryItem(14, "Appelsinjuice", "Drinks", 16.50, "Kingsway Appelsinjuice 1,5L"),
    GroceryItem(15, "Havregryn", "Pantry", 10.95, "Solgryn, havregryn"),
    GroceryItem(16, "Solsikkeolie", "Pantry", 18.00, "Solsikkeolie, 1L"),
    GroceryItem(17, "Toiletpapir", "Household", 32.00, "3 lags toiletpapir"),
    GroceryItem(18, "Opvasketabs", "Household", 49.00, "Opvasketabs til opvaskemaskine"),
    GroceryItem(19, "Rødvin", "Wine", 75.00, "Zinfandel rødvin"),
    GroceryItem(20, "Frosne Ærter", "Frozen", 11.00, "Frosne ærter i pose"),
    GroceryItem(21, "Pizza Ristorante", "Frozen", 29.00, "Frysepizza, ristorante"),
    GroceryItem(22, "Chokoladekiks", "Snacks", 15.00, "Prince chokoladekiks"),
    GroceryItem(23, "Kaffe (500g)", "Pantry", 54.00, "Pulverkaffe, 500g"),
    GroceryItem(24, "Sukker", "Pantry", 12.00,"Dansk sukker, 500g"),
    GroceryItem(25, "Hvedemel", "Pantry", 11.00, "Hvedemel, 1kg"),
    GroceryItem(26, "Tandpasta", "Personal Care", 19.95, "Colgate tandpasta 30ml"),
    GroceryItem(27, "Shampoo", "Personal Care", 34.00, "Head 'n shoulders shampoo"),
    GroceryItem(28, "Hundemad", "Pets", 89.00, "Hundemad 30kg"),
    GroceryItem(29, "Kattegodbidder", "Pets", 15.00, "Kattegodbidder, 100g"),
    GroceryItem(30, "Knækbrød", "Bakery", 14.50, "Knækbrød, groft"),
    GroceryItem(31, "Avocado (2 stk)", "Produce", 20.00, "Modne avocadoer"),
    GroceryItem(32, "Blåbær", "Produce", 25.00, "Bøllemosebær")
)