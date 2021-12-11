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
import com.Resepku.resepmakanan.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class APICategory {
    private OnCategoryResponse onCategoryResponse;
    private RequestQueue requestQueue;

    public APICategory(Context context, OnCategoryResponse onCategoryResponse) {
        this.onCategoryResponse = onCategoryResponse;

        requestQueue = Volley.newRequestQueue(context);
    }

    public void getCategories() {
        String url = FoodRecipe.categories;

        CacheRequest cacheRequest = new CacheRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Category> categories = new ArrayList<>();
                    JSONArray arrayCategories = response.getJSONArray("categories");

                    for (int i = 0; i < arrayCategories.length(); i++) {
                        JSONObject jsonCategory = arrayCategories.getJSONObject(i);

                        categories.add(new Category(
                                jsonCategory.getString("idCategory"),
                                jsonCategory.getString("strCategory"),
                                jsonCategory.getString("strCategoryThumb"),
                                jsonCategory.getString("strCategoryDescription")
                        ));
                    }

                    onCategoryResponse.onCategoriesResponse(categories, null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onCategoryResponse.onCategoriesResponse(null, error);
            }
        });

        execute(cacheRequest);
    }

    private void execute(JsonObjectRequest jsonObjectRequest) {
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    public interface OnCategoryResponse {
        void onCategoriesResponse(@Nullable ArrayList<Category> categories, VolleyError error);
    }
}
