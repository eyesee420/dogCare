package com.example.dogcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dogcare.models.apointments_model;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class set_appointment extends AppCompatActivity implements View.OnClickListener{
    EditText selectDate,selectTime;
    ProgressBar proggress;
    private int mYear, mMonth, mDay, mHour, mMinute;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText full_name,cotntact_number,dog_name,dog_type,dog_symptoms;
    Button apply_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_appointment);

        selectDate=(EditText)findViewById(R.id.date);
        selectTime=(EditText)findViewById(R.id.time);

        selectDate.setOnClickListener(this);
        selectTime.setOnClickListener(this);

        proggress = findViewById(R.id.proggress);
        full_name = findViewById(R.id.full_name);
        cotntact_number = findViewById(R.id.cotntact_number);
        dog_name = findViewById(R.id.dog_name);
        dog_type = findViewById(R.id.dog_type);
        dog_symptoms = findViewById(R.id.dog_symptoms);
        apply_btn = findViewById(R.id.apply_btn);

        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_data();
            }
        });



    }

    private void add_data() {

        apointments_model apointmentsModel = new apointments_model();


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/LLL/yyyy");
        String date = currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss a");
        String time = currentTime.format(calendar.getTime());
        String mytime = date + " " + time;

        apointmentsModel.setFull_name(full_name.getText().toString().trim());
        apointmentsModel.setCotntact_number(cotntact_number.getText().toString().trim());
        apointmentsModel.setDog_name(dog_name.getText().toString().trim());
        apointmentsModel.setDog_type(dog_type.getText().toString().trim());
        apointmentsModel.setDog_symptoms(dog_symptoms.getText().toString().trim());
        apointmentsModel.setSched_date(selectDate.getText().toString().trim());
        apointmentsModel.setSched_time(selectTime.getText().toString().trim());
        apointmentsModel.setCurrentdate_time(mytime);
        String doc_id = db.collection("appointments").document().getId();
        apointmentsModel.setDocId(doc_id);
        apointmentsModel.setStatus("pending");
        FirebaseUser userid = mAuth.getCurrentUser();
        apointmentsModel.setUsers_id(userid.getUid());
        apointmentsModel = new apointments_model(apointmentsModel.getFull_name(),apointmentsModel.getCotntact_number(),
                apointmentsModel.getDog_name(), apointmentsModel.getDog_type(),apointmentsModel.getDog_symptoms(),
                apointmentsModel.getSched_date(),apointmentsModel.getSched_time(),apointmentsModel.getCurrentdate_time()
                ,apointmentsModel.getDocId(),apointmentsModel.getStatus(),apointmentsModel.getUsers_id());

        proggress.setVisibility(View.VISIBLE);
        db.collection("appointments").document(userid.getUid()).collection("my sched")
                .document(doc_id).set(apointmentsModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(set_appointment.this, "success", Toast.LENGTH_SHORT).show();
                proggress.setVisibility(View.GONE);
                startActivity(new Intent(set_appointment.this ,users_setAppointments_nav.class));
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {

        if (view == selectDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {


                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            selectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == selectTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String AM_PM ;
                            if(hourOfDay < 12) {
                                AM_PM = "AM";
                            } else {
                                AM_PM = "PM";
                            }
                            selectTime.setText(hourOfDay + ":" + minute + " " + AM_PM );
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}