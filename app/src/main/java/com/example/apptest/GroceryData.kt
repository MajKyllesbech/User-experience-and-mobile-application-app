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
    GroceryItem(1, "Arla - Minimælk", "Dairy", 11.95, "Bag det grønne 24-timers mærke banker et hjerte for frisk mælk og dyrevelfærd. Mælken leveres inden for 24 timer fra gård til butik og vores køer græsser udenfor om sommeren og går frit i staldene om vinteren. Arla 24 – Bedre dyrevelfærd året rundt. Arla® 24 Minimælk har en fedtprocent på kun 0,4%. Minimælken er mærket med det grønne nøglehul, der guider til et sundere valg. Arla® 24 findes også som skummet-, let- og sødmælk.",imageRes = R.drawable.arla_minimaelk),

    // Suggested Grid Items
    GroceryItem(2, "Kims Chips", "Snacks", 18.00, "Kims",imageRes = R.drawable.kims_chips),
    GroceryItem(3, "Gulerødder", "Produce", 9.95, "Økologiske gulerødder", imageRes = R.drawable.gulerodder),
    GroceryItem(4, "Rugbrød", "Bakery", 21.50, "Bagerns bedste rugbrød",imageRes = R.drawable.rugbroed),
    GroceryItem(5, "Hakket Oksekød", "Meat", 45.00, "Hakket Oksekød 4-7% fedt",imageRes = R.drawable.hakketoksekod),
    GroceryItem(6, "Bananer (Bunt)", "Produce", 12.00, "5 bananer",imageRes = R.drawable.bananer),
    GroceryItem(7, "Coca Cola 1.5L", "Drinks", 22.00, "Coca Cola 1,5 L",imageRes = R.drawable.cocacola),
    GroceryItem(8, "Æg (10 stk)", "Dairy", 24.50, "Æg fra en kylling",imageRes = R.drawable.aeg),
    GroceryItem(9, "Smør", "Dairy", 19.95, "Smør fra kærgaarden",imageRes = R.drawable.kaeraarden),
    GroceryItem(10, "Pasta Penne", "Pantry", 8.50, "Pasta penne 500g",imageRes = R.drawable.pastapenne),
    GroceryItem(11, "Tomatsauce", "Pantry", 14.00, "Tomatsauce, til pizza mm",imageRes = R.drawable.tomatosauce),
    GroceryItem(12, "Agurk", "Produce", 6.00, "Agurker",imageRes = R.drawable.agurk),
    GroceryItem(13, "Kyllingebryst", "Meat", 55.00, "Kyllingebryst 450g",imageRes = R.drawable.kyllingebryst),
    GroceryItem(14, "Appelsinjuice", "Drinks", 16.50, "Kingsway Appelsinjuice 1,5L",imageRes = R.drawable.appelsinjuic),
    GroceryItem(15, "Havregryn", "Pantry", 10.95, "Solgryn, havregryn",imageRes = R.drawable.solgryn),
    GroceryItem(16, "Solsikkeolie", "Pantry", 18.00, "Solsikkeolie, 1L",imageRes = R.drawable.solsikkeolie),
    GroceryItem(17, "Toiletpapir", "Household", 32.00, "3 lags toiletpapir", imageRes = R.drawable.toiletpapir),
    GroceryItem(18, "Opvasketabs", "Household", 49.00, "Opvasketabs til opvaskemaskine",imageRes = R.drawable.opvasketabs),
    GroceryItem(19, "Rødvin", "Wine", 75.00, "Zinfandel rødvin",imageRes = R.drawable.rodvin),
    GroceryItem(20, "Frosne Ærter", "Frozen", 11.00, "Frosne ærter i pose",imageRes = R.drawable.frosneearter),
    GroceryItem(21, "Pizza Ristorante", "Frozen", 29.00, "Frysepizza, ristorante",imageRes = R.drawable.pizza),
    GroceryItem(22, "Chokoladekiks", "Snacks", 15.00, "Prince chokoladekiks",imageRes = R.drawable.princechokoladekiks),
    GroceryItem(23, "Kaffe (500g)", "Pantry", 54.00, "Pulverkaffe, 500g",imageRes = R.drawable.oulverkaffe),
    GroceryItem(24, "Sukker", "Pantry", 12.00,"Dansk sukker, 500g",imageRes = R.drawable.sukker),
    GroceryItem(25, "Hvedemel", "Pantry", 11.00, "Hvedemel, 1kg", imageRes = R.drawable.hvedemel),
    GroceryItem(26, "Tandpasta", "Personal Care", 19.95, "Colgate tandpasta 30ml", imageRes = R.drawable.colgate),
    GroceryItem(27, "Shampoo", "Personal Care", 34.00, "Head 'n shoulders shampoo",imageRes = R.drawable.hvedemel),
    GroceryItem(28, "Hundemad", "Pets", 89.00, "Hundemad 30kg",imageRes = R.drawable.hundemad),
    GroceryItem(29, "Kattegodbidder", "Pets", 15.00, "Kattegodbidder, 100g",imageRes = R.drawable.kattegodbidder),
    GroceryItem(30, "Knækbrød", "Bakery", 14.50, "Knækbrød, groft",imageRes = R.drawable.knaekbrod),
    GroceryItem(31, "Avocado (2 stk)", "Produce", 20.00, "Modne avocadoer",imageRes = R.drawable.avocado),
    GroceryItem(32, "Blåbær", "Produce", 25.00, "Bøllemosebær",imageRes = R.drawable.blaabaer)
)