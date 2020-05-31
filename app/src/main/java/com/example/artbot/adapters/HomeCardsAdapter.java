package com.example.artbot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artbot.R;
import com.example.artbot.frags.HomeFragment;
import com.example.artbot.model.MostLike;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCardsAdapter extends RecyclerView.Adapter<HomeCardsAdapter.ViewHolder> {

    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    List<MostLike.Message> mostLikes;
    int flag = 0;
    String IMAGE_BASE_URL = "http://paradowme.000webhostapp.com/images/";
    Context context;

    public HomeCardsAdapter(ListItemClickListener listener, int i) {
        mOnClickListener = listener;
        flag = i;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        int layoutIdForListItem = 0;

        if (flag == 1)
            layoutIdForListItem = R.layout.home_card_item;

        else if(flag == 2)
            layoutIdForListItem = R.layout.cat_item;

        else if(flag == 3)
            layoutIdForListItem = R.layout.fav_item;


        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
//        holder.imageView.setImageResource(mostLikes.get(i).getImage());

        Glide.with(context)
                .load(IMAGE_BASE_URL + mostLikes.get(i).getImage())
                .placeholder(R.drawable.loading)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);

        //TODO:(2) make the string more readable
        holder.mName.setText(mostLikes.get(i).getTitle());

        holder.mDiscription.setText(mostLikes.get(i).getRate());

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:(1) Implement the love action
            }
        });
    }

    @Override
    public int getItemCount() {
        return mostLikes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{


        @BindView(R.id.image_h_card_view)
        ImageView imageView;
        @BindView(R.id.fav_dis)
        ImageView fav;
        @BindView(R.id.name_h_card_view)
        TextView mName;
        @BindView(R.id.description_h_card_view)
        TextView mDiscription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickPosition);
        }
    }

    public void setData(List<MostLike.Message> mostLikes) {
        this.mostLikes = mostLikes;
        notifyDataSetChanged();
    }
}
