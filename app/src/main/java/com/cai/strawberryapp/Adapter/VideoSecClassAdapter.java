package com.cai.strawberryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.cai.strawberryapp.VO.VideoClassVO;
import java.util.List;

public class VideoSecClassAdapter
        extends BaseAdapter
{
    private Context mContext;
    private List<VideoClassVO> mList;

    public VideoSecClassAdapter(Context paramContext, List<VideoClassVO> paramList)
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
            paramView = LayoutInflater.from(this.mContext).inflate(2130968613, null);
            paramViewGroup = new ViewHolder();
            paramViewGroup.tv_sec_class = ((TextView)paramView.findViewById(2131361958));
            paramViewGroup.tv_sec_class_id = ((TextView)paramView.findViewById(2131361957));
            paramView.setTag(paramViewGroup);
        }
        for (;;)
        {
            paramViewGroup.tv_sec_class.setText(((VideoClassVO)this.mList.get(paramInt)).getVideoSecClassName());
            paramViewGroup.tv_sec_class_id.setText(((VideoClassVO)this.mList.get(paramInt)).getVideoSecClassId());
            return paramView;
            paramViewGroup = (ViewHolder)paramView.getTag();
        }
    }

    class ViewHolder
    {
        private TextView tv_sec_class;
        private TextView tv_sec_class_id;

        ViewHolder() {}
    }
}
