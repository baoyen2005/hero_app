package com.example.booknews.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.booknews.R;
import com.example.booknews.model.entity.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HeroMainAdapter extends RecyclerView.Adapter<HeroMainAdapter.BookViewHolder> {


    private final Context mCtx;
    private  List<Hero> heroList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public HeroMainAdapter(Context mCtx, OnItemClickListener onItemClickListener) {
        this.mCtx = mCtx;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Hero hero = heroList.get(position);

        Glide.with(mCtx)
                .load(hero.getImageurl())
                .into(holder.imageView);
        holder.textView.setText(hero.getName());
        holder.constraintItem.setOnClickListener(view ->{
            onItemClickListener.onItemClick(hero,position);
        });
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Hero hero,int position);
    }
    
    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<Hero> list){
        heroList.clear();
        heroList.addAll(list);
        notifyDataSetChanged();
    }

    protected static class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        ConstraintLayout constraintItem;
        public BookViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            constraintItem = itemView.findViewById(R.id.constraintItem);
        }
    }
}
