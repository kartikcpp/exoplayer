package com.kartiksaini.exoplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class movieadapter extends RecyclerView.Adapter<movieadapter.myviewholder> {
    JSONObject mjsonobject;
    Context ctx;
    LayoutInflater mlayoutinflater;
    JSONArray marray;

    movieadapter(Context ctx,JSONObject mjsonobject){
        this.ctx=ctx;
        mlayoutinflater=LayoutInflater.from(ctx);
        this.mjsonobject=mjsonobject;

        try {
            marray = mjsonobject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= mlayoutinflater.inflate(R.layout.itemlayout,parent,false);
        return new myviewholder(v,ctx);


    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        try {
            marray.get(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Glide
                .with(ctx)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.loading_spinner)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        int i=0;
        try {
            i=mjsonobject.getJSONArray("results").length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return i;
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        public myviewholder(@NonNull View itemView,Context ctx) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview);
        }


    }


}
