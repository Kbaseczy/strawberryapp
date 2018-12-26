package com.cai.strawberryapp.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cai.strawberryapp.VO.VideoThreeClassVO;
import java.util.List;

public class VideoThreeClassAdapter
        extends BaseAdapter
{
    private Context mContext;
    private List<VideoThreeClassVO> mList;
    private ViewHolder viewHolder = null;

    public VideoThreeClassAdapter(Context paramContext, List<VideoThreeClassVO> paramList)
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
            paramView = LayoutInflater.from(this.mContext).inflate(2130968601, null);
            this.viewHolder = new ViewHolder();
            this.viewHolder.tv_three_class = ((TextView)paramView.findViewById(2131361910));
            this.viewHolder.tv_three_class_id = ((TextView)paramView.findViewById(2131361909));
            this.viewHolder.iv_three_class = ((ImageView)paramView.findViewById(2131361908));
            paramView.setTag(this.viewHolder);
        }
        for (;;)
        {
            this.viewHolder.tv_three_class.setText(((VideoThreeClassVO)this.mList.get(paramInt)).getVideoThreeClassName());
            this.viewHolder.tv_three_class_id.setText(((VideoThreeClassVO)this.mList.get(paramInt)).getVideoThreeClassId());
            paramViewGroup = BitmapFactory.decodeByteArray(((VideoThreeClassVO)this.mList.get(paramInt)).getVideoThreeClassImg(), 0, ((VideoThreeClassVO)this.mList.get(paramInt)).getVideoThreeClassImg().length);
            this.viewHolder.iv_three_class.setImageBitmap(paramViewGroup);
            return paramView;
            this.viewHolder = ((ViewHolder)paramView.getTag());
        }
    }

    class ViewHolder
    {
        private ImageView iv_three_class;
        private TextView tv_three_class;
        private TextView tv_three_class_id;

        ViewHolder() {}
    }
}
