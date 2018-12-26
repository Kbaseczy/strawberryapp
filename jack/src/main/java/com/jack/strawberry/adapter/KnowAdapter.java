package com.jack.strawberry.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jack.strawberry.R;
import com.jack.strawberry.activity.WebViewActivity;
import com.jack.strawberry.vo.KnowVO;

import java.util.List;

public class KnowAdapter extends BaseAdapter {

    private Context mContext;
    private List<KnowVO> mList;

    public KnowAdapter(Context paramContext, List<KnowVO> paramList) {
        this.mContext = paramContext;
        this.mList = paramList;
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (contentView == null) {
            contentView = View.inflate(mContext, R.layout.list_know, null);
            viewHolder = new ViewHolder();
            viewHolder.ll_container = contentView.findViewById(R.id.ll_container);
            viewHolder.tv_time = contentView.findViewById(R.id.knowTime);
            viewHolder.tv_title = contentView.findViewById(R.id.knowTitle);
            viewHolder.tv_imageView = contentView.findViewById(R.id.knowImg);

            contentView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) contentView.getTag();
        }

        final KnowVO knowVO = mList.get(position);
        viewHolder.tv_time.setText(knowVO.getKnowTime());
        viewHolder.tv_title.setText(knowVO.getKnowTitle());
        viewHolder.tv_imageView.setImageResource(knowVO.getKnowImage());
        viewHolder.ll_container.setOnClickListener(view ->
                mContext.startActivity(new Intent(mContext, WebViewActivity.class)
                        .setData(Uri.parse(knowVO.getKnowLinkUrl()))));
        return contentView;
    }

    class ViewHolder {
        private LinearLayout ll_container;
        private ImageView tv_imageView;
        private TextView tv_id;
        private TextView tv_time;
        private TextView tv_title;

        ViewHolder() {
        }
    }
}
