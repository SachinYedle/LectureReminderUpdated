package com.example.admin1.camerademo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageView galleryImage;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_FROM_GALLERY = 2;
    static final int REQUEST_VIDEO_FROM_GALLERY = 23;
    static final int PERMISSION_REQUEST_CODE = 31;
    private MediaPlayer mediaPlayer;

    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.image);
        galleryImage = (ImageView)findViewById(R.id.gallery_image);
        //mediaPlayer = MediaPlayer.create(this,R.raw.champion);
        mediaPlayer = new MediaPlayer();

        videoView = (VideoView)findViewById(R.id.vide_view);
        /*Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.champion_video);
        videoView.setVideoURI(uri);
        videoView.start();
        */

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(checkPermission(MainActivity.this)) {
                    Intent intent = new Intent();
                    intent.setType("video/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select File"), REQUEST_VIDEO_FROM_GALLERY);
                }
                return false;
            }
        });

    }
    public void onClick(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void playMusic(View view){
        mediaPlayer.start();
        try {
            mediaPlayer.setDataSource("/storage/sdcard0/Sounds/Song.mp3");
            mediaPlayer.prepare();
        } catch (IOException e) {
            Log.e("IOException:",e.getLocalizedMessage(),e);
        }

        mediaPlayer.start();
    }
    public void pauseMusic(View view){
        mediaPlayer.pause();
        videoView.pause();
    }

    public void capturePicFromGallery(View view){
        if(checkPermission(MainActivity.this)) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select File"), REQUEST_IMAGE_FROM_GALLERY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_IMAGE_FROM_GALLERY && resultCode == RESULT_OK){
            Bitmap bm=null;
            if (data != null) {
                try {
                    bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            galleryImage.setImageBitmap(bm);
        }

        if(requestCode == REQUEST_VIDEO_FROM_GALLERY && resultCode == RESULT_OK){
            Uri videoUri = data.getData();
            videoView.setVideoURI(videoUri);
            videoView.start();
        }
    }

    public static boolean checkPermission(final Context context)
    {

        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"Permission Granted",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"Permission Not Granted",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
