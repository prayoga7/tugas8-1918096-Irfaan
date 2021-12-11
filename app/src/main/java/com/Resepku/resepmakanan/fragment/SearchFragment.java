package com.Resepku.resepmakanan.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputLayout;
import com.Resepku.resepmakanan.R;
import com.Resepku.resepmakanan.activity.MealDetailActivity;
import com.Resepku.resepmakanan.adapter.MealAdapter;
import com.Resepku.resepmakanan.model.Meal;
import com.Resepku.resepmakanan.networking.APIMeal;

import java.util.ArrayList;
import java.util.Objects;

public class SearchFragment extends Fragment implements MealAdapter.OnSelected {
    private TextInputLayout inputSearch;
    private RecyclerView rvSearch;
    private ProgressBar pbSearch;
    private MealAdapter mealAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputSearch = view.findViewById(R.id.input_search);
        pbSearch = view.findViewById(R.id.pb);
        rvSearch = view.findViewById(R.id.rv);
    }

    @Override
    public void onStart() {
        super.onStart();

        mealAdapter = new MealAdapter(getContext());
        mealAdapter.setOnSelected(this);

        Objects.requireNonNull(inputSearch.getEditText()).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    pbSearch.setVisibility(View.VISIBLE);

                    APIMeal apiMeal = new APIMeal(getContext());
                    apiMeal.getMealSearch(textView.getText().toString().trim());
                    apiMeal.setOnMealListResponse(new APIMeal.OnMealListResponse() {
                        @Override
                        public void onMealList(@Nullable ArrayList<Meal> meals, @Nullable VolleyError error) {
                            if (meals != null) {
                                pbSearch.setVisibility(View.GONE);

                                mealAdapter.setMeals(meals);
                                rvSearch.setAdapter(mealAdapter);
                            }
                        }
                    });

                    hideKeyboard();
                    inputSearch.getEditText().clearFocus();
                    return true;
                }
                return false;
            }
        });
    }

    private void hideKeyboard() {
        if (getActivity() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);

            View view = getActivity().getCurrentFocus();
            if (view == null) {
                view = new View(getActivity().getApplicationContext());
            }

            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onItemSelected(Meal meal) {
        if (meal != null) {
            Intent intent = new Intent(getContext(), MealDetailActivity.class);
            intent.putExtra(MealDetailActivity.EXTRA_MEAL, meal);
            startActivity(intent);
        }
    }
}