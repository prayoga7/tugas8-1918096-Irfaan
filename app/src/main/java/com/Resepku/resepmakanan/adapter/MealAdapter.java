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


public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Meal> meals;
    private OnSelected onSelected;

    public MealAdapter(Context context) {
        this.context = context;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public void setOnSelected(OnSelected onSelected) {
        this.onSelected = onSelected;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Meal meal = meals.get(position);

        Glide.with(context)
                .load(meal.getThumb())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.icons8_no_image_64)
                .into(holder.ivThumb);
        holder.tvName.setText(meal.getName());
        holder.mcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelected.onItemSelected(meal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView mcv;
        ImageView ivThumb;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mcv = itemView.findViewById(R.id.mcv);
            ivThumb = itemView.findViewById(R.id.iv_thumb);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public interface OnSelected{
        void onItemSelected(Meal meal);
    }
}
