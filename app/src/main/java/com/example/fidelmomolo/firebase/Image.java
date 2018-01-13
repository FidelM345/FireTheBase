package com.example.fidelmomolo.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Image extends AppCompatActivity {
    Button image_selector;
    private  static final int GALLERY_INTENT=2;
    StorageReference storageReference;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        image_selector=findViewById(R.id.image_selector);


        progressDialog=new ProgressDialog(this);


        //used to get the storage reference i.e connection to the storage url is automatically determined in the json file
        storageReference= FirebaseStorage.getInstance().getReference();

        image_selector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");// specifies it to select images only and nothing else

                startActivityForResult(intent,GALLERY_INTENT);



            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(Image.this,"can work",Toast.LENGTH_LONG).show();

        if (requestCode==GALLERY_INTENT){

            Toast.makeText(Image.this,"can work",Toast.LENGTH_LONG).show();

            progressDialog.setMessage("uploading...");
            progressDialog.show();



            Uri uri=data.getData();

            StorageReference filepath=storageReference.child("Photos").child(uri.getLastPathSegment());

            //put file on the database storage
           filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   Toast.makeText(Image.this,"upload done",Toast.LENGTH_LONG).show();
                   progressDialog.dismiss();
               }
           });

        }
    }
}
