package com.example.retrieve_img.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrieve_img.R;
import com.example.retrieve_img.model.img_text_model;
import com.example.retrieve_img.placesAdapter.PlaceListAdapter;
import com.example.retrieve_img.viewmodel.placeListviewmodel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class Explore extends AppCompatActivity {

    CardView cv;
    ImageButton ib;
    TextView e1;
    PlaceListAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<img_text_model> objectlist;

    DatabaseReference databaseReference;
    placeListviewmodel placeList;
    BottomSheetBehavior bottomSheetBehavior;
    SearchView searchView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);


        Intent intent = getIntent();
        String string = intent.getStringExtra("country");


        cv = findViewById(R.id.card_serch);
        //searchView = findViewById(R.id.srch_design);


        recyclerView = findViewById(R.id.recycler_vie);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this , 2);
       // LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);


        objectlist = new ArrayList<>();
        clearall();

        Query query = FirebaseDatabase.getInstance().getReference().child(string);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    img_text_model imgTextModel = new img_text_model();

                    imgTextModel.setImg_URL(datasnapshot.child("id").getValue().toString());
                    imgTextModel.setText(datasnapshot.child("text").getValue().toString());

                    objectlist.add(imgTextModel);
                }


                adapter = new PlaceListAdapter(getApplicationContext(), objectlist);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {



                Toast.makeText(getApplicationContext(), "List Can`t be downloaded", Toast.LENGTH_SHORT).show();
            }
        });




        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Fragment fragment = new Fragment();
                //FragmentManager fragmentManager;
                type_destination typeDestination = new type_destination();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.explore , typeDestination).commit();

            }
        });


      /*  View view = findViewById(R.id.bottom_sheet);

        BottomSheetBehavior.from(view).addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                    case BottomSheetBehavior.STATE_EXPANDED:
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                    case BottomSheetBehavior.STATE_DRAGGING:
                    case BottomSheetBehavior.STATE_SETTLING:
                    default:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

*/


    }



    private void clearall() {
        if(objectlist != null){
            objectlist.clear();

            if(adapter != null)
                adapter.notifyDataSetChanged();
        }

    }



}