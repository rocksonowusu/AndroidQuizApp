package com.example.doit;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private Context context;
    private List<CardItem> cardItems;
    private List<CardItem> cardItemsFull;

    public CardAdapter(Context context, List<CardItem> cardItems) {
        this.context = context;
        this.cardItems = cardItems;
        this.cardItemsFull = new ArrayList<>(cardItems);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem currentItem = cardItems.get(position);
        holder.cardTitle.setText(currentItem.getTitle());
        holder.cardDescription.setText(currentItem.getDescription());
        holder.cardImage.setImageResource(currentItem.getImageResId());

        // Truncate description for the card view
        holder.cardDescription.setMaxLines(3);
        holder.cardDescription.setEllipsize(TextUtils.TruncateAt.END);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizDetailsBottomSheet bottomSheet = QuizDetailsBottomSheet.newInstance(
                        currentItem.getImageResId(),
                        currentItem.getTitle(),
                        currentItem.getDescription()
                );
                bottomSheet.show(((AppCompatActivity) context).getSupportFragmentManager(), "QuizDetailsBottomSheet");
            }
        });
    }

    public void filter(String text) {
        cardItems.clear();
        if (text.isEmpty()) {
            cardItems.addAll(cardItemsFull);
        } else {
            text = text.toLowerCase();
            for (CardItem item : cardItemsFull) {
                if (item.getTitle().toLowerCase().contains(text) || item.getDescription().toLowerCase().contains(text)) {
                    cardItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView cardTitle;
        public TextView cardDescription;
        public ImageView cardImage;
        public MaterialCardView cardView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardDescription = itemView.findViewById(R.id.desc_food);
            cardImage = itemView.findViewById(R.id.card_image);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
