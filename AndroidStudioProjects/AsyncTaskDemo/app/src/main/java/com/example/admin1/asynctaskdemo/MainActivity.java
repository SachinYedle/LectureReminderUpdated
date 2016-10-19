package com.example.admin1.asynctaskdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private String path;
    private ProgressBar progressBar;
    private String imageUrl = "https://i.kinja-img.com/gawker-media/image/upload/s--pEKSmwzm--/c_scale,fl_progressive,q_80,w_800/1414228815325188681.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);
        imageView = (ImageView)findViewById(R.id.image);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

    }
    public void startDownload(View view){
        Log.i("startDownload","Download started");
        new DownloadImage().execute(imageUrl);
    }
    private class DownloadImage extends AsyncTask<String,Integer,String>{

        int count;
        InputStream input;
        OutputStream output;
        int sizeOfFile;
        URL url;
        URLConnection connection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(strings[0]);
                connection = url.openConnection();
                connection.connect();
                sizeOfFile = connection.getContentLength();
                input = new BufferedInputStream(url.openStream(), 8192);
                path = getFilesDir().toString();
                output = new FileOutputStream(path+"/image.jpg");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress((int)((total*100)/sizeOfFile));
                    output.write(data, 0, count);
                }

                input.close();
                output.close();
            } catch (Exception e) {
                Log.e("Exception: ",e.getLocalizedMessage(),e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            textView.setText("done");
            path = path + "/image.jpg";
            imageView.setImageDrawable(Drawable.createFromPath(path));
            testSetText("dsad");

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);

        }
    }
    public void testSetText(String s){
        textView.setText("Hayyy"+s);
    }
}
