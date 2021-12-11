package com.Resepku.resepmakanan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.Resepku.resepmakanan.R;

import java.util.ArrayList;


public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {
    private Context context;
    private String[] strings;

    public TagAdapter(Context context, String[] strings) {
        this.context = context;
        this.strings = strings;

        // find not null or null
        ArrayList<String> dummy = new ArrayList<>();
        for (String string : strings) {
            if (string != null) {
                if (!string.equals("null")) {
                    dummy.add(string);
                }
            }
        }

        this.strings = new String[dummy.size()];
        this.strings = dummy.toArray(this.strings);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_tag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (strings[position] != null)
            holder.chipTag.setText(strings[position]);
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        Chip chipTag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            chipTag = itemView.findViewById(R.id.chip_tag);
        }
    }
}
