package com.example.doit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class QuizDetailsBottomSheet extends BottomSheetDialogFragment {

    private static final String ARG_IMAGE_RES = "image_res";
    private static final String ARG_TITLE = "title";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_CATEGORY = "title";

    public static QuizDetailsBottomSheet newInstance(int imageRes, String title, String description) {
        QuizDetailsBottomSheet fragment = new QuizDetailsBottomSheet();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_RES, imageRes);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESCRIPTION, description);
        args.putString(ARG_CATEGORY, title);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_details_bottom_sheet, container, false);

        ImageView quizImage = view.findViewById(R.id.quiz_image);
        TextView quizTitle = view.findViewById(R.id.quiz_title);
        TextView quizDescription = view.findViewById(R.id.quiz_description);
        Button takeQuizButton = view.findViewById(R.id.button_take_quiz);

        if (getArguments() != null) {
            quizImage.setImageResource(getArguments().getInt(ARG_IMAGE_RES));
            quizTitle.setText(getArguments().getString(ARG_TITLE));
            quizDescription.setText(getArguments().getString(ARG_DESCRIPTION));
        } else {
            quizTitle.setText("Quiz Title");
        }

        takeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeQuiz = new Intent(getActivity(), QuizPage.class);
                assert getArguments() != null;
                takeQuiz.putExtra("QUIZ_TITLE", getArguments().getString(ARG_TITLE));
                takeQuiz.putExtra("category", getArguments().getString(ARG_TITLE));
                startActivity(takeQuiz);
            }
        });
        return view;
    }
}
