package com.cai.strawberryapp.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import com.cai.strawberryapp.Activity.VideoListActivity;
import com.cai.strawberryapp.Adapter.VideoThreeClassAdapter;
import com.cai.strawberryapp.VO.VideoThreeClassVO;
import java.util.List;

public class ThreeClassFragment
        extends Fragment
{
    private GridView gv_three_video;
    private Context mContext;
    private List<VideoThreeClassVO> mList;
    private TextView tv_three_class_id;

    public ThreeClassFragment(Context paramContext, List<VideoThreeClassVO> paramList)
    {
        this.mContext = paramContext;
        this.mList = paramList;
    }

    public void gridItemClick()
    {
        this.gv_three_video.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                ThreeClassFragment.this.tv_three_class_id = ((TextView)paramAnonymousView.findViewById(2131361909));
                paramAnonymousAdapterView = new Intent(ThreeClassFragment.this.mContext, VideoListActivity.class);
                paramAnonymousView = new Bundle();
                paramAnonymousView.putString("threeId", ThreeClassFragment.this.tv_three_class_id.getText().toString());
                paramAnonymousAdapterView.putExtras(paramAnonymousView);
                ThreeClassFragment.this.startActivityForResult(paramAnonymousAdapterView, 0);
            }
        });
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        paramLayoutInflater = View.inflate(getActivity(), 2130968602, null);
        this.gv_three_video = ((GridView)paramLayoutInflater.findViewById(2131361911));
        paramViewGroup = new VideoThreeClassAdapter(getActivity(), this.mList);
        this.gv_three_video.setAdapter(paramViewGroup);
        gridItemClick();
        return paramLayoutInflater;
    }
}
