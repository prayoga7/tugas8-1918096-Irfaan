package com.Resepku.resepmakanan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.Resepku.resepmakanan.R;

import java.util.ArrayList;
import java.util.Collections;


public class InstructionAdapter extends RecyclerView.Adapter<InstructionAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> strings;
    private OnSelected onSelected;

    public InstructionAdapter(Context context, String string) {
        this.context = context;

        // make ordered number instruction
        strings = new ArrayList<>();
        ArrayList<String> tempStrings = new ArrayList<>();
        Collections.addAll(tempStrings, string.split("\r\n"));

        // karena ada beberapa meal yang \r\n duplikasi maka harus mencari item yang tidak kosong
        int count = 1;
        for (int i = 0; i < tempStrings.size(); i++) {
            if (!tempStrings.get(i).isEmpty()) {
                strings.add(count + ". " + tempStrings.get(i));
                count++;
            }
        }
    }

    public void setOnSelected(OnSelected onSelected) {
        this.onSelected = onSelected;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_instruction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tvInstruction.setText(strings.get(position));
        holder.mcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mcv.setChecked(true);
                onSelected.onItemSelected(getItemCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView mcv;
        TextView tvInstruction;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mcv = itemView.findViewById(R.id.mcv);
            tvInstruction = itemView.findViewById(R.id.tv_instruction);
        }
    }

    public interface OnSelected{
        void onItemSelected(int itemCount);
    }
}
