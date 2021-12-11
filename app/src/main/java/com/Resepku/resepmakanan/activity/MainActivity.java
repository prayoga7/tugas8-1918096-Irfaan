package com.Resepku.resepmakanan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.Resepku.resepmakanan.adapter.FragmentAdapter;
import com.Resepku.resepmakanan.fragment.HomeFragment;
import com.Resepku.resepmakanan.R;
import com.Resepku.resepmakanan.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 vp2;
    private BottomAppBar bap;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp2 = findViewById(R.id.vp2);
        bap = findViewById(R.id.bap);
        fab = findViewById(R.id.fab);

        setViewPager();
        setBottomAppBar();
        setRoundedBottomApp();
    }

    @Override
    protected void onStart() {
        super.onStart();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
            }
        });
    }

    private void setRoundedBottomApp() {
        MaterialShapeDrawable materialShapeDrawable = (MaterialShapeDrawable) bap.getBackground();
        materialShapeDrawable.setShapeAppearanceModel(
                materialShapeDrawable.getShapeAppearanceModel()
                        .toBuilder()
                        .setTopLeftCorner(CornerFamily.ROUNDED, 100)
                        .setTopRightCorner(CornerFamily.ROUNDED, 100)
                        .build()
        );
    }

    private void setBottomAppBar() {
        bap.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp2.setCurrentItem(0, true);
            }
        });

        bap.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search) {
                    vp2.setCurrentItem(1, true);
                    return true;
                }
                return false;
            }
        });
    }

    private void setViewPager() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());
        fragmentAdapter.add(new HomeFragment());
        fragmentAdapter.add(new SearchFragment());

        vp2.setAdapter(fragmentAdapter);
    }
}