package com.Resepku.resepmakanan.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.Resepku.resepmakanan.R;
import com.Resepku.resepmakanan.adapter.MealAdapter;
import com.Resepku.resepmakanan.model.Category;
import com.Resepku.resepmakanan.model.Meal;
import com.Resepku.resepmakanan.networking.APIMeal;

import java.util.ArrayList;

public class CategoryMealActivity extends AppCompatActivity implements APIMeal.OnMealListResponse, MealAdapter.OnSelected {
    public static final String EXTRA_CATEGORY = "extra_category";
    private MaterialToolbar mb;
    private TextView tvName, tvDesc;
    private ImageView ivThumb;
    private ProgressBar pbCategory;
    private RecyclerView rvCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        mb = findViewById(R.id.mb);
        tvName = findViewById(R.id.tv_name);
        tvDesc = findViewById(R.id.tv_desc);
        ivThumb = findViewById(R.id.iv_thumb);
        pbCategory = findViewById(R.id.pb);
        rvCategory = findViewById(R.id.rv);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Category category = getIntent().getParcelableExtra(EXTRA_CATEGORY);
        if (category != null) {
            setSupportActionBar(mb);

            Glide.with(this)
                    .load(category.getThumb())
                    .placeholder(R.drawable.icons8_no_image_64)
                    .into(ivThumb);

            tvName.setText(category.getName());
            tvDesc.setText(category.getDesc());

            APIMeal apiMeal = new APIMeal(this);
            apiMeal.getMealListCategory(category.getName());
            apiMeal.setOnMealListResponse(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onMealList(@Nullable ArrayList<Meal> meals, @Nullable VolleyError error) {
        if (meals != null) {
            pbCategory.setVisibility(View.GONE);

            MealAdapter mealAdapter = new MealAdapter(this);
            mealAdapter.setMeals(meals);
            mealAdapter.setOnSelected(this);

            rvCategory.setAdapter(mealAdapter);
        }
    }

    @Override
    public void onItemSelected(Meal meal) {
        if (meal != null) {
            String id = meal.getId();

            APIMeal apiMeal = new APIMeal(this);
            apiMeal.getMeal(id);
            apiMeal.setOnMealResponse(new APIMeal.OnMealResponse() {
                @Override
                public void onMeal(@Nullable Meal meal, @Nullable VolleyError error) {
                    Intent intent = new Intent(CategoryMealActivity.this, MealDetailActivity.class);
                    intent.putExtra(MealDetailActivity.EXTRA_MEAL, meal);
                    startActivity(intent);
                }
            });
        }
    }
}