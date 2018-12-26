package com.cai.strawberryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cai.strawberryapp.VO.LeaveQueVO;
import java.util.List;

public class LeaveListAdapter
        extends BaseAdapter
{
    private String audioPath;
    private Context mContext;
    private List<LeaveQueVO> mList;

    public LeaveListAdapter(Context paramContext, List<LeaveQueVO> paramList)
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
            paramView = LayoutInflater.from(this.mContext).inflate(2130968610, null);
            paramViewGroup = new ViewHolder();
            paramViewGroup.ll_que_list_audio = ((LinearLayout)paramView.findViewById(2131361945));
            paramViewGroup.tv_que_list_audio_time = ((TextView)paramView.findViewById(2131361947));
            paramViewGroup.but_que_list_audio = ((Button)paramView.findViewById(2131361946));
            paramViewGroup.tv_que_list_id = ((TextView)paramView.findViewById(2131361941));
            paramViewGroup.tv_que_list_user = ((TextView)paramView.findViewById(2131361942));
            paramViewGroup.tv_que_list_title = ((TextView)paramView.findViewById(2131361943));
            paramViewGroup.tv_que_list_content = ((TextView)paramView.findViewById(2131361944));
            paramViewGroup.tv_que_list_time = ((TextView)paramView.findViewById(2131361949));
            paramViewGroup.tv_leave_num = ((TextView)paramView.findViewById(2131361950));
            paramViewGroup.tv_que_list_image = ((ImageView)paramView.findViewById(2131361948));
            paramView.setTag(paramViewGroup);
            paramViewGroup.tv_que_list_id.setText(((LeaveQueVO)this.mList.get(paramInt)).getQueId());
            paramViewGroup.tv_que_list_title.setText(((LeaveQueVO)this.mList.get(paramInt)).getQueTitle());
            this.audioPath = ((LeaveQueVO)this.mList.get(paramInt)).getQueAudioPath();
            if ((((LeaveQueVO)this.mList.get(paramInt)).getQueContent() != null) && (!((LeaveQueVO)this.mList.get(paramInt)).getQueContent().equals(""))) {
                break label562;
            }
            paramViewGroup.tv_que_list_audio_time.setText(((LeaveQueVO)this.mList.get(paramInt)).getQueAudioTime());
            paramViewGroup.tv_que_list_content.setVisibility(8);
            paramViewGroup.ll_que_list_audio.setVisibility(0);
            paramViewGroup.but_que_list_audio.setText(((LeaveQueVO)this.mList.get(paramInt)).getQueAudioPath());
            paramViewGroup.but_que_list_audio.setClickable(false);
            paramViewGroup.but_que_list_audio.setBackgroundResource(2130903043);
            paramViewGroup.but_que_list_audio.setFocusable(false);
        }
        for (;;)
        {
            paramViewGroup.tv_que_list_user.setText(((LeaveQueVO)this.mList.get(paramInt)).getQueUser());
            paramViewGroup.tv_que_list_time.setText(((LeaveQueVO)this.mList.get(paramInt)).getQueTime());
            paramViewGroup.tv_leave_num.setText(((LeaveQueVO)this.mList.get(paramInt)).getLeaveNum());
            if (((LeaveQueVO)this.mList.get(paramInt)).getQueImg() == null) {
                break label605;
            }
            paramViewGroup.tv_que_list_image.setTag(((LeaveQueVO)this.mList.get(paramInt)).getQueImgPath());
            paramViewGroup.tv_que_list_image.setImageResource(2130903068);
            if ((paramViewGroup.tv_que_list_image.getTag() != null) && (paramViewGroup.tv_que_list_image.getTag().equals(((LeaveQueVO)this.mList.get(paramInt)).getQueImgPath())))
            {
                paramViewGroup.tv_que_list_image.setVisibility(0);
                paramViewGroup.tv_que_list_image.setImageBitmap(((LeaveQueVO)this.mList.get(paramInt)).getQueImg());
            }
            return paramView;
            paramViewGroup = (ViewHolder)paramView.getTag();
            break;
            label562:
            paramViewGroup.tv_que_list_content.setVisibility(0);
            paramViewGroup.ll_que_list_audio.setVisibility(8);
            paramViewGroup.tv_que_list_content.setText(((LeaveQueVO)this.mList.get(paramInt)).getQueContent());
        }
        label605:
        paramViewGroup.tv_que_list_image.setVisibility(8);
        return paramView;
    }

    class ViewHolder
    {
        private Button but_que_list_audio;
        private LinearLayout ll_que_list_audio;
        private TextView tv_leave_num;
        private TextView tv_que_list_audio_time;
        private TextView tv_que_list_content;
        private TextView tv_que_list_id;
        private ImageView tv_que_list_image;
        private TextView tv_que_list_time;
        private TextView tv_que_list_title;
        private TextView tv_que_list_user;

        ViewHolder() {}
    }
}
