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
import com.example.artbot.model.MostLike;
import com.example.artbot.model.UserFav;
import com.example.artbot.utils.StringRefactor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavsAdapter extends RecyclerView.Adapter<FavsAdapter.ViewHolder> {

    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    List<UserFav> favs;
    String IMAGE_BASE_URL = "http://paradowme.000webhostapp.com/images/";
    Context context;

    public FavsAdapter(ListItemClickListener listener) {
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        int layoutIdForListItem = R.layout.fav_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
//        holder.imageView.setImageResource(mostLikes.get(i).getImage());

        Glide.with(context)
                .load(IMAGE_BASE_URL + favs.get(i).getImage())
                .placeholder(R.drawable.loading)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);

        //dTODO:(2) make the string more readable
        holder.mName.setText(StringRefactor.getENstring(favs.get(i).getTitle()));

        holder.mDiscription.setText(favs.get(i).getRate());

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:(1) Implement the love action
            }
        });
    }

    @Override
    public int getItemCount() {
        return favs==null?0:favs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{


        @BindView(R.id.image_card_view)
        ImageView imageView;

        // that 's liked
        @BindView(R.id.fav)
        ImageView fav;

        @BindView(R.id.name_card_view)
        TextView mName;
        @BindView(R.id.description_card_view)
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

    public void setData(List<UserFav> favs) {
        this.favs = favs;
        notifyDataSetChanged();
    }
}