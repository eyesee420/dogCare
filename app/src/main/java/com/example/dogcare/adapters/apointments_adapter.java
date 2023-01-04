package com.example.dogcare.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogcare.R;
import com.example.dogcare.models.apointments_model;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class apointments_adapter  extends FirestoreRecyclerAdapter<apointments_model, apointments_adapter.holder> {


    public apointments_adapter(@NonNull FirestoreRecyclerOptions<apointments_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull apointments_adapter.holder holder, int i, @NonNull apointments_model model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public apointments_adapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appointments, parent, false);

        return new apointments_adapter.holder(view);
    }

    public class holder extends RecyclerView.ViewHolder {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Context context;
        Button cancel_btn , delete_btn;
        TextView full_name ,contact,dogName,dogType,dogSymptoms,shedDate,schedTime ,status ,datetime;
        public holder(@NonNull View itemView) {
            super(itemView);

            context =itemView.getContext();

            datetime = itemView.findViewById(R.id.current_date);
            full_name = itemView.findViewById(R.id.full_name);
            contact = itemView.findViewById(R.id.contact_number);
            dogName = itemView.findViewById(R.id.dog_name);
            dogType = itemView.findViewById(R.id.dog_type);
            dogSymptoms = itemView.findViewById(R.id.dog_symptoms);
            shedDate = itemView.findViewById(R.id.shed_date);
            schedTime = itemView.findViewById(R.id.sched_time);
            status = itemView.findViewById(R.id.status);
            cancel_btn = itemView.findViewById(R.id.cancel_btn);
            delete_btn = itemView.findViewById(R.id.delete_btn);

        }

        public void bind(apointments_model model) {

            datetime.setText("Scheduled @\n" +model.getCurrentdate_time());
            full_name.setText( model.getFull_name());
            contact.setText(model.getCotntact_number());
            dogName.setText(model.getDog_name());
            dogType.setText(model.getDog_type());
            dogSymptoms.setText(model.getDog_symptoms());
            shedDate.setText(model.getSched_date());
            schedTime.setText(model.getSched_time());
            status.setText(model.getStatus());


            String cancel = "Cancelled";
            if(cancel.equals(model.getStatus())){
                cancel_btn.setVisibility(View.GONE);
            }


            cancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Map<String, Object> stats = new HashMap<>();
                    stats.put("status", "Cancelled");
                    db.collection("appointments")
                            .document(model.getUsers_id()).collection("my sched")
                            .document(model.getDocId()).update(stats).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast.makeText(context, "Application Canceled",
                                            Toast.LENGTH_SHORT).show();

                                }
                            });
                }
            });

            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.collection("appointments")
                            .document(model.getUsers_id()).collection("my sched")
                            .document(model.getDocId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(context, "Application Deleted",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });

        }
    }
}
