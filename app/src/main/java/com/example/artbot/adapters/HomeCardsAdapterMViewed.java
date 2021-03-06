package com.example.artbot.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.artbot.R;
import com.example.artbot.model.MostLike;
import com.example.artbot.utils.LikeClick;
import com.example.artbot.utils.StringRefactor;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.artbot.utils.Links.IMAGE_BASE_URL;

public class HomeCardsAdapterMViewed extends RecyclerView.Adapter<HomeCardsAdapterMViewed.ViewHolder> {

    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onMostViewedClick(int clickedItemIndex);
    }

    private List<MostLike.Message> mostViewed;
    private Context context;
    List<Long> userLikes;

    public HomeCardsAdapterMViewed(ListItemClickListener listener) {
        mOnClickListener = listener;
        this.userLikes = userLikes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        context = parent.getContext();

        int layoutIdForListItem  = R.layout.home_card_item;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
//        holder.imageView.setImageResource(mostViewed.get(i).getImage());

        GlideApp.with(context)
                .load(IMAGE_BASE_URL + mostViewed.get(i).getImage())
                .placeholder(R.drawable.loading)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);

        //dTODO:(2) make the string more readable
        holder.mName.setText(StringRefactor.getENstring(mostViewed.get(i).getTitle()));

        if(mostViewed.get(i).isLike()){
            Log.i("Response Fav:", mostViewed.get(i).getTitle() +" ,ID :" + mostViewed.get(i).getId());
            holder.fav.setImageResource(R.drawable.ic_favorite);
        }

        holder.mDiscription.setText(mostViewed.get(i).getRate());

        holder.fav.setOnClickListener(v -> {
            //TODO:(1) Implement the love action

            Long id = mostViewed.get(holder.getAdapterPosition()).getId();
            LikeClick.loveAction(v, holder.fav, id, context );

        });
    }

    @Override
    public int getItemCount() {
        return mostViewed==null?0:mostViewed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        @BindView(R.id.image_card_view)
        ImageView imageView;
        @BindView(R.id.fav_dis)
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
            mOnClickListener.onMostViewedClick(clickPosition);
        }
    }

    public void setData(List<MostLike.Message> mostViewed) {
        this.mostViewed = mostViewed;
        notifyDataSetChanged();
    }
}
