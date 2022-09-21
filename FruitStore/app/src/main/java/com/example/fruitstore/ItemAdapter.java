package com.example.fruitstore;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter {

    ArrayList<Fruit> myList = new ArrayList<>();
    Context context;

    public ItemAdapter(ArrayList<Fruit> myList, Context context) {
        this.myList = myList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item , parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }
// in the View Holder i added a new viewHolder and used v (view) and added a set on clickListener
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //((ViewHolder)holder).img.setImageResource(myList.get(position).getFruitPicture());
        Picasso.with(context).load(myList.get(position).getFruitPicture()).into(((ViewHolder)holder).img);
        ((ViewHolder)holder).name.setText(myList.get(position).getFruitName());
        ((ViewHolder)holder).price.setText(myList.get(position).getFruitPrice() + "KD");
        ((ViewHolder)holder).v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("fruit",myList.get(position));
                context.startActivity(intent);
                // in here i added an intent and getExtra, named it anything i want and moved with the options he wanted.
            }
        });
        ((ViewHolder)holder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fruit removedfruit = myList.get(position);
                int x = position;

                AlertDialog.Builder alert = new AlertDialog.Builder(context)
                        .setTitle("Attention")
                        .setMessage("Are you sure you want to delete?!")
                        .setPositiveButton("delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                myList.remove(position);
                                notifyDataSetChanged();
                                // the view in the onCLick we are writing in is the one mentioned!
                                Snackbar.make(context, view , "1 Item deleted!", 3000)
                                        .setAction("Undo", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                myList.add(x, removedfruit);
                                                notifyDataSetChanged();
                                            }
                                        }).show();


                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // we copy pasted the name from the command itself, we didnt create the name
                                dialogInterface.dismiss();
                            }
                        });
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img, delete;
        TextView name, price;
        View v;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            img = v.findViewById(R.id.imageView);
            name = v.findViewById(R.id.textView);
            price = v.findViewById(R.id.textView2);
            delete = v.findViewById(R.id.imageViewdelete);


        }
    }
}
