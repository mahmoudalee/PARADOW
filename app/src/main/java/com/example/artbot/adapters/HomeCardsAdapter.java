package com.example.artbot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artbot.R;
import com.example.artbot.model.Categories;
import java.util.ArrayList;

public class HomeCardsAdapter extends RecyclerView.Adapter<HomeCardsAdapter.ViewHolder> {
    final private ListItemClickListener mOnClickListener;

    ArrayList<Categories> categories;

    int flag = 0;
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public HomeCardsAdapter(ArrayList<Categories> categories , ListItemClickListener listener, int flag){
        this.categories=categories;
        this.flag = flag;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public HomeCardsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = 0;

        if (flag == 1) {
            layoutIdForListItem = R.layout.home_card_item;
        }
        else if(flag == 2)
            layoutIdForListItem = R.layout.cat_item;
        else if(flag == 3)
            layoutIdForListItem = R.layout.fav_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCardsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.imageView.setImageResource(categories.get(i).getImage());
        viewHolder.mName.setText(categories.get(i).getName());
        viewHolder.mDiscription.setText(categories.get(i).getRate());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        ImageView imageView;
        TextView mName;
        TextView mDiscription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name_course_view);
            mDiscription = itemView.findViewById(R.id.description_course_view);
            imageView = itemView.findViewById(R.id.image_course_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickPosition);
        }
    }
}
