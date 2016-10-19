package com.example.admin1.picassodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.image);

        Picasso.with(this)
                .load("http://1.bp.blogspot.com/-FlpE6jqIzQg/UmAq6fFgejI/AAAAAAAADko/ulj3pT0dIlg/s1600/best-nature-desktop-hd-wallpaper.jpg")
                .placeholder(R.drawable.image3)
                .error(R.drawable.image1)
                .resize(400,400)
                .into(imageView);
    }
}
