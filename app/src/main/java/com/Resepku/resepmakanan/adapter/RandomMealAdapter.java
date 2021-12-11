package com.Resepku.resepmakanan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.card.MaterialCardView;
import com.Resepku.resepmakanan.R;
import com.Resepku.resepmakanan.model.Meal;

import java.util.ArrayList;


public class RandomMealAdapter extends RecyclerView.Adapter<RandomMealAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Meal> meals;
    private OnSelected onSelected;

    public RandomMealAdapter(Context context) {
        this.context = context;

        meals = new ArrayList<>();
        meals.add(null);
        meals.add(null);
        meals.add(null);
        meals.add(null);
        meals.add(null);
    }

    public void setOnSelected(OnSelected onSelected) {
        this.onSelected = onSelected;
    }

    public Meal getMeals(int index) {
        return meals.get(index);
    }

    public void setMeal(Meal meal, int index) {
        meals.set(index, meal);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_random_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Meal meal = meals.get(position);

        if (meal != null) {
            Glide.with(context)
                    .load(meal.getThumb())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.icons8_no_image_64)
                    .into(holder.ivRandomMeal);

            holder.tvRandomMeal.setText(meal.getName());
            holder.mcvRandom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSelected.onItemSelected(meal);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView mcvRandom;
        ImageView ivRandomMeal;
        TextView tvRandomMeal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mcvRandom = itemView.findViewById(R.id.mcv_random);
            ivRandomMeal = itemView.findViewById(R.id.iv_random_meal);
            tvRandomMeal = itemView.findViewById(R.id.tv_random_meal);
        }
    }

    public interface OnSelected{
        void onItemSelected(Meal meal);
    }
}
