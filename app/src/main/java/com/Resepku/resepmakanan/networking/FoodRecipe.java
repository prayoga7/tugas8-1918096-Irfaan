package com.Resepku.resepmakanan.networking;


public interface FoodRecipe {
    String baseUrl = "https://www.themealdb.com/api/json/v1/1/";
    String random = baseUrl + "random.php";
    String categories = baseUrl + "categories.php";
    String categoriesMeal = baseUrl + "filter.php?c={0}";
    String meal = baseUrl + "lookup.php?i={0}";
    String search = baseUrl + "search.php?s={0}";
}
