package com.example.artbot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artbot.R;
import com.example.artbot.frags.DiscoverFragment;
import com.example.artbot.model.CategoryRes;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    List<CategoryRes.Message> categories ;
    String IMAGE_BASE_URL = "http://paradowme.000webhostapp.com/images/";
    Context context;

    public CategoriesAdapter(ListItemClickListener itemClickListener) {
        mOnClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.categories_list_item, parent, shouldAttachToParentImmediately);

        return  new CategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Glide.with(context)
                .load(IMAGE_BASE_URL + categories.get(i).getImage())
                .placeholder(R.drawable.loading)
                .error(R.mipmap.ic_launcher)
//                .transform(new BlurTransformation())
                .into(holder.imageView);

        //TODO:(2) make the string more readable
        holder.mName.setText(categories.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return categories==null?0:categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        @BindView(R.id.r_img)
        ImageView imageView;
        @BindView(R.id.r_txt)
        TextView mName;

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
    public void setData(List<CategoryRes.Message> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

}
