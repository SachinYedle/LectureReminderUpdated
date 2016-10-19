package com.example.admin1.retrofitgsondemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{
    private TextView textView;
    private List<RefugeData> refugeDatas;
    private ProgressDialog dialog;
    private String url = "http://dna-dev.elasticbeanstalk.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);


        getRefuges();
    }
    public void getRefuges(){
        dialog = ProgressDialog.show(this,"Loading data","Please wait...",false,false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RefugeObjectApi api = retrofit.create(RefugeObjectApi.class);
        Call<Refuge> call = api.getRefuges();
        call.enqueue(new Callback<Refuge>() {
            @Override
            public void onResponse(Call<Refuge> call, Response<Refuge> response) {
                dialog.dismiss();
                //Log.i("Body",""+response.body());
                String refugeDetail = "\nCode: " + response.body().getCode() +
                        "\nSuccess: " + response.body().getSuccess() +
                        "\nMessage: " + response.body().getMessage();


                refugeDatas = response.body().getData();
                showData(refugeDetail);
                //Log.i("Data",""+response.body().getData());
                //textView.setText(refugeDetail);
            }

            @Override
            public void onFailure(Call<Refuge> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failure"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                Log.e("Failure",t.getLocalizedMessage());
            }
        });

    }
    public void showData(String refugeDetails){
        String data = "\n\nData: ";
        for(int i = 0;i< refugeDatas.size();i++){
            data += "\nId: " + refugeDatas.get(i).getId() +
                    "\nName: " + refugeDatas.get(i).getName() +
                    "\nItemId: " + refugeDatas.get(i).getItemid();
        }
        textView.setText(refugeDetails +""+data);
    }
}
