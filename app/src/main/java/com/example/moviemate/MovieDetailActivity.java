package com.example.moviemate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {
    private final String LOG_TAG = this.getClass().getSimpleName();

    TextView nameTextView;
    TextView categoryTextView;
    ImageView imageSource;
    TextView alcoholicTextView;
    TextView recipeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle("Movie Details");
//            toolbar.setDisplayHomeAsUpEnabled(true);

            Log.i(LOG_TAG, "new toolbar title" + toolbar.getTitle());
        }

//        nameTextView = findViewById(R.id.cocktail_detail_name);
//        categoryTextView = findViewById(R.id.cocktail_detail_category);
//        imageSource = findViewById(R.id.cocktail_detail_image);
//        alcoholicTextView = findViewById(R.id.cocktail_detail_alcoholic);
//        ingredientView = findViewById(R.id.ingredient_list);
//        recipeTextView = findViewById(R.id.cocktail_detail_recipe);
    }
}