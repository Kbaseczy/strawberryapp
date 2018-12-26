package com.cai.strawberryapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.cai.strawberryapp.VO.LeaveFristAnsVO;
import com.cai.strawberryapp.VO.LeaveSecAnsVO;
import java.util.List;
import java.util.Map;

public class FristReplyAdapter
        extends BaseAdapter
{
    private final int FIRST_TYPE = 0;
    private final int OTHERS_TYPE = 1;
    private int Reply_User_Name = -167085;
    private final int TYPE_COUNT = 2;
    private int currentType;
    private List<byte[]> imaList;
    private Context mContext;
    private List<Map<String, Object>> mList;
    private Map<String, String> mMap;
    private int mPosition;
    private List<byte[]> mQueImgList;
    private MediaPlayer mediaPlayer;
    private List<LeaveSecAnsVO> secReplyList;
    private LeaveFristAnsVO vo;

    public FristReplyAdapter(Context paramContext, List<Map<String, Object>> paramList, Map<String, String> paramMap, List<byte[]> paramList1)
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
                paramView = LayoutInflater.from(this.mContext).inflate(2130968617, null);
                paramViewGroup.iv_que_detail_image1 = ((ImageView)paramView.findViewById(2131361970));
                paramViewGroup.iv_que_detail_image2 = ((ImageView)paramView.findViewById(2131361971));
                paramViewGroup.iv_que_detail_image3 = ((ImageView)paramView.findViewById(2131361972));
                paramViewGroup.iv_que_detail_image4 = ((ImageView)paramView.findViewById(2131361973));
                paramViewGroup.tv_que_detail_user = ((TextView)paramView.findViewById(2131361964));
                paramViewGroup.tv_que_detail_time = ((TextView)paramView.findViewById(2131361975));
                paramViewGroup.tv_que_detail_title = ((TextView)paramView.findViewById(2131361965));
                paramViewGroup.tv_que_detail_content = ((TextView)paramView.findViewById(2131361966));
                paramViewGroup.ll_que_detail_audio = ((LinearLayout)paramView.findViewById(2131361967));
                paramViewGroup.but_que_detail_audio = ((Button)paramView.findViewById(2131361968));
                paramViewGroup.tv_que_detail_audio_time = ((TextView)paramView.findViewById(2131361969));
                paramView.setTag(paramViewGroup);
                if ((!((String)this.mMap.get("tv_que_detail_content")).equals("")) && (this.mMap.get("tv_que_detail_content") != null)) {
                    break label370;
                }
                paramViewGroup.ll_que_detail_audio.setVisibility(0);
                paramViewGroup.but_que_detail_audio.setBackgroundResource(2130903043);
                paramViewGroup.tv_que_detail_content.setVisibility(8);
                paramViewGroup.tv_que_detail_audio_time.setText((CharSequence)this.mMap.get("queAudioTime"));
                paramViewGroup.but_que_detail_audio.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View paramAnonymousView)
                    {
                        if ((FristReplyAdapter.this.mediaPlayer != null) && (FristReplyAdapter.this.mediaPlayer.isPlaying()))
                        {
                            FristReplyAdapter.this.mediaPlayer.stop();
                            FristReplyAdapter.this.mediaPlayer.release();
                            FristReplyAdapter.this.mediaPlayer = null;
                            return;
                        }
                        FristReplyAdapter.this.mediaPlayer = new MediaPlayer();
                        FristReplyAdapter.this.mediaPlayer.reset();
                        FristReplyAdapter.this.mediaPlayer.setAudioStreamType(3);
                        try
                        {
                            FristReplyAdapter.this.mediaPlayer.setDataSource("http://118.190.155.221:8098/StrawAdminWeb/resources/audio/queaudio/" + (String)FristReplyAdapter.this.mMap.get("queAudioPath"));
                            FristReplyAdapter.this.mediaPlayer.prepareAsync();
                            FristReplyAdapter.this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
                            {
                                public void onPrepared(MediaPlayer paramAnonymous2MediaPlayer)
                                {
                                    FristReplyAdapter.this.mediaPlayer.start();
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
                paramViewGroup.tv_que_detail_user.setText((CharSequence)this.mMap.get("tv_que_detail_user"));
                paramViewGroup.tv_que_detail_time.setText((CharSequence)this.mMap.get("tv_que_detail_time"));
                paramViewGroup.tv_que_detail_title.setText((CharSequence)this.mMap.get("tv_que_detail_title"));
                setFristViewImg(paramViewGroup);
                return paramView;
                paramViewGroup = (FristViewHolder)paramView.getTag();
                break;
                label370:
                paramViewGroup.ll_que_detail_audio.setVisibility(8);
                paramViewGroup.tv_que_detail_content.setVisibility(0);
                paramViewGroup.tv_que_detail_content.setText((CharSequence)this.mMap.get("tv_que_detail_content"));
            }
        }
        Object localObject1 = paramView;
        label696:
        label839:
        label869:
        label882:
        Object localObject2;
        if (this.currentType == 1)
        {
            if (paramView != null) {
                break label1060;
            }
            paramViewGroup = new ViewHolder();
            paramView = LayoutInflater.from(this.mContext).inflate(2130968608, null);
            paramViewGroup.floor_num = ((TextView)paramView.findViewById(2131361925));
            paramViewGroup.tv_first_reply_id = ((TextView)paramView.findViewById(2131361924));
            paramViewGroup.tv_first_reply_user = ((TextView)paramView.findViewById(2131361926));
            paramViewGroup.tv_first_reply_content = ((TextView)paramView.findViewById(2131361927));
            paramViewGroup.tv_first_reply_time = ((TextView)paramView.findViewById(2131361932));
            paramViewGroup.ll_first_reply_audio = ((LinearLayout)paramView.findViewById(2131361928));
            paramViewGroup.but_first_reply_audio = ((Button)paramView.findViewById(2131361929));
            paramViewGroup.tv_first_reply_audio_time = ((TextView)paramView.findViewById(2131361930));
            paramViewGroup.ll_first_reply_img_container = ((LinearLayout)paramView.findViewById(2131361931));
            paramViewGroup.ll_sec_reply_bg = ((LinearLayout)paramView.findViewById(2131361933));
            paramViewGroup.tv_first_reply_sec_reply1 = ((TextView)paramView.findViewById(2131361934));
            paramViewGroup.tv_first_reply_sec_reply2 = ((TextView)paramView.findViewById(2131361935));
            paramView.setTag(paramViewGroup);
            localObject1 = (Map)this.mList.get(paramInt - 1);
            this.vo = ((LeaveFristAnsVO)((Map)localObject1).get("vo"));
            if ((((Map)localObject1).get("imgList") == null) || (((Map)localObject1).get("secReplyList") != null)) {
                break label1071;
            }
            this.imaList = ((List)((Map)localObject1).get("imgList"));
            paramViewGroup.floor_num.setText(paramInt + " ���");
            paramViewGroup.tv_first_reply_id.setText(this.vo.getFristAnsId());
            paramViewGroup.tv_first_reply_user.setText(this.vo.getFristAnsUser());
            if ((!this.vo.getFristAnsContent().equals("")) && (this.vo.getFristAnsContent() != null)) {
                break label1180;
            }
            paramViewGroup.tv_first_reply_content.setVisibility(8);
            paramViewGroup.ll_first_reply_audio.setVisibility(0);
            paramViewGroup.tv_first_reply_audio_time.setText(this.vo.getFristAnsAudioTime());
            paramViewGroup.but_first_reply_audio.setText(this.vo.getFristAnsAudioPath());
            paramViewGroup.but_first_reply_audio.setBackgroundResource(2130903043);
            paramViewGroup.tv_first_reply_content.setText("");
            paramViewGroup.tv_first_reply_time.setText(this.vo.getFristAnsTime());
            if (this.imaList == null) {
                break label1320;
            }
            paramViewGroup.ll_first_reply_img_container.removeAllViews();
            paramInt = 0;
            if (paramInt < this.imaList.size()) {
                break label1239;
            }
            if (this.secReplyList.size() <= 0) {
                break label1635;
            }
            if (this.secReplyList.size() >= 2) {
                break label1332;
            }
            paramViewGroup.ll_sec_reply_bg.setVisibility(0);
            paramViewGroup.tv_first_reply_sec_reply1.setVisibility(0);
            paramViewGroup.tv_first_reply_sec_reply1.setText(((LeaveSecAnsVO)this.secReplyList.get(0)).getSecAnsUser() + ":  " + ((LeaveSecAnsVO)this.secReplyList.get(0)).getSecAnsContent());
            localObject2 = paramViewGroup.tv_first_reply_sec_reply1.getText().toString();
            localObject1 = ((String)localObject2).split(":");
            localObject2 = new SpannableStringBuilder((CharSequence)localObject2);
            ((SpannableStringBuilder)localObject2).setSpan(new ForegroundColorSpan(this.Reply_User_Name), 0, localObject1[0].length(), 33);
            paramViewGroup.tv_first_reply_sec_reply1.setText((CharSequence)localObject2);
            localObject1 = paramView;
        }
        for (;;)
        {
            return (View)localObject1;
            label1060:
            paramViewGroup = (ViewHolder)paramView.getTag();
            break;
            label1071:
            if ((((Map)localObject1).get("imgList") == null) && (((Map)localObject1).get("secReplyList") != null))
            {
                this.secReplyList = ((List)((Map)localObject1).get("secReplyList"));
                break label696;
            }
            if ((((Map)localObject1).get("imgList") == null) || (((Map)localObject1).get("secReplyList") == null)) {
                break label696;
            }
            this.imaList = ((List)((Map)localObject1).get("imgList"));
            this.secReplyList = ((List)((Map)localObject1).get("secReplyList"));
            break label696;
            label1180:
            if ((this.vo.getFristAnsContent().equals("")) || (this.vo.getFristAnsContent() == null)) {
                break label839;
            }
            paramViewGroup.tv_first_reply_content.setVisibility(0);
            paramViewGroup.ll_first_reply_audio.setVisibility(8);
            paramViewGroup.tv_first_reply_content.setText(this.vo.getFristAnsContent());
            break label839;
            label1239:
            localObject1 = LayoutInflater.from(this.mContext).inflate(2130968600, paramViewGroup.ll_first_reply_img_container, false);
            ((ImageView)((View)localObject1).findViewById(2131361907)).setImageBitmap(BitmapFactory.decodeByteArray((byte[])this.imaList.get(paramInt), 0, ((byte[])this.imaList.get(paramInt)).length));
            paramViewGroup.ll_first_reply_img_container.addView((View)localObject1);
            paramInt += 1;
            break label869;
            label1320:
            paramViewGroup.ll_first_reply_img_container.setVisibility(8);
            break label882;
            label1332:
            localObject1 = paramView;
            if (this.secReplyList.size() == 2)
            {
                paramViewGroup.ll_sec_reply_bg.setVisibility(0);
                paramViewGroup.tv_first_reply_sec_reply1.setVisibility(0);
                paramViewGroup.tv_first_reply_sec_reply1.setText(((LeaveSecAnsVO)this.secReplyList.get(0)).getSecAnsUser() + ":  " + ((LeaveSecAnsVO)this.secReplyList.get(0)).getSecAnsContent());
                localObject1 = paramViewGroup.tv_first_reply_sec_reply1.getText().toString();
                localObject2 = ((String)localObject1).split(":");
                Object localObject3 = new SpannableStringBuilder((CharSequence)localObject1);
                localObject1 = new ForegroundColorSpan(this.Reply_User_Name);
                ((SpannableStringBuilder)localObject3).setSpan(localObject1, 0, localObject2[0].length(), 33);
                paramViewGroup.tv_first_reply_sec_reply1.setText((CharSequence)localObject3);
                paramViewGroup.tv_first_reply_sec_reply2.setVisibility(0);
                paramViewGroup.tv_first_reply_sec_reply2.setText(((LeaveSecAnsVO)this.secReplyList.get(1)).getSecAnsUser() + ":  " + ((LeaveSecAnsVO)this.secReplyList.get(1)).getSecAnsContent());
                localObject3 = paramViewGroup.tv_first_reply_sec_reply2.getText().toString();
                localObject2 = ((String)localObject3).split(":");
                localObject3 = new SpannableStringBuilder((CharSequence)localObject3);
                ((SpannableStringBuilder)localObject3).setSpan(localObject1, 0, localObject2[0].length(), 33);
                paramViewGroup.tv_first_reply_sec_reply2.setText((CharSequence)localObject3);
                localObject1 = paramView;
                continue;
                label1635:
                paramViewGroup.ll_sec_reply_bg.setVisibility(8);
                paramViewGroup.tv_first_reply_sec_reply2.setVisibility(8);
                paramViewGroup.tv_first_reply_sec_reply1.setVisibility(8);
                localObject1 = paramView;
            }
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
        Bitmap localBitmap = BitmapFactory.decodeByteArray((byte[])this.mQueImgList.get(i), 0, ((byte[])this.mQueImgList.get(i)).length);
        switch (i)
        {
        }
        for (;;)
        {
            i += 1;
            break;
            paramFristViewHolder.iv_que_detail_image1.setVisibility(0);
            paramFristViewHolder.iv_que_detail_image1.setImageBitmap(localBitmap);
            continue;
            paramFristViewHolder.iv_que_detail_image2.setVisibility(0);
            paramFristViewHolder.iv_que_detail_image2.setImageBitmap(localBitmap);
            continue;
            paramFristViewHolder.iv_que_detail_image3.setVisibility(0);
            paramFristViewHolder.iv_que_detail_image3.setImageBitmap(localBitmap);
            continue;
            paramFristViewHolder.iv_que_detail_image4.setVisibility(0);
            paramFristViewHolder.iv_que_detail_image4.setImageBitmap(localBitmap);
        }
    }

    class FristViewHolder
    {
        private Button but_que_detail_audio;
        private ImageView iv_que_detail_image1;
        private ImageView iv_que_detail_image2;
        private ImageView iv_que_detail_image3;
        private ImageView iv_que_detail_image4;
        private LinearLayout ll_que_detail_audio;
        private TextView tv_que_detail_audio_time;
        private TextView tv_que_detail_content;
        private TextView tv_que_detail_time;
        private TextView tv_que_detail_title;
        private TextView tv_que_detail_user;

        FristViewHolder() {}
    }

    class ViewHolder
    {
        private Button but_first_reply_audio;
        private TextView floor_num;
        private LinearLayout ll_first_reply_audio;
        private LinearLayout ll_first_reply_img_container;
        private LinearLayout ll_sec_reply_bg;
        private TextView tv_first_reply_audio_time;
        private TextView tv_first_reply_content;
        private TextView tv_first_reply_id;
        private TextView tv_first_reply_sec_reply1;
        private TextView tv_first_reply_sec_reply2;
        private TextView tv_first_reply_time;
        private TextView tv_first_reply_user;

        ViewHolder() {}
    }
}
