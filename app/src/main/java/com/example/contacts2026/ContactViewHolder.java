package com.example.contacts2026;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    public ImageView Avatar;
    public TextView Name;
    public TextView Email;
    public CardView Card;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        Avatar = itemView.findViewById(R.id.avatar);
        Name = itemView.findViewById(R.id.name);
        Email = itemView.findViewById(R.id.email);
        Card = itemView.findViewById(R.id.card);
    }
}
