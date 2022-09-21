package com.example.fruitstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView nametext = findViewById(R.id.textViewnamed);
        TextView pricetext = findViewById(R.id.textViewpriced);
        ImageView img = findViewById(R.id.imageViewdetails);

        Bundle bundle = getIntent().getExtras();

        Fruit sentfruit =(Fruit) bundle.getSerializable("fruit");

        nametext.setText(sentfruit.getFruitName());
        pricetext.setText(sentfruit.getFruitPrice() + "KD");
        // img.setImageResource(sentfruit.getFruitPicture());
        Picasso.with(this).load(sentfruit.getFruitPicture()).into(img);
    }
}