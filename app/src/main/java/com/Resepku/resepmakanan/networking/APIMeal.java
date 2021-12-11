package com.Resepku.resepmakanan.networking;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.Resepku.resepmakanan.model.Meal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.ArrayList;


public class APIMeal {
    private RequestQueue requestQueue;
    private OnMealResponse onMealResponse;
    private OnMealListResponse onMealListResponse;

    public APIMeal(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void setOnMealResponse(OnMealResponse onMealResponse) {
        this.onMealResponse = onMealResponse;
    }

    public void setOnMealListResponse(OnMealListResponse onMealListResponse) {
        this.onMealListResponse = onMealListResponse;
    }

    public void getRandomMeal() {
        String url = FoodRecipe.random;

        CacheRequest cacheRequest = new CacheRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("meals");

                    JSONObject jsonMeal = array.getJSONObject(0);
                    ArrayList<String> ingredients = new ArrayList<>();
                    ingredients.add(jsonMeal.getString("strIngredient1"));
                    ingredients.add(jsonMeal.getString("strIngredient2"));
                    ingredients.add(jsonMeal.getString("strIngredient3"));
                    ingredients.add(jsonMeal.getString("strIngredient4"));
                    ingredients.add(jsonMeal.getString("strIngredient5"));
                    ingredients.add(jsonMeal.getString("strIngredient6"));
                    ingredients.add(jsonMeal.getString("strIngredient7"));
                    ingredients.add(jsonMeal.getString("strIngredient8"));
                    ingredients.add(jsonMeal.getString("strIngredient9"));
                    ingredients.add(jsonMeal.getString("strIngredient10"));
                    ingredients.add(jsonMeal.getString("strIngredient11"));
                    ingredients.add(jsonMeal.getString("strIngredient12"));
                    ingredients.add(jsonMeal.getString("strIngredient13"));
                    ingredients.add(jsonMeal.getString("strIngredient14"));
                    ingredients.add(jsonMeal.getString("strIngredient15"));
                    ingredients.add(jsonMeal.getString("strIngredient16"));
                    ingredients.add(jsonMeal.getString("strIngredient17"));
                    ingredients.add(jsonMeal.getString("strIngredient18"));
                    ingredients.add(jsonMeal.getString("strIngredient19"));
                    ingredients.add(jsonMeal.getString("strIngredient20"));

                    ArrayList<String> measures = new ArrayList<>();
                    measures.add(jsonMeal.getString("strMeasure1"));
                    measures.add(jsonMeal.getString("strMeasure2"));
                    measures.add(jsonMeal.getString("strMeasure3"));
                    measures.add(jsonMeal.getString("strMeasure4"));
                    measures.add(jsonMeal.getString("strMeasure5"));
                    measures.add(jsonMeal.getString("strMeasure6"));
                    measures.add(jsonMeal.getString("strMeasure7"));
                    measures.add(jsonMeal.getString("strMeasure8"));
                    measures.add(jsonMeal.getString("strMeasure9"));
                    measures.add(jsonMeal.getString("strMeasure10"));
                    measures.add(jsonMeal.getString("strMeasure11"));
                    measures.add(jsonMeal.getString("strMeasure12"));
                    measures.add(jsonMeal.getString("strMeasure13"));
                    measures.add(jsonMeal.getString("strMeasure14"));
                    measures.add(jsonMeal.getString("strMeasure15"));
                    measures.add(jsonMeal.getString("strMeasure16"));
                    measures.add(jsonMeal.getString("strMeasure17"));
                    measures.add(jsonMeal.getString("strMeasure18"));
                    measures.add(jsonMeal.getString("strMeasure19"));
                    measures.add(jsonMeal.getString("strMeasure20"));

                    Meal meal = new Meal(
                            jsonMeal.getString("idMeal"),
                            jsonMeal.getString("strMeal"),
                            jsonMeal.getString("strCategory"),
                            jsonMeal.getString("strInstructions"),
                            jsonMeal.getString("strMealThumb"),
                            jsonMeal.getString("strTags"),
                            ingredients,
                            measures
                    );

                    onMealResponse.onMeal(meal, null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onMealResponse.onMeal(null, error);
            }
        });

        // should cache false to can be generated random meal
        cacheRequest.setShouldCache(false);
        execute(cacheRequest);
    }

    public void getMeal(String id) {
        String url = MessageFormat.format(FoodRecipe.meal, id);

        CacheRequest cacheRequest = new CacheRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("meals");

                    JSONObject jsonMeal = array.getJSONObject(0);
                    ArrayList<String> ingredients = new ArrayList<>();
                    ingredients.add(jsonMeal.getString("strIngredient1"));
                    ingredients.add(jsonMeal.getString("strIngredient2"));
                    ingredients.add(jsonMeal.getString("strIngredient3"));
                    ingredients.add(jsonMeal.getString("strIngredient4"));
                    ingredients.add(jsonMeal.getString("strIngredient5"));
                    ingredients.add(jsonMeal.getString("strIngredient6"));
                    ingredients.add(jsonMeal.getString("strIngredient7"));
                    ingredients.add(jsonMeal.getString("strIngredient8"));
                    ingredients.add(jsonMeal.getString("strIngredient9"));
                    ingredients.add(jsonMeal.getString("strIngredient10"));
                    ingredients.add(jsonMeal.getString("strIngredient11"));
                    ingredients.add(jsonMeal.getString("strIngredient12"));
                    ingredients.add(jsonMeal.getString("strIngredient13"));
                    ingredients.add(jsonMeal.getString("strIngredient14"));
                    ingredients.add(jsonMeal.getString("strIngredient15"));
                    ingredients.add(jsonMeal.getString("strIngredient16"));
                    ingredients.add(jsonMeal.getString("strIngredient17"));
                    ingredients.add(jsonMeal.getString("strIngredient18"));
                    ingredients.add(jsonMeal.getString("strIngredient19"));
                    ingredients.add(jsonMeal.getString("strIngredient20"));

                    ArrayList<String> measures = new ArrayList<>();
                    measures.add(jsonMeal.getString("strMeasure1"));
                    measures.add(jsonMeal.getString("strMeasure2"));
                    measures.add(jsonMeal.getString("strMeasure3"));
                    measures.add(jsonMeal.getString("strMeasure4"));
                    measures.add(jsonMeal.getString("strMeasure5"));
                    measures.add(jsonMeal.getString("strMeasure6"));
                    measures.add(jsonMeal.getString("strMeasure7"));
                    measures.add(jsonMeal.getString("strMeasure8"));
                    measures.add(jsonMeal.getString("strMeasure9"));
                    measures.add(jsonMeal.getString("strMeasure10"));
                    measures.add(jsonMeal.getString("strMeasure11"));
                    measures.add(jsonMeal.getString("strMeasure12"));
                    measures.add(jsonMeal.getString("strMeasure13"));
                    measures.add(jsonMeal.getString("strMeasure14"));
                    measures.add(jsonMeal.getString("strMeasure15"));
                    measures.add(jsonMeal.getString("strMeasure16"));
                    measures.add(jsonMeal.getString("strMeasure17"));
                    measures.add(jsonMeal.getString("strMeasure18"));
                    measures.add(jsonMeal.getString("strMeasure19"));
                    measures.add(jsonMeal.getString("strMeasure20"));

                    Meal meal = new Meal(
                            jsonMeal.getString("idMeal"),
                            jsonMeal.getString("strMeal"),
                            jsonMeal.getString("strCategory"),
                            jsonMeal.getString("strInstructions"),
                            jsonMeal.getString("strMealThumb"),
                            jsonMeal.getString("strTags"),
                            ingredients,
                            measures
                    );

                    onMealResponse.onMeal(meal, null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onMealResponse.onMeal(null, error);
            }
        });

        execute(cacheRequest);
    }

    public void getMealListCategory(final String category) {
        String url = MessageFormat.format(FoodRecipe.categoriesMeal, category);

        CacheRequest cacheRequest = new CacheRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Meal> meals = new ArrayList<>();
                    JSONArray array = response.getJSONArray("meals");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonMeal = array.getJSONObject(i);
                        Meal meal = new Meal(
                                jsonMeal.getString("idMeal"),
                                jsonMeal.getString("strMeal"),
                                category,
                                null,
                                jsonMeal.getString("strMealThumb"),
                                null,
                                null,
                                null
                        );

                        meals.add(meal);
                    }

                    onMealListResponse.onMealList(meals, null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onMealListResponse.onMealList(null, error);
            }
        });

        execute(cacheRequest);
    }

    public void getMealSearch(String keyword) {
        String url = MessageFormat.format(FoodRecipe.search, keyword);
        CacheRequest cacheRequest = new CacheRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Meal> meals = new ArrayList<>();
                    JSONArray array = response.getJSONArray("meals");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonMeal = array.getJSONObject(i);
                        ArrayList<String> ingredients = new ArrayList<>();
                        ingredients.add(jsonMeal.getString("strIngredient1"));
                        ingredients.add(jsonMeal.getString("strIngredient2"));
                        ingredients.add(jsonMeal.getString("strIngredient3"));
                        ingredients.add(jsonMeal.getString("strIngredient4"));
                        ingredients.add(jsonMeal.getString("strIngredient5"));
                        ingredients.add(jsonMeal.getString("strIngredient6"));
                        ingredients.add(jsonMeal.getString("strIngredient7"));
                        ingredients.add(jsonMeal.getString("strIngredient8"));
                        ingredients.add(jsonMeal.getString("strIngredient9"));
                        ingredients.add(jsonMeal.getString("strIngredient10"));
                        ingredients.add(jsonMeal.getString("strIngredient11"));
                        ingredients.add(jsonMeal.getString("strIngredient12"));
                        ingredients.add(jsonMeal.getString("strIngredient13"));
                        ingredients.add(jsonMeal.getString("strIngredient14"));
                        ingredients.add(jsonMeal.getString("strIngredient15"));
                        ingredients.add(jsonMeal.getString("strIngredient16"));
                        ingredients.add(jsonMeal.getString("strIngredient17"));
                        ingredients.add(jsonMeal.getString("strIngredient18"));
                        ingredients.add(jsonMeal.getString("strIngredient19"));
                        ingredients.add(jsonMeal.getString("strIngredient20"));

                        ArrayList<String> measures = new ArrayList<>();
                        measures.add(jsonMeal.getString("strMeasure1"));
                        measures.add(jsonMeal.getString("strMeasure2"));
                        measures.add(jsonMeal.getString("strMeasure3"));
                        measures.add(jsonMeal.getString("strMeasure4"));
                        measures.add(jsonMeal.getString("strMeasure5"));
                        measures.add(jsonMeal.getString("strMeasure6"));
                        measures.add(jsonMeal.getString("strMeasure7"));
                        measures.add(jsonMeal.getString("strMeasure8"));
                        measures.add(jsonMeal.getString("strMeasure9"));
                        measures.add(jsonMeal.getString("strMeasure10"));
                        measures.add(jsonMeal.getString("strMeasure11"));
                        measures.add(jsonMeal.getString("strMeasure12"));
                        measures.add(jsonMeal.getString("strMeasure13"));
                        measures.add(jsonMeal.getString("strMeasure14"));
                        measures.add(jsonMeal.getString("strMeasure15"));
                        measures.add(jsonMeal.getString("strMeasure16"));
                        measures.add(jsonMeal.getString("strMeasure17"));
                        measures.add(jsonMeal.getString("strMeasure18"));
                        measures.add(jsonMeal.getString("strMeasure19"));
                        measures.add(jsonMeal.getString("strMeasure20"));

                        meals.add(new Meal(
                                jsonMeal.getString("idMeal"),
                                jsonMeal.getString("strMeal"),
                                jsonMeal.getString("strCategory"),
                                jsonMeal.getString("strInstructions"),
                                jsonMeal.getString("strMealThumb"),
                                jsonMeal.getString("strTags"),
                                ingredients,
                                measures
                        ));
                    }

                    onMealListResponse.onMealList(meals, null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onMealListResponse.onMealList(null, error);
            }
        });

        execute(cacheRequest);
    }

    private void execute(JsonObjectRequest jsonObjectRequest) {
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    public interface OnMealResponse {
        void onMeal(@Nullable Meal meal, @Nullable VolleyError error);
    }

    public interface OnMealListResponse {
        void onMealList(@Nullable ArrayList<Meal> meals, @Nullable VolleyError error);
    }
}
