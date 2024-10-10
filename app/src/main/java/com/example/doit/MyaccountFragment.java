package com.example.doit;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MyaccountFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 100;

    private ImageView profilePic,logoutButton;
    private EditText email, fullname, address, country, telephone;

    private int userId;
    private DatabaseClass dbHelper;
    private SharedPreferences sharedPreferences;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myaccount, container, false);

        dbHelper = new DatabaseClass(requireContext(), "QuizApp", null, 1);

        profilePic = view.findViewById(R.id.profile_pic);
        fullname = view.findViewById(R.id.fullname);
        address = view.findViewById(R.id.address);
        country = view.findViewById(R.id.country);
        telephone = view.findViewById(R.id.telephone);
        email = view.findViewById(R.id.email);
        Button editButton = view.findViewById(R.id.edit_button);
        logoutButton = view.findViewById(R.id.logout);
        ImageView pickImageButton = view.findViewById(R.id.pick_image_button);

        // Initialize sharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("user_id", -1);
        email.setText(sharedPreferences.getString("email", ""));

        if (userId != -1) {
            Cursor cursor = dbHelper.getUserProfile(userId);
            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String fullnameText = cursor.getString(cursor.getColumnIndex("fullname"));
                @SuppressLint("Range") String addressText = cursor.getString(cursor.getColumnIndex("address"));
                @SuppressLint("Range") String countryText = cursor.getString(cursor.getColumnIndex("country"));
                @SuppressLint("Range") String telephoneText = cursor.getString(cursor.getColumnIndex("telephone"));
                @SuppressLint("Range") byte[] profilePicBytes = cursor.getBlob(cursor.getColumnIndex("profile_pic"));

                fullname.setText(fullnameText);
                address.setText(addressText);
                country.setText(countryText);
                telephone.setText(telephoneText);

                if (profilePicBytes != null && profilePicBytes.length > 0) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(profilePicBytes, 0, profilePicBytes.length);
                    profilePic.setImageBitmap(bitmap);
                }
                email.setEnabled(false);
            } else {
                // Show alert dialog to prompt the user to complete their profile
                showAlertToCompleteProfile();
            }
            cursor.close();
        }

        pickImageButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            } else {
                pickImage();
            }
        });

        editButton.setOnClickListener(v -> {
            String buttonText = editButton.getText().toString();
            if (buttonText.equals("Edit")) {
                editButton.setText("Save");
                enableEditing(true);
            } else {
                editButton.setText("Edit");
                enableEditing(false);
                saveProfile();
            }
        });

        logoutButton.setOnClickListener(v -> {
            // Clear SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            // Redirect to Login Activity
            Intent intent = new Intent(requireActivity(), Login.class);
            startActivity(intent);
            requireActivity().finish();
        });



        return view;
    }

    private void enableEditing(boolean enabled) {
        fullname.setEnabled(enabled);
        address.setEnabled(enabled);
        country.setEnabled(enabled);
        telephone.setEnabled(enabled);
    }

    private void saveProfile() {
        String fullnameText = fullname.getText().toString();
        String addressText = address.getText().toString();
        String countryText = country.getText().toString();
        String telephoneText = telephone.getText().toString();

        profilePic.buildDrawingCache();
        Bitmap bitmap = profilePic.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] profilePicBytes = stream.toByteArray();

        boolean result = dbHelper.updateUserProfile(userId, profilePicBytes, fullnameText, addressText, countryText, telephoneText);
        if (result) {
            Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Failed to update profile", Toast.LENGTH_SHORT).show();
        }
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), selectedImageUri);
                profilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            } else {
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showAlertToCompleteProfile() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Complete Your Profile");
        builder.setMessage("Please complete your profile information.");
        builder.setPositiveButton("OK", (dialog, which) -> {
            // Allow the user to edit their profile
            enableEditing(true);
        });
        builder.show();
    }
}
