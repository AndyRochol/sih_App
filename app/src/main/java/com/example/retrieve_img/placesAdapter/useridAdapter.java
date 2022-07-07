package com.example.retrieve_img.placesAdapter;

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
import com.example.retrieve_img.model.user_id_model;

import java.util.ArrayList;

public class useridAdapter extends RecyclerView.Adapter<useridAdapter.myviewholder> {
     Context context ;
     ArrayList<user_id_model> list;
    public useridAdapter(Context context, ArrayList<user_id_model> list) {
        this.context =context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.id_layout,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull useridAdapter.myviewholder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).fitCenter().into(holder.user_im);
        holder.name.setText(list.get(position).getName());
        holder.location.setText(list.get(position).getLocation());
        holder.status.setText(list.get(position).getAvailable());
        holder.langu.setText(list.get(position).getLanguage());
        holder.pts.setText(list.get(position).getPts());
    }


    @Override
    public int getItemCount() {

        if(this.list != null)
            return list.size();
        else
            return 0;

    }

    public static class myviewholder extends RecyclerView.ViewHolder {
        TextView name , status , location,langu ,pts;
        ImageView user_im ;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            status =  itemView.findViewById(R.id.available_status);
            location = itemView.findViewById(R.id.show_location);
            langu = itemView.findViewById(R.id.language);
            pts =  itemView.findViewById(R.id.pts_no);
            user_im =  itemView.findViewById(R.id.user_img);
        }
    }


}
