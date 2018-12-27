package com.jack.strawberry.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jack.strawberry.R;
import com.jack.strawberry.vo.StoreVO;

import java.util.List;

public class RecyclerAdapterStore extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<StoreVO> list;

    public RecyclerAdapterStore(Context context) {
        this.mContext = context;
    }

    public void setList(List<StoreVO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder) {
            StoreVO storeVO = list.get(i);
            ((ViewHolder) viewHolder).name_shop.setText(storeVO.getName());
            ((ViewHolder) viewHolder).score_shop.setText(String.valueOf(storeVO.getScore()));
            Glide.with(mContext).load(i % 2 == 0 ? R.drawable.image13 : R.mipmap.ic_launcher).
                    into(((ViewHolder) viewHolder).image_shop);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image_shop;
        TextView name_shop, score_shop;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            name_shop = itemView.findViewById(R.id.name_shop);
            score_shop = itemView.findViewById(R.id.price_shop);
            image_shop = itemView.findViewById(R.id.image_shop);
        }
    }
}
