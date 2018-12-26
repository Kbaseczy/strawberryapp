package com.cai.strawberryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cai.strawberryapp.VO.KnowVO;
import java.util.List;

public class KnowAdapter
        extends BaseAdapter
{
    private Context mContext;
    private List<KnowVO> mList;

    public KnowAdapter(Context paramContext, List<KnowVO> paramList)
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
            paramView = LayoutInflater.from(this.mContext).inflate(2130968609, null);
            paramViewGroup = new ViewHolder();
            paramViewGroup.tv_time = ((TextView)paramView.findViewById(2131361940));
            paramViewGroup.tv_title = ((TextView)paramView.findViewById(2131361939));
            paramViewGroup.tv_id = ((TextView)paramView.findViewById(2131361938));
            paramViewGroup.imageView = ((ImageView)paramView.findViewById(2131361937));
            paramView.setTag(paramViewGroup);
        }
        for (;;)
        {
            paramViewGroup.tv_time.setText(((KnowVO)this.mList.get(paramInt)).getKnowTime());
            paramViewGroup.tv_title.setText(((KnowVO)this.mList.get(paramInt)).getKnowTitle());
            paramViewGroup.tv_id.setText(((KnowVO)this.mList.get(paramInt)).getKnowId());
            paramViewGroup.imageView.setImageBitmap(((KnowVO)this.mList.get(paramInt)).getKnowImage());
            return paramView;
            paramViewGroup = (ViewHolder)paramView.getTag();
        }
    }

    class ViewHolder
    {
        private ImageView imageView;
        private TextView tv_id;
        private TextView tv_time;
        private TextView tv_title;

        ViewHolder() {}
    }
}
