package com.meryemayar54gmail.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URL;

public class DashBoard extends AppCompatActivity {

    private Button mUploadbtn;
    private ImageView mImageView;

    private StorageReference mStorage;

    private static final int Camera_Request_Code=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mStorage= FirebaseStorage.getInstance().getReference();

       mUploadbtn =(Button) findViewById(R.id.uploadbtn);
       mImageView=(ImageView) findViewById(R.id.imageView);

       mUploadbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(intent, Camera_Request_Code);
           }
       });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Camera_Request_Code && resultCode==RESULT_OK){

            //Uri url= data.getData();
           // StorageReference filePath = mStorage.child("Photo").child(.... );



        }
    }
}
