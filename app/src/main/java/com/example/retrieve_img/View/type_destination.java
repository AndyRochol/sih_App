package com.example.retrieve_img.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.retrieve_img.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class type_destination extends Fragment {


    Spinner s1 , s2;
    TextView t1 , t2;
    ImageButton ib1;
    ArrayList<String> al;
    ArrayList<String> al2;
    ArrayList<String> list;
    ArrayAdapter<String> adapter , adapter2;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_type_destination, container, false);

        s1 = view.findViewById(R.id.cou_list);
        s2 = view.findViewById(R.id.state_select);
        t1 = view.findViewById(R.id.host_textView);
        t2 = view.findViewById(R.id.visitor_text);
        ib1 = view.findViewById(R.id.search_btn);

        al = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item , al);
        s1.setAdapter(adapter);

        al2 = new ArrayList<>();
        adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item , al2);
        s2.setAdapter(adapter2);
        //s1.onClick();

        databaseReference = FirebaseDatabase.getInstance().getReference("Destinations");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item : snapshot.getChildren()){
                    al.add(item.getKey());
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    al.add("India");
                    adapter.notifyDataSetChanged();
            }
        });

        databaseReference = FirebaseDatabase.getInstance("Destinations").getReference(s1.getSelectedItem().toString());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item : snapshot.getChildren()){
                    al2.add(item.getKey());
                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                al2.add("Delhi");
                adapter2.notifyDataSetChanged();

            }
        });

        recyclerView = view.findViewById(R.id.recycler_vie);
        list = new ArrayList<>();
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = FirebaseDatabase.getInstance().getReference();
            }
        });


        return view;

    }
}