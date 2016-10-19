package com.example.admin1.recyclerviewmultiplelayouts;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by admin1 on 16/9/16.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static Context context;
    String []textValues;
    String []buttonValues;
    int []imageValues;
    int button = 0, text = 1, image = 2;
    MyAdapter(Context context,String[] textValues,String[] buttonValues,int []imageValues){
        this.context = context;
        this.textValues = textValues;
        this.buttonValues = buttonValues;
        this.imageValues = imageValues;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        switch(viewType){
            case 0:
                View view = inflater.inflate(R.layout.button_row,parent,false);
                ButtonViewHolder buttonViewHolder = new ButtonViewHolder(view);
                return buttonViewHolder;
            case 1:
                View view1 = inflater.inflate(R.layout.button_textview_row,parent,false);
                TextViewHolder textViewHolder = new TextViewHolder(view1);
                return textViewHolder;
            case 2:
                View view2 = inflater.inflate(R.layout.button_textvew_image,parent,false);
                ImageViewHolder imageViewHolder = new ImageViewHolder(view2);
                return imageViewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            switch (position){
                case 0:
                    ButtonViewHolder buttonViewHolder = (ButtonViewHolder)holder;
                    buttonViewHolder.button.setText(buttonValues[position]);
                    break;
                case 1:
                    TextViewHolder textViewHolder = (TextViewHolder)holder;
                    textViewHolder.button.setText(buttonValues[position]);
                    textViewHolder.textView.setText(textValues[position]);
                    break;
                default:
                    ImageViewHolder imageViewHolder = (ImageViewHolder)holder;
                    imageViewHolder.button.setText(buttonValues[position]);
                    imageViewHolder.textView.setText(textValues[position]);
                    imageViewHolder.imageView.setImageResource(imageValues[position]);
                    break;
            }
    }

    @Override
    public int getItemCount() {
        return textValues.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button button;
        public ButtonViewHolder(View itemView) {
            super(itemView);
            button = (Button)itemView.findViewById(R.id.button);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition()+1;
            Toast.makeText(context,"Button:"+position,Toast.LENGTH_SHORT).show();
        }
    }
    public static class TextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Button button;
        TextView textView;
        public TextViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.button);
            textView = (TextView) itemView.findViewById(R.id.text);
            button.setOnClickListener(this);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition()+1;
            switch (view.getId()){
                case R.id.text:
                    Toast.makeText(context,"TextView:"+position,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(context,"Button:"+position,Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Button button;
        TextView textView;
        ImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.button);
            textView = (TextView)itemView.findViewById(R.id.text);
            imageView = (ImageView)itemView.findViewById(R.id.image);
            button.setOnClickListener(this);
            textView.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition()+1;
            switch (view.getId()){
                case R.id.text:
                    Toast.makeText(context,"TextView:"+position,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.image:
                    Toast.makeText(context,"ImageView:"+position,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(context,"Button:"+position,Toast.LENGTH_SHORT).show();
            }

        }
    }

}
