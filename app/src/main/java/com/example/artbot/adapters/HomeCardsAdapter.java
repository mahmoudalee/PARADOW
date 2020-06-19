package com.example.artbot.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artbot.MainActivity;
import com.example.artbot.R;
import com.example.artbot.frags.HomeFragment;
import com.example.artbot.model.LikeRes;
import com.example.artbot.model.MostLike;
import com.example.artbot.network.DataService;
import com.example.artbot.network.RetrofitInstance;
import com.example.artbot.utils.LikeClick;
import com.example.artbot.utils.StringRefactor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
//        holder.imageView.setImageResource(mostLikes.get(i).getImage());

        Glide.with(context)
                .load(IMAGE_BASE_URL + mostLikes.get(i).getImage())
                .placeholder(R.drawable.loading)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);

        //dTODO:(2) make the string more readable
        holder.mName.setText(StringRefactor.getENstring(mostLikes.get(i).getTitle()));

        holder.mDiscription.setText(mostLikes.get(i).getRate());


        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:(1) Implement the love action

                ImageView likeImage = (ImageView)v;
                int imgResource = R.drawable.ic_favorite_border;

                if ( likeImage != null && likeImage.getDrawable() != null) {
                    Drawable.ConstantState constantState;

                    constantState = context.getResources()
                            .getDrawable(imgResource, context.getTheme())
                            .getConstantState();

                    if (likeImage.getDrawable().getConstantState() == constantState) {
//                            like(mostLikes.get(holder.getAdapterPosition()).getId(), MainActivity.token);
                        Log.i("Response:", mostLikes.get(holder.getAdapterPosition()).getId() +"   "+MainActivity.token);
                        holder.fav.setImageResource(R.drawable.ic_favorite);

                        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
                        Call<LikeRes> call = service.Like(mostLikes.get(holder.getAdapterPosition()).getId(), MainActivity.token);
                        call.enqueue(new Callback<LikeRes>() {
                            @Override
                            public void onResponse(Call<LikeRes> call, Response<LikeRes> response) {

                                if(response.code() == 500)
                                {
                                    Toast.makeText(context, "Error Like", Toast.LENGTH_LONG).show();
                                    holder.fav.setImageResource(R.drawable.ic_favorite_border);
                                }
                                else{

                                    Log.i("Response:", "Like done"+response.code());
                                    Toast.makeText(context, "Liked", Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<LikeRes> call, Throwable t) {

                                Toast.makeText(context, "Error Like", Toast.LENGTH_LONG).show();
                                holder.fav.setImageResource(R.drawable.ic_favorite_border);
                            }

                        });
                    }
                    else {
                        holder.fav.setImageResource(R.drawable.ic_favorite_border);

                        Toast.makeText(context, "Dislike", Toast.LENGTH_SHORT).show();
//                        LikeClick.dislike(mostLikes.get(holder.getAdapterPosition()).getId(),MainActivity.token);

                        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
                        Call<LikeRes> call = service.DisLike(mostLikes.get(holder.getAdapterPosition()).getId(), MainActivity.token);
                        call.enqueue(new Callback<LikeRes>() {
                            @Override
                            public void onResponse(Call<LikeRes> call, Response<LikeRes> response) {

                                Log.i("Response:", "DisLike done"+response.code());
                                Toast.makeText(context, "DisLike", Toast.LENGTH_LONG).show();
//                                Gson gson = new GsonBuilder().create();
//                                LikeRes mError=new LikeRes();
//                                mError= gson.fromJson(String.valueOf(response.errorBody()),LikeRes.class);
//                                Toast.makeText(context, mError.getMessage(), Toast.LENGTH_LONG).show();
//                                Log.i("Response:", mError.getMessage());
                                if (response.body().getMessage().equals("delete love successfully"))
                                {

                                    Toast.makeText(context, "DisLike Confirmed", Toast.LENGTH_SHORT).show();
//                    v.setBackground();
                                }

                            }

                            @Override
                            public void onFailure(Call<LikeRes> call, Throwable t) {

                            }

                        });
                    }
                }
//                if (like.getDrawable().getConstantState() == context.getResources().getDrawable( imgResource).getConstantState()){
//
//                }

            }
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
            mOnClickListener.onListItemClick(clickPosition);
        }
    }

    public void setData(List<MostLike.Message> mostLikes) {
        this.mostLikes = mostLikes;
        notifyDataSetChanged();
    }





    public void like(Long id, String token) {



    }
}
