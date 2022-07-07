package com.example.retrieve_img.placesAdapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrieve_img.R;
import com.example.retrieve_img.model.img_text_model;

import java.util.ArrayList;
import java.util.List;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.myviewholder> {

  ArrayList<img_text_model> objectlist;
  Context mcontext;

    public PlaceListAdapter(Context applicationContext, ArrayList<img_text_model> objectlist) {
        this.mcontext = applicationContext;
        this.objectlist = objectlist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.independent_feed , parent , false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceListAdapter.myviewholder holder, int position) {
        holder.name.setText(objectlist.get(position).getText());
        Glide.with(mcontext).load(objectlist.get(position).getImg_URL()).fitCenter().centerCrop().into(holder.imageView);

    }



    @Override
    public int getItemCount() {
        if(this.objectlist != null)
            return this.objectlist.size();
        else
            return 0;
    }

    public static class myviewholder extends RecyclerView.ViewHolder {
      TextView name;
      ImageView imageView;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name  = itemView.findViewById(R.id.place_name);
            imageView = itemView.findViewById(R.id.place_img);
        }

    }
}
