package com.kartiksaini.exoplayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        String imagepath="https://image.tmdb.org/t/p/w500";
        String name="";
        try {
           imagepath+= marray.getJSONObject(position).getString("poster_path");
           name=marray.getJSONObject(position).getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Glide
                .with(ctx)
                .load(imagepath)
                .placeholder(R.drawable.images)
                .centerCrop()
                .into(holder.imageView);
        holder.mtext.setText(name);

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

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final ImageView imageView;
        TextView mtext;
        public myviewholder(@NonNull View itemView,Context ctx) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview);
            mtext=itemView.findViewById(R.id.textView);
            imageView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            String desc="no description";
            int position=getLayoutPosition();
            try {

                desc = marray.getJSONObject(position).getString("overview");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent i=new Intent(ctx,MainActivity.class);
            i.putExtra("moviedesc",desc);
            ctx.startActivity(i);
        }
    }


}
