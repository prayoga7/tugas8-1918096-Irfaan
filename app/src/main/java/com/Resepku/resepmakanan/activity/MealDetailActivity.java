package com.Resepku.resepmakanan.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.Resepku.resepmakanan.R;
import com.Resepku.resepmakanan.adapter.IngredientAdapter;
import com.Resepku.resepmakanan.adapter.InstructionAdapter;
import com.Resepku.resepmakanan.adapter.TagAdapter;
import com.Resepku.resepmakanan.model.Meal;
import com.Resepku.resepmakanan.util.DatabaseFavorite;

public class MealDetailActivity extends AppCompatActivity implements View.OnClickListener, InstructionAdapter.OnSelected {
    public final static String EXTRA_MEAL = "extra_meal";
    private MaterialToolbar mb;
    private ImageView ivThumb;
    private FloatingActionButton fab;
    private RecyclerView rvTags, rvIngredients;
    private TextView tvName, tvCategory, tvInstruction;
    private ViewPager2 vp2;
    private Meal meal;
    private DatabaseFavorite databaseFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        mb = findViewById(R.id.mb);
        ivThumb = findViewById(R.id.iv_thumb);
        fab = findViewById(R.id.fab);
        tvName = findViewById(R.id.tv_name);
        tvCategory = findViewById(R.id.tv_category);
//        tvInstruction = findViewById(R.id.tv_instruction);
        vp2 = findViewById(R.id.vp2);
        rvTags = findViewById(R.id.rv_tags);
        rvIngredients = findViewById(R.id.rv_ingredients);
    }

    @Override
    protected void onStart() {
        super.onStart();

        meal = getIntent().getParcelableExtra(EXTRA_MEAL);
        if (meal != null) {
            // check is meal favorite or not
            databaseFavorite = new DatabaseFavorite(this);
            boolean isFav = databaseFavorite.isExists(meal.getId());
            int icon = isFav ? R.drawable.ic_baseline_favorite_24 : R.drawable.ic_baseline_favorite_border_24;
            fab.setImageResource(icon);

            setSupportActionBar(mb);
            fab.setOnClickListener(this);

            Glide.with(this)
                    .load(meal.getThumb())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.icons8_no_image_64)
                    .into(ivThumb);

            tvName.setText(meal.getName());
            tvCategory.setText(meal.getCategory());

//            // make ordered number instruction
//            ArrayList<String> strings = new ArrayList<>();
//            Collections.addAll(strings, meal.getInstructions().split("\r\n"));
//
//            // karena ada beberapa meal yang \r\n duplikasi maka harus mencari item yang tidak kosong
//            StringBuilder stringBuilder = new StringBuilder();
//            int count = 1;
//            for (int i = 0; i < strings.size(); i++) {
//                if (!strings.get(i).isEmpty()) {
//                    stringBuilder.append(count).append(". ").append(strings.get(i)).append("\n");
//
//                    count++;
//                }
//            }
//            tvInstruction.setText(stringBuilder.toString());

            // set tag
            TagAdapter tagAdapter = new TagAdapter(this, meal.getTags());
            rvTags.setAdapter(tagAdapter);

            // set ingredient
            IngredientAdapter ingredientAdapter = new IngredientAdapter(this, meal.getIngredients(), meal.getMeasures());
            rvIngredients.setAdapter(ingredientAdapter);

            // set instruction
            InstructionAdapter instructionAdapter = new InstructionAdapter(this, meal.getInstructions());
            instructionAdapter.setOnSelected(this);
            vp2.setAdapter(instructionAdapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onClick(View view) {
        boolean isFav = databaseFavorite.isExists(meal.getId());

        if (isFav) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete favorite meal");
            builder.setMessage("Are you sure ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    fab.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    databaseFavorite.delete(meal.getId());
                    Toast.makeText(MealDetailActivity.this, "Remove from your favorite meal", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            builder.show();
        } else {
            fab.setImageResource(R.drawable.ic_baseline_favorite_24);
            databaseFavorite.insert(meal);
            Toast.makeText(this, "Added to your favorite meal", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(int itemCount) {
        if (vp2.getCurrentItem() != (itemCount - 1)) {
            vp2.setCurrentItem(vp2.getCurrentItem() + 1);
        }
    }
}