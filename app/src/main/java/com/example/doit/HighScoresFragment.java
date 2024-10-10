package com.example.doit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HighScoresFragment extends Fragment {

    private RecyclerView recyclerView;
    private HighScoresAdapter adapter;
    private List<Category> categories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_high_score, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_high_scores);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        categories = loadCategories();
        adapter = new HighScoresAdapter(getContext(), categories);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Category> loadCategories() {
        List<Category> categories = new ArrayList<>();
        // Add your categories here with default high scores
        categories.add(new Category(R.drawable.animals, "Animals", 0));
        categories.add(new Category(R.drawable.nature, "Nature", 0));
        categories.add(new Category(R.drawable.bible,"Bible",0));
        categories.add(new Category(R.drawable.cars,"Cars",0));
        categories.add(new Category(R.drawable.science,"Science",0));
        categories.add(new Category(R.drawable.trends,"Trends",0));
        categories.add(new Category(R.drawable.business,"Business",0));
        categories.add(new Category(R.drawable.fashion,"Fashion",0));
        categories.add(new Category(R.drawable.food,"Food",0));
        categories.add(new Category(R.drawable.space,"Space",0));
        // Add more categories here...

        // Load high scores from the database and update the categories
        DatabaseClass db = new DatabaseClass(requireContext(), "QuizApp", null, 1);
        for (Category category : categories) {
            int highScore = db.getHighScoreForCategory(category.getTitle());
            category.setHighScore(highScore);
        }

        return categories;
    }
}