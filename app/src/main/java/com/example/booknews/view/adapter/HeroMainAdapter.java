package com.example.booknews.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
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

public class HeroMainAdapter extends RecyclerView.Adapter<HeroMainAdapter.ViewHolder> {
    private final String TAG = "HeroMainAdapter";
    private final List<Hero> heroes = new ArrayList<>();
    private final ClickShowDetailHero clickShowDetailHero;

    public HeroMainAdapter(ClickShowDetailHero clickShowDetailHero) {
        this.clickShowDetailHero = clickShowDetailHero;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.constraintItem.getContext();
        Log.d(TAG, "onBindViewHolder: "+ heroes.size());
        Hero hero = heroes.get(position);
        Glide.with(context)
                .load(hero.getImageurl())
                .into(holder.imageView);
        holder.textView.setText(hero.getName());
        holder.constraintItem.setOnClickListener(view -> clickShowDetailHero.openDetailHero(hero, position));
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<Hero> list) {
        if (list != null && !list.isEmpty()) {
            if (!heroes.isEmpty()) heroes.clear();
            heroes.addAll(list);
            Log.d(TAG, "updateList: " + heroes.size());
            notifyDataSetChanged();
        } else {
            Log.d(TAG, "updateList: list null or heroes null");
        }
    }

    public interface ClickShowDetailHero {
        void openDetailHero(Hero hero, int position);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        private final ConstraintLayout constraintItem;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            constraintItem = itemView.findViewById(R.id.constraintItem);
        }
    }
}
