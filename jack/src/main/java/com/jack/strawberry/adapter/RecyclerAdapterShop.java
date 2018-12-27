package com.jack.strawberry.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jack.strawberry.R;
import com.jack.strawberry.StrawberryApplication;
import com.jack.strawberry.activity.LoginActivity;
import com.jack.strawberry.activity.QuestionActivity;
import com.jack.strawberry.activity.StoreActivity;
import com.jack.strawberry.greendao.StoreVODao;
import com.jack.strawberry.utils.PreferenceUtils;
import com.jack.strawberry.vo.StoreVO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RecyclerAdapterShop extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Map<String, String>> list;
    private Context mContext;
    StoreActivity activity = new StoreActivity();
    public RecyclerAdapterShop(Context context) {
        this.mContext = context;
    }

    public void setList(List<Map<String, String>> list) {
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
            Map<String, String> map = list.get(i);

            ((ViewHolder) viewHolder).name_shop.setText(map.get("name"));
            ((ViewHolder) viewHolder).score_shop.setText(map.get("score"));

            ((ViewHolder) viewHolder).cardView.setOnClickListener(v -> {
                        int score_have = PreferenceUtils.getInt(mContext, LoginActivity.SCORE);
                        int score_minus = Integer.parseInt(Objects.requireNonNull(map.get("score")));
                        if (score_have >= score_minus) {
                            StoreVODao storeVODao = StrawberryApplication.getDaoSession().getStoreVODao();
                            storeVODao.insertOrReplace(new StoreVO(null,Integer.parseInt(Objects.requireNonNull(map.get("score"))),map.get("name")));
                            PreferenceUtils.persistInt(mContext, LoginActivity.SCORE, score_have - score_minus);
                            Toast.makeText(mContext, "恭喜您~兑换成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "积分不足，请答题", Toast.LENGTH_SHORT).show();
                            showNormalDialog();
                        }
                    }
            );
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
            image_shop = itemView.findViewById(R.id.image_shop);
            name_shop = itemView.findViewById(R.id.name_shop);
            score_shop = itemView.findViewById(R.id.price_shop);
        }
    }

    private void showNormalDialog() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mContext);
        normalDialog.setIcon(R.drawable.ic_launcher_background);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("点击确定前往答题");
        normalDialog.setPositiveButton("确定", (dialog, which) ->
                mContext.startActivity(new Intent(mContext,QuestionActivity.class)));
        normalDialog.show();
    }
}
