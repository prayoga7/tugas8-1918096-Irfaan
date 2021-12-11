package com.Resepku.resepmakanan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.card.MaterialCardView;
import com.Resepku.resepmakanan.R;
import com.Resepku.resepmakanan.model.Category;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Category> categories;
    private OnSelected onSelected;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public void setOnSelected(OnSelected onSelected) {
        this.onSelected = onSelected;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Category category = categories.get(position);

        holder.tvName.setText(category.getName());
        Glide.with(context)
                .load(category.getThumb())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(ContextCompat.getDrawable(context, R.drawable.icons8_no_image_64))
                .into(holder.ivThumb);
        holder.mcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelected.onItemSelected(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView mcv;
        TextView tvName;
        ImageView ivThumb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mcv = itemView.findViewById(R.id.mcv);
            tvName = itemView.findViewById(R.id.tv_name);
            ivThumb = itemView.findViewById(R.id.iv_thumb);
        }
    }

    public interface OnSelected{
        void onItemSelected(Category category);
    }
}
