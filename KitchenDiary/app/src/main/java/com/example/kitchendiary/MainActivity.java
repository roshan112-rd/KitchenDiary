package com.example.kitchendiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<FoodData> myFoodList;
    private DatabaseReference databaseReference;

    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    EditText txt_search;
    Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Chef Diary");

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(MainActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        txt_search = (EditText)findViewById(R.id.txt_searchText);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading items....");

        myFoodList = new ArrayList<>();

        myAdapter = new Adapter(MainActivity.this,myFoodList);
        mRecyclerView.setAdapter(myAdapter);

        databaseReference= FirebaseDatabase.getInstance().getReference("Recipe");
        progressDialog.show();

        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myFoodList.clear();
                for(DataSnapshot itemSnapshot:dataSnapshot.getChildren()){
                    FoodData foodData = itemSnapshot.getValue(FoodData.class);
                    myFoodList.add(foodData);
                }
                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Process cancelled", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }

    private void filter(String text) {
        ArrayList<FoodData> filterList = new ArrayList<>();
        for(FoodData item:myFoodList){
            if (item.getName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        myAdapter.filteredList(filterList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void btn_uploadActivity(MenuItem item) {
        startActivity(new Intent(this,LogIn.class));
    }
    boolean twice;
//    @Override

//    public void onBackPressed() {
//
//    }
}
