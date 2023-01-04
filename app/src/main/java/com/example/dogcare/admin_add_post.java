package com.example.dogcare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogcare.models.blogs_post_model;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class admin_add_post extends AppCompatActivity {
    ImagePicker imagePicker;
    Uri uri;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference reference = FirebaseStorage.getInstance().getReference();

    EditText post_text , tittle;
    ImageView image_view;
    Button btn_choose_image,btn_post;
    ProgressBar progress_circular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_post);


        tittle = findViewById(R.id.tittle);
        post_text = findViewById(R.id.discriptions);
        image_view = findViewById(R.id.image_view);
        btn_choose_image = findViewById(R.id.btn_choose_image);
        btn_post = findViewById(R.id.btn_post);
        progress_circular = findViewById(R.id.progress_circular);

        btn_choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            ImagePicker.Companion.with(admin_add_post.this)
                    .start();
            }
        });

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uri != null) {
                    progress_circular.setVisibility(View.VISIBLE);
                    uploadtofirebase(uri);

                } else {
                    Toast.makeText(admin_add_post.this, "Please select image", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void uploadtofirebase(Uri uri) {
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat currentDate = new SimpleDateFormat("dd/LLL/yyyy");
                        String date = currentDate.format(calendar.getTime());
                        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss a");
                        String time = currentTime.format(calendar.getTime());
                        String mytime = date + " " + time;


                        String my_tittle=  tittle.getText().toString().trim();
                        String my_discription=  post_text.getText().toString().trim();

                        String doc_id = db.collection("post").document().getId();

                        blogs_post_model blogsPostModel = new blogs_post_model(uri.toString(),my_tittle,my_discription,mytime,doc_id);



                        //  FirebaseUser userid = mAuth.getCurrentUser();
                        // String idd = db.collection("shop and products").document(userid.getUid()).collection("my shops").getId();
                        db.collection("blogs and post").document(doc_id)
                                .set(blogsPostModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });


                        progress_circular.setVisibility(View.INVISIBLE);
                        Toast.makeText(admin_add_post.this, "uploaded", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
    private String getFileExtension(Uri muri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(muri));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uri = data.getData();
        image_view.setImageURI(uri);


    }
}