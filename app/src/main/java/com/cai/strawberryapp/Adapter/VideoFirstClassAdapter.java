package com.cai.strawberryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.cai.strawberryapp.VO.VideoClassVO;
import java.util.List;

public class VideoFirstClassAdapter
        extends BaseAdapter
{
    private Context mContext;
    private List<List<VideoClassVO>> mList;
    private ViewHolder viewHolder = null;

    public VideoFirstClassAdapter(Context paramContext, List<List<VideoClassVO>> paramList)
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
            paramView = LayoutInflater.from(this.mContext).inflate(2130968614, null);
            this.viewHolder = new ViewHolder();
            this.viewHolder.tv_first_class = ((TextView)paramView.findViewById(2131361959));
            paramView.setTag(this.viewHolder);
        }
        for (;;)
        {
            this.viewHolder.tv_first_class.setText(((VideoClassVO)((List)this.mList.get(paramInt)).get(0)).getVideoFirClassName());
            return paramView;
            this.viewHolder = ((ViewHolder)paramView.getTag());
        }
    }

    class ViewHolder
    {
        private TextView tv_first_class;

        ViewHolder() {}
    }
}
