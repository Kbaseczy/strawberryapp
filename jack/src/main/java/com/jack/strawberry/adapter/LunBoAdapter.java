package com.jack.strawberry.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.strawberry.R;
import com.jack.strawberry.activity.WebViewActivity;
import com.jack.strawberry.utils.Utils;
import com.jack.strawberry.vo.ImageVO;

import java.util.List;

public class LunBoAdapter extends PagerAdapter {

    public int flag = 0;
    private List<ImageVO> viewList;
    private Context context;

    public LunBoAdapter(Context context, List<ImageVO> paramList) {
        this.context = context;
        this.viewList = paramList;
    }

    public void destroyItem(ViewGroup paramViewGroup, int position, Object paramObject) {
        paramViewGroup.removeView((View) paramObject);
    }

    public int getCount() {
        return this.viewList.size();
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int position) {
        final ImageVO imageVO = viewList.get(position);

        View view = View.inflate(context, R.layout.top_image, null);
        ImageView imageView = view.findViewById(R.id.top_image);
        TextView textView = view.findViewById(R.id.top_textview);
        imageView.setImageResource(imageVO.imageResource);
        textView.setText(imageVO.desc);

        view.setOnClickListener(view1 ->
                context.startActivity(new Intent(context, WebViewActivity.class)
                        .setData(Uri.parse(imageVO.linkURL))));

        paramViewGroup.addView(view);
        return view;
    }

    public boolean isViewFromObject(View paramView, Object paramObject) {
        return paramView == paramObject;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
