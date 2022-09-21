package com.example.fruitstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fruit> itemList = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://fruitstore-d9bf5-default-rtdb.firebaseio.com");
    DatabaseReference dbRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fruit Fruit1 = new Fruit("Pineapples", 1.50, R.drawable.picture1);
        //Fruit Fruit2 = new Fruit("Strawberryes", 1.70, R.drawable.picture2);
        //Fruit Fruit3 = new Fruit("Oranges", 1.0, R.drawable.picture3);
        //Fruit Fruit5 = new Fruit("Mangoes", 2.0, R.drawable.picture5);
        //Fruit Fruit6 = new Fruit("Watermelon", 2.5, R.drawable.picture6);



        RecyclerView recycler = findViewById(R.id.rv);
        recycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);

        ItemAdapter adapter = new ItemAdapter(itemList, this);
         recycler.setAdapter(adapter);


        final Query mystore = dbRef.child("Fruits");

        mystore.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot a : snapshot.getChildren()){
                    Fruit fruit = a.getValue(Fruit.class);
                    itemList.add(fruit);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}