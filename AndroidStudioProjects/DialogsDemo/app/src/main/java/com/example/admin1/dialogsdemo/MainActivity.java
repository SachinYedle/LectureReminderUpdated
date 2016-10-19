package com.example.admin1.dialogsdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static Context context;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        button = (Button)findViewById(R.id.alert_dialog);
    }
    protected void onStart(){
        super.onStart();
        Log.i("method","onstart");
        Toast.makeText(getApplicationContext(),"onStart()",Toast.LENGTH_SHORT).show();
    }
    protected void onRestart(){
        super.onRestart();
        Log.i("method","onrestart");
        Toast.makeText(getApplicationContext(),"onRestart()",Toast.LENGTH_SHORT).show();
    }

    protected void onResume(){
        super.onResume();
        Log.i("method","onresume");
        Toast.makeText(getApplicationContext(),"onResume()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("method","onpause");
        Toast.makeText(getApplicationContext(),"onPause()",Toast.LENGTH_SHORT).show();
    }

    //    protected void onPause(){
//        super.onPause();
//        Log.i("method","onpause");
//        Toast.makeText(getApplicationContext(),"onPause()",Toast.LENGTH_SHORT).show();
//    }

    protected void onStop(){
        super.onStop();
        Log.i("method","onstop");
        Toast.makeText(getApplicationContext(),"onStop()",Toast.LENGTH_SHORT).show();
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i("method","ondestroy");
        Toast.makeText(getApplicationContext(),"onDestroy()",Toast.LENGTH_SHORT).show();
    }
    public void showDialog(View view){
        AlertDialogDemo alertDialogDemo = new AlertDialogDemo();
        alertDialogDemo.show(getSupportFragmentManager(),"AlertDialog");

    }

    public void showCustomDialog(View view){
        CustomDialogDemo customDialogDemo = new CustomDialogDemo();
        customDialogDemo.show(getSupportFragmentManager(),"CustomDialog");

    }
    public static class AlertDialogDemo extends DialogFragment{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Use Location");
            builder.setTitle("Location")
                    .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context,"Agree",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("DisAgree", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context,"DisAgree",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNeutralButton("Remind me later", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context,"Remind me later",Toast.LENGTH_SHORT).show();
                        }
                    });
            return builder.create();
        }
    }
    public static class CustomDialogDemo extends DialogFragment{
        EditText editText;
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            final View view = inflater.inflate(R.layout.custom_layout,null);
            builder.setView(view);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    editText = (EditText)view.findViewById(R.id.edit_text);
                    Toast.makeText(getActivity(),"Name: "+ editText.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(),"Cancel",Toast.LENGTH_SHORT).show();
                }
            });

            return builder.create();
        }
    }


}
