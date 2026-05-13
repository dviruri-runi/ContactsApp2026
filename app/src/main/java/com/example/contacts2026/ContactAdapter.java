package com.example.contacts2026;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    List<Contact> contacts;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ContactAdapter() {
        super();
        contacts = new ArrayList<>();
        /*contacts.add(new Contact(R.drawable.avatar1,"Contact 1","contact1@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar2,"Contact 2","contact2@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar3,"Contact 3","contact3@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar4,"Contact 4","contact4@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar5,"Contact 5","contact5@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar6,"Contact 6","contact6@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar7,"Contact 7","contact7@gmail.com"));
        contacts.add(new Contact(R.drawable.avatar8,"Contact 8","contact8@gmail.com"));*/

        db.collection("contacts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    AddContactsToList(task.getResult());
                    notifyDataSetChanged();
                }
            }
        });
        db.collection("contacts").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                AddContactsToList(value);
                notifyDataSetChanged();
            }
        });
    }

    private void AddContactsToList(QuerySnapshot result) {
        contacts.clear();
        for (QueryDocumentSnapshot doc : result ) {
            Contact c = new Contact(doc.get("Avatar").toString(),doc.get("Name").toString(),doc.get("Email").toString(),doc.get("ID").toString());
            contacts.add(c);
        }
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
        //holder.Avatar.setImageURI(Uri.parse(contact.getAvatar()));
        Glide.with(holder.Avatar).load(contact.getAvatar()).into(holder.Avatar);
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
        Contact c = contacts.get(pos);
        db.collection("contacts").document(c.getID()).delete();
        /*
        contacts.remove(pos);
        notifyDataSetChanged();
         */
    }
}
