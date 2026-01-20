package com.example.contacts2026;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    List<Contact> contacts;

    public ContactAdapter() {
        super();
        contacts = new ArrayList<>();
        contacts.add(new Contact(R.drawable.avatar1,"Contact 1","contact1@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar2,"Contact 2","contact2@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar3,"Contact 3","contact3@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar4,"Contact 4","contact4@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar5,"Contact 5","contact5@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar6,"Contact 6","contact6@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar7,"Contact 7","contact7@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar8,"Contact 8","contact8@gmail.com"));
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact,parent,false);
        ContactViewHolder viewHolder = new ContactViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.Avatar.setImageResource(contact.getAvatar());
        holder.Name.setText(contact.getName());
        holder.Email.setText(contact.getEmail());
        holder.Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ContactActivity.class);
                intent.putExtra("contact",contact);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) view.getContext(),
                        holder.Card,
                        "cardAnimation"
                );
                view.getContext().startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void AddContact(Contact contact) {
        contacts.add(contact);
        notifyDataSetChanged();
    }

    public void DeleteContact(int pos) {
        contacts.remove(pos);
        notifyDataSetChanged();
    }
}
