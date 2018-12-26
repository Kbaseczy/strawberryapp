package com.cai.strawberryapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cai.strawberryapp.VO.LeaveSecAnsVO;
import java.util.List;
import java.util.Map;

public class SecReplyAdapter
        extends BaseAdapter
{
    private final int FIRST_TYPE = 0;
    private final int OTHERS_TYPE = 1;
    private int Reply_User_Name = -167085;
    private final int TYPE_COUNT = 2;
    private int currentType;
    private List<byte[]> imaList;
    private Context mContext;
    private List<LeaveSecAnsVO> mList;
    private Map<String, String> mMap;
    private int mPosition;
    private List<Bitmap> mQueImgList;
    private MediaPlayer mediaPlayer;
    private List<String> secReplyList;
    private LeaveSecAnsVO vo;

    public SecReplyAdapter(Context paramContext, List<LeaveSecAnsVO> paramList, Map<String, String> paramMap, List<Bitmap> paramList1)
    {
        this.mContext = paramContext;
        this.mList = paramList;
        this.mMap = paramMap;
        this.mQueImgList = paramList1;
    }

    public int getCount()
    {
        if (this.mList == null) {
            return 0;
        }
        return this.mList.size() + 1;
    }

    public Object getItem(int paramInt)
    {
        return null;
    }

    public long getItemId(int paramInt)
    {
        return paramInt;
    }

    public int getItemViewType(int paramInt)
    {
        if (paramInt == 0) {
            return 0;
        }
        return 1;
    }

    public MediaPlayer getMediaPlayer()
    {
        return this.mediaPlayer;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
        this.currentType = getItemViewType(paramInt);
        if (this.currentType == 0)
        {
            paramViewGroup = new FristViewHolder();
            if (paramView == null)
            {
                paramView = LayoutInflater.from(this.mContext).inflate(2130968598, null);
                paramViewGroup.iv_ans_detail_image1 = ((ImageView)paramView.findViewById(2131361902));
                paramViewGroup.iv_ans_detail_image2 = ((ImageView)paramView.findViewById(2131361903));
                paramViewGroup.iv_ans_detail_image3 = ((ImageView)paramView.findViewById(2131361904));
                paramViewGroup.iv_ans_detail_image4 = ((ImageView)paramView.findViewById(2131361905));
                paramViewGroup.tv_ans_detail_user = ((TextView)paramView.findViewById(2131361896));
                paramViewGroup.tv_ans_detail_time = ((TextView)paramView.findViewById(2131361897));
                paramViewGroup.tv_ans_detail_content = ((TextView)paramView.findViewById(2131361898));
                paramViewGroup.ll_ans_detail_audio = ((LinearLayout)paramView.findViewById(2131361899));
                paramViewGroup.but_ans_detail_audio = ((Button)paramView.findViewById(2131361900));
                paramViewGroup.tv_ans_detail_audio_time = ((TextView)paramView.findViewById(2131361901));
                paramView.setTag(paramViewGroup);
                if ((!((String)this.mMap.get("tv_first_reply_content")).equals("")) || (((String)this.mMap.get("audioPath")).equals(""))) {
                    break label344;
                }
                paramViewGroup.ll_ans_detail_audio.setVisibility(0);
                paramViewGroup.tv_ans_detail_content.setVisibility(8);
                paramViewGroup.tv_ans_detail_audio_time.setText((CharSequence)this.mMap.get("audioTime"));
                paramViewGroup.but_ans_detail_audio.setBackgroundResource(2130903043);
                paramViewGroup.but_ans_detail_audio.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View paramAnonymousView)
                    {
                        if ((SecReplyAdapter.this.mediaPlayer != null) && (SecReplyAdapter.this.mediaPlayer.isPlaying()))
                        {
                            SecReplyAdapter.this.mediaPlayer.stop();
                            SecReplyAdapter.this.mediaPlayer.release();
                            SecReplyAdapter.this.mediaPlayer = null;
                            return;
                        }
                        SecReplyAdapter.this.mediaPlayer = new MediaPlayer();
                        SecReplyAdapter.this.mediaPlayer.reset();
                        SecReplyAdapter.this.mediaPlayer.setAudioStreamType(3);
                        try
                        {
                            SecReplyAdapter.this.mediaPlayer.setDataSource("http://118.190.155.221:8098/StrawAdminWeb/resources/audio/ansaudio/" + (String)SecReplyAdapter.this.mMap.get("audioPath"));
                            SecReplyAdapter.this.mediaPlayer.prepareAsync();
                            SecReplyAdapter.this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
                            {
                                public void onPrepared(MediaPlayer paramAnonymous2MediaPlayer)
                                {
                                    SecReplyAdapter.this.mediaPlayer.start();
                                }
                            });
                            return;
                        }
                        catch (Exception paramAnonymousView)
                        {
                            paramAnonymousView.printStackTrace();
                        }
                    }
                });
            }
            for (;;)
            {
                paramViewGroup.tv_ans_detail_user.setText((CharSequence)this.mMap.get("tv_first_reply_user"));
                paramViewGroup.tv_ans_detail_time.setText((CharSequence)this.mMap.get("tv_first_reply_time"));
                setFristViewImg(paramViewGroup);
                return paramView;
                paramViewGroup = (FristViewHolder)paramView.getTag();
                break;
                label344:
                paramViewGroup.ll_ans_detail_audio.setVisibility(8);
                paramViewGroup.tv_ans_detail_content.setVisibility(0);
                paramViewGroup.tv_ans_detail_content.setText((CharSequence)this.mMap.get("tv_first_reply_content"));
            }
        }
        paramViewGroup = paramView;
        if (this.currentType == 1)
        {
            if (paramView != null) {
                break label584;
            }
            paramViewGroup = new ViewHolder();
            paramView = LayoutInflater.from(this.mContext).inflate(2130968611, null);
            paramViewGroup.tv_sec_reply_content = ((TextView)paramView.findViewById(2131361951));
            paramViewGroup.tv_sec_reply_time = ((TextView)paramView.findViewById(2131361952));
            paramView.setTag(paramViewGroup);
        }
        for (;;)
        {
            this.vo = ((LeaveSecAnsVO)this.mList.get(paramInt - 1));
            Object localObject = this.vo.getSecAnsUser() + ":  " + this.vo.getSecAnsContent();
            String[] arrayOfString = ((String)localObject).split(":");
            localObject = new SpannableStringBuilder((CharSequence)localObject);
            ((SpannableStringBuilder)localObject).setSpan(new ForegroundColorSpan(this.Reply_User_Name), 0, arrayOfString[0].length(), 33);
            paramViewGroup.tv_sec_reply_content.setText((CharSequence)localObject);
            paramViewGroup.tv_sec_reply_time.setText(this.vo.getSecAnsTime());
            paramViewGroup = paramView;
            return paramViewGroup;
            label584:
            paramViewGroup = (ViewHolder)paramView.getTag();
        }
    }

    public int getViewTypeCount()
    {
        return 2;
    }

    public void setFristViewImg(FristViewHolder paramFristViewHolder)
    {
        int i;
        if (this.mQueImgList.size() > 0)
        {
            i = 0;
            if (i < this.mQueImgList.size()) {}
        }
        else
        {
            return;
        }
        switch (i)
        {
        }
        for (;;)
        {
            i += 1;
            break;
            paramFristViewHolder.iv_ans_detail_image1.setVisibility(0);
            paramFristViewHolder.iv_ans_detail_image1.setImageBitmap((Bitmap)this.mQueImgList.get(i));
            continue;
            paramFristViewHolder.iv_ans_detail_image2.setVisibility(0);
            paramFristViewHolder.iv_ans_detail_image2.setImageBitmap((Bitmap)this.mQueImgList.get(i));
            continue;
            paramFristViewHolder.iv_ans_detail_image3.setVisibility(0);
            paramFristViewHolder.iv_ans_detail_image3.setImageBitmap((Bitmap)this.mQueImgList.get(i));
            continue;
            paramFristViewHolder.iv_ans_detail_image4.setVisibility(0);
            paramFristViewHolder.iv_ans_detail_image4.setImageBitmap((Bitmap)this.mQueImgList.get(i));
        }
    }

    class FristViewHolder
    {
        private Button but_ans_detail_audio;
        private ImageView iv_ans_detail_image1;
        private ImageView iv_ans_detail_image2;
        private ImageView iv_ans_detail_image3;
        private ImageView iv_ans_detail_image4;
        private LinearLayout ll_ans_detail_audio;
        private TextView tv_ans_detail_audio_time;
        private TextView tv_ans_detail_content;
        private TextView tv_ans_detail_time;
        private TextView tv_ans_detail_user;

        FristViewHolder() {}
    }

    class ViewHolder
    {
        private TextView tv_sec_reply_content;
        private TextView tv_sec_reply_time;

        ViewHolder() {}
    }
}
