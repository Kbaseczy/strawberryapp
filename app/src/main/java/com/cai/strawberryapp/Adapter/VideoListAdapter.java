package com.cai.strawberryapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cai.strawberryapp.VO.VideoListVO;
import java.util.List;

public class VideoListAdapter
        extends BaseAdapter
{
    private Context mContext;
    private List<VideoListVO> mList;

    public VideoListAdapter(Context paramContext, List<VideoListVO> paramList)
    {
        this.mContext = paramContext;
        this.mList = paramList;
    }

    public int getCount()
    {
        return this.mList.size();
    }

    public Object getItem(int paramInt)
    {
        return this.mList.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
        if (paramView == null)
        {
            paramView = LayoutInflater.from(this.mContext).inflate(2130968612, null);
            paramViewGroup = new ViewHolder();
            paramViewGroup.tv_path = ((TextView)paramView.findViewById(2131361956));
            paramViewGroup.tv_title = ((TextView)paramView.findViewById(2131361955));
            paramViewGroup.imageView = ((ImageView)paramView.findViewById(2131361954));
            paramView.setTag(paramViewGroup);
        }
        for (;;)
        {
            paramViewGroup.tv_path.setText(((VideoListVO)this.mList.get(paramInt)).getVideoPath());
            paramViewGroup.tv_title.setText(((VideoListVO)this.mList.get(paramInt)).getVideoTitle());
            Bitmap localBitmap = BitmapFactory.decodeByteArray(((VideoListVO)this.mList.get(paramInt)).getVideoImage(), 0, ((VideoListVO)this.mList.get(paramInt)).getVideoImage().length);
            paramViewGroup.imageView.setImageBitmap(localBitmap);
            return paramView;
            paramViewGroup = (ViewHolder)paramView.getTag();
        }
    }

    class ViewHolder
    {
        private ImageView imageView;
        private TextView tv_path;
        private TextView tv_title;

        ViewHolder() {}
    }
}
