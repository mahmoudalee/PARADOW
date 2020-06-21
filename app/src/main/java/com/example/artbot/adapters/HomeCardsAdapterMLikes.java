package com.example.artbot.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artbot.MainActivity;
import com.example.artbot.R;
import com.example.artbot.model.LikeRes;
import com.example.artbot.model.MostLike;
import com.example.artbot.network.DataService;
import com.example.artbot.network.RetrofitInstance;
import com.example.artbot.utils.LikeClick;
import com.example.artbot.utils.StringRefactor;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeCardsAdapterMLikes extends RecyclerView.Adapter<HomeCardsAdapterMLikes.ViewHolder> {

    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onMostLikeClick(int clickedItemIndex);
    }

    List<MostLike.Message> mostLikes;
    String IMAGE_BASE_URL = "http://paradowme.000webhostapp.com/images/";
    Context context;

    public HomeCardsAdapterMLikes(ListItemClickListener listener) {
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        context = parent.getContext();

        int layoutIdForListItem =  R.layout.home_card_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
//        holder.imageView.setImageResource(mostLikes.get(i).getImage());

        GlideApp.with(context)
                .load(IMAGE_BASE_URL + mostLikes.get(i).getImage())
                .placeholder(R.drawable.loading)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);

        //dTODO:(2) make the string more readable
        holder.mName.setText(StringRefactor.getENstring(mostLikes.get(i).getTitle()));

        holder.mDiscription.setText(mostLikes.get(i).getRate());


        holder.fav.setOnClickListener(v -> {
            //TODO:(1) Implement the love action

            Long id = mostLikes.get(holder.getAdapterPosition()).getId();
            LikeClick.loveAction(v, holder.fav, id, context );

        });
    }

    @Override
    public int getItemCount() {
        return mostLikes==null?0:mostLikes.size();
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
            mOnClickListener.onMostLikeClick(clickPosition);
        }
    }

    public void setData(List<MostLike.Message> mostLikes) {
        this.mostLikes = mostLikes;
        notifyDataSetChanged();
    }

}
