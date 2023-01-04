package com.example.dogcare.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dogcare.R;

import com.example.dogcare.models.blogs_post_model;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class bologpost_adapter extends FirestoreRecyclerAdapter<blogs_post_model, bologpost_adapter.holder> {


    public bologpost_adapter(@NonNull FirestoreRecyclerOptions<blogs_post_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull bologpost_adapter.holder holder, int i, @NonNull blogs_post_model model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public bologpost_adapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_blog, parent, false);

        return new bologpost_adapter.holder(view);
    }

    public class holder extends RecyclerView.ViewHolder {

        Context context;
        ImageView image_view;
        TextView tittle ,calendar,discriptions;
        public holder(@NonNull View itemView) {
            super(itemView);

            context =itemView.getContext();

            tittle = itemView.findViewById(R.id.tittle);
            calendar = itemView.findViewById(R.id.calendar);
            discriptions = itemView.findViewById(R.id.discriptions);
            image_view = itemView.findViewById(R.id.image_view);



        }

        public void bind(blogs_post_model model) {

            Glide.with(context.getApplicationContext())
                    .load(model.getImageuri())
                    .into(image_view);

            tittle.setText(model.getText_tittle());
            calendar.setText("Posted @\n"+model.getCalendar());
            discriptions.setText(model.getText_discription());

        }
    }
}
