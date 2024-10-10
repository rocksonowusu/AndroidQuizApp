package com.example.doit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private EditText searchBar;
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;
    private TextView headerTextView;

    private RecyclerView mainContent;
    private ImageView profilePic, searchIcon;
    private SharedPreferences sharedPreferences;
    private DatabaseClass dbHelper;
    private int userId;
    private CardAdapter cardAdapter;
    private List<CardItem> cardItems;


    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        searchBar = findViewById(R.id.editText_search);
        searchIcon = findViewById(R.id.search_icon);
        profilePic = findViewById(R.id.imageView_profile);

        mainContent = findViewById(R.id.recyclerView_cards);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.view_pager);
        headerTextView = findViewById(R.id.headerTextView);


        // Initialize DatabaseClass
        dbHelper = new DatabaseClass(this, "QuizApp", null, 1);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("user_id", -1);

        if (userId != -1 && dbHelper != null) {
            String username = dbHelper.getUsernameById(userId);
            Bitmap profilePicture = dbHelper.getProfilePictureById(userId);

            if (username != null) {
                headerTextView.setText(getString(R.string.unlock_your_potential, username));

                if (profilePicture != null) {
                    profilePic.setImageBitmap(profilePicture);
                } else {
                    profilePic.setImageResource(R.drawable.profile_pic); // Set a default profile picture
                }

                boolean isFirstLogin = sharedPreferences.getBoolean("is_first_login", true);
                if (isFirstLogin) {
                    showProfileUpdateDialog();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("is_first_login", false);
                    editor.apply();
                }
            } else {
                headerTextView.setText("Username not found");
            }
        } else {
            headerTextView.setText("User ID not found");
        }


        // Set up the bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // Set up the view pager with fragments
        FragmentAdapter fragmentAdapter = new FragmentAdapter(this);
        fragmentAdapter.addFragment(new HighScoresFragment());
        fragmentAdapter.addFragment(new NotificationFragment());
        fragmentAdapter.addFragment(new MyaccountFragment());
        viewPager.setAdapter(fragmentAdapter);

        // Sync ViewPager with BottomNavigationView
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomNavigationView.getMenu().getItem(position + 1).setChecked(true); // +1 because home is not in ViewPager
            }
        });

        // Set the animation
        viewPager.setPageTransformer(new ZoomOutPageTransformer());

        // Initialize the search functionality
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchBar.getVisibility() == View.GONE) {
                    expandSearchBar();
                } else {
                    collapseSearchBar();
                }
            }
        });


        //Initializing the profile pic image
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideMainContent();

                // Get the userId from shared preferences
                SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                int userId = sharedPreferences.getInt("user_id", -1);

                // Check if userId is valid
                if (userId != -1) {
                    MyaccountFragment myAccountFragment = new MyaccountFragment();

                    // Pass the userId to the fragment
                    Bundle bundle = new Bundle();
                    bundle.putInt("userId", userId);
                    myAccountFragment.setArguments(bundle);

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, myAccountFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get user ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Initialize the card items and set up the RecyclerView
        cardItems = new ArrayList<>();
        cardItems.add(new CardItem(R.drawable.animals, getString(R.string.title_animals), getString(R.string.desc_animals)));
        cardItems.add(new CardItem(R.drawable.nature, getString(R.string.title_nature), getString(R.string.desc_nature)));
        cardItems.add(new CardItem(R.drawable.business, getString(R.string.title_business), getString(R.string.desc_business)));
        cardItems.add(new CardItem(R.drawable.trends, getString(R.string.title_trends), getString(R.string.desc_trends)));
        cardItems.add(new CardItem(R.drawable.space, getString(R.string.title_space), getString(R.string.desc_space)));
        cardItems.add(new CardItem(R.drawable.food, getString(R.string.title_food), getString(R.string.desc_food)));
        cardItems.add(new CardItem(R.drawable.cars, getString(R.string.title_cars), getString(R.string.desc_cars)));
       // cardItems.add(new CardItem(R.drawable.progamming, getString(R.string.title_programming), getString(R.string.desc_programming)));
        cardItems.add(new CardItem(R.drawable.fashion, getString(R.string.title_fashion), getString(R.string.desc_fashion)));
        cardItems.add(new CardItem(R.drawable.bible, getString(R.string.title_bible), getString(R.string.desc_bible)));
        cardItems.add(new CardItem(R.drawable.science, getString(R.string.title_science), getString(R.string.desc_science)));
        // Add more cards as needed

        CardAdapter cardAdapter = new CardAdapter(this, cardItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mainContent.setLayoutManager(linearLayoutManager);
        mainContent.setAdapter(cardAdapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cardAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        // Begin a fragment transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (itemId == R.id.navigation_home) {
            // Hide any visible fragments
            hideAllFragments(fragmentManager);

            // Show main content
            showMainContent();
            viewPager.setVisibility(View.GONE);
            mainContent.setVisibility(View.VISIBLE);
            return true;

        } else if (itemId == R.id.navigation_highScores) {
            // Hide any visible fragments
            hideAllFragments(fragmentManager);

            // Show the LeaderboardFragment
            hideMainContent();
            viewPager.setCurrentItem(0);
            viewPager.setVisibility(View.VISIBLE);
            fragmentTransaction.replace(R.id.fragment_container, new HighScoresFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return true;

        } else if (itemId == R.id.my_account) {
            // Hide any visible fragments
            hideAllFragments(fragmentManager);

            // Show the NotificationFragment
            viewPager.setCurrentItem(1);
            hideMainContent();
            viewPager.setVisibility(View.VISIBLE);
            fragmentTransaction.replace(R.id.fragment_container, new MyaccountFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return true;

        }

        return false;
    }

    // Method to hide all fragments
    private void hideAllFragments(FragmentManager fragmentManager) {
        // Iterate through existing fragments and hide them
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null) {
                fragmentManager.beginTransaction().hide(fragment).commit();
            }
        }
    }

    private void expandSearchBar() {
        searchBar.setVisibility(View.VISIBLE);
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(searchBar.getHeight(), View.MeasureSpec.EXACTLY);
        searchBar.measure(widthSpec, heightSpec);

        Animation searchbarAnimate = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                searchBar.getLayoutParams().width = interpolatedTime == 1
                        ? RelativeLayout.LayoutParams.WRAP_CONTENT
                        : (int) (searchBar.getMeasuredWidth() * interpolatedTime);
                searchBar.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        searchbarAnimate.setDuration(800);
        searchBar.startAnimation(searchbarAnimate);
    }

    private void collapseSearchBar() {
        final int initialWidth = searchBar.getMeasuredWidth();

        Animation searchbarCollapse = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    searchBar.setVisibility(View.GONE);
                } else {
                    searchBar.getLayoutParams().width = initialWidth - (int) (initialWidth * interpolatedTime);
                    searchBar.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        searchbarCollapse.setDuration(300);
        searchBar.startAnimation(searchbarCollapse);
    }

    private void hideMainContent() {
        headerTextView.setVisibility(View.GONE);
        mainContent.setVisibility(View.GONE);
        profilePic.setVisibility(View.GONE);
        searchBar.setVisibility(View.GONE);
        searchIcon.setVisibility(View.GONE);
    }

    private void showMainContent() {
        headerTextView.setVisibility(View.VISIBLE);
        mainContent.setVisibility(View.VISIBLE);
        bottomNavigationView.setVisibility(View.VISIBLE);
        profilePic.setVisibility(View.VISIBLE);
        searchBar.setVisibility(View.VISIBLE);
        searchIcon.setVisibility(View.VISIBLE);
    }

    private void showProfileUpdateDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Update Profile")
                .setMessage("Please update your profile details.")
                .setPositiveButton("Go to Profile", (dialog, which) -> {
                    hideMainContent();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new MyaccountFragment())
                            .addToBackStack(null)
                            .commit();
                })
                .setNegativeButton("Later", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            showMainContent();
        } else {
            super.onBackPressed();
        }
    }
}
