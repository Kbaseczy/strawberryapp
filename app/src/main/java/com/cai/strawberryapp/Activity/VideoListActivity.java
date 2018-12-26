package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cai.strawberryapp.Adapter.VideoListAdapter;
import com.cai.strawberryapp.BaseClass.ConnWeb;
import com.cai.strawberryapp.BaseClass.FooterMenu;
import com.cai.strawberryapp.BaseClass.HeaderMenu;
import com.cai.strawberryapp.BaseClass.JsonUtil;
import com.cai.strawberryapp.VO.VideoListVO;
import com.cai.strawberryapp.View.OnRefreshListener;
import com.cai.strawberryapp.View.RefreshListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.PrintStream;
import java.util.List;

public class VideoListActivity
        extends Activity
        implements OnRefreshListener
{
    private String ThreeClassId;
    private Button backBut;
    private Button class_but;
    private ImageView iv_new_que;
    private List<VideoListVO> list;
    private int mLastY = 0;
    private int mYDown = 0;
    private int requestNum = 0;
    private RelativeLayout rl_video_list_progressbar;
    private Handler setVideoListHandler;
    VideoListAdapter videoListAdapter;
    private RefreshListView videoListView;

    public void addNewQueClick()
    {
        this.iv_new_que.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView) {}
        });
    }

    public void getVideoListInfo()
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Object localObject = ConnWeb.ConnForJson(VideoListActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/class/VideoListInfoAjax", "requestNum=" + VideoListActivity.this.requestNum + "&ThreeClassId=" + VideoListActivity.this.ThreeClassId, "GET", 0, null, null);
                    System.out.println("================ddddddd================");
                    VideoListActivity.this.list = ((List)JsonUtil.getGson().fromJson((String)localObject, new TypeToken() {}.getType()));
                    localObject = new Message();
                    ((Message)localObject).obj = VideoListActivity.this.list;
                    VideoListActivity.this.setVideoListHandler.sendMessage((Message)localObject);
                    return;
                }
                catch (Exception localException)
                {
                    localException.printStackTrace();
                }
            }
        }.start();
    }

    public void init()
    {
        new HeaderMenu(this, true, "������", null).initHeaderBut();
        this.backBut = ((Button)findViewById(2131361814));
        this.backBut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                paramAnonymousView = new Intent(VideoListActivity.this, VideoClassActivity.class);
                VideoListActivity.this.startActivity(paramAnonymousView);
                VideoListActivity.this.finish();
            }
        });
        new FooterMenu(this).initFooterBut();
        this.class_but = ((Button)findViewById(2131361801));
        this.class_but.setBackgroundResource(2130903051);
        this.videoListView = ((RefreshListView)findViewById(2131361885));
        this.iv_new_que = ((ImageView)findViewById(2131361823));
        this.rl_video_list_progressbar = ((RelativeLayout)findViewById(2131361884));
        this.ThreeClassId = getIntent().getExtras().getString("threeId");
    }

    public void itemClick()
    {
        this.videoListView.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
            {
                switch (paramAnonymousMotionEvent.getAction())
                {
                }
                for (;;)
                {
                    if ((VideoListActivity.this.mYDown - VideoListActivity.this.mLastY < 10) && (VideoListActivity.this.mYDown - VideoListActivity.this.mLastY > -10)) {
                        VideoListActivity.this.videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
                            {
                                paramAnonymous2AdapterView = (TextView)paramAnonymous2View.findViewById(2131361956);
                                paramAnonymous2View = (TextView)paramAnonymous2View.findViewById(2131361955);
                                Intent localIntent = new Intent(VideoListActivity.this, VideoPlayActivity.class);
                                Bundle localBundle = new Bundle();
                                localBundle.putString("threadId", VideoListActivity.this.ThreeClassId);
                                localBundle.putString("videoPath", paramAnonymous2AdapterView.getText().toString());
                                localBundle.putString("videoTitle", paramAnonymous2View.getText().toString());
                                localIntent.putExtras(localBundle);
                                VideoListActivity.this.startActivity(localIntent);
                            }
                        });
                    }
                    return false;
                    VideoListActivity.this.mYDown = ((int)paramAnonymousMotionEvent.getRawY());
                    continue;
                    VideoListActivity.this.mLastY = ((int)paramAnonymousMotionEvent.getRawY());
                }
            }
        });
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130968596);
        init();
        getVideoListInfo();
        this.setVideoListHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                super.handleMessage(paramAnonymousMessage);
                VideoListActivity.this.rl_video_list_progressbar.setVisibility(8);
                VideoListActivity.this.videoListView.setVisibility(0);
                VideoListActivity.this.videoListAdapter = new VideoListAdapter(VideoListActivity.this, (List)paramAnonymousMessage.obj);
                VideoListActivity.this.videoListView.setAdapter(VideoListActivity.this.videoListAdapter);
                VideoListActivity.this.videoListView.setOnRefreshListener(VideoListActivity.this);
            }
        };
        itemClick();
    }

    public void onDownPullRefresh()
    {
        new AsyncTask()
        {
            protected Void doInBackground(Void... paramAnonymousVarArgs)
            {
                try
                {
                    Thread.sleep(2000L);
                    VideoListActivity.this.requestNum = 0;
                    System.out.println("������");
                }
                catch (InterruptedException paramAnonymousVarArgs)
                {
                    try
                    {
                        paramAnonymousVarArgs = ConnWeb.ConnForJson(VideoListActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/class/VideoListInfoAjax", "requestNum=0&ThreeClassId=" + VideoListActivity.this.ThreeClassId, "GET", 0, null, null);
                        paramAnonymousVarArgs = (List)JsonUtil.getGson().fromJson(paramAnonymousVarArgs, new TypeToken() {}.getType());
                        VideoListActivity.this.list.clear();
                        int i = 0;
                        for (;;)
                        {
                            int j = paramAnonymousVarArgs.size();
                            if (i >= j)
                            {
                                return null;
                                paramAnonymousVarArgs = paramAnonymousVarArgs;
                                paramAnonymousVarArgs.printStackTrace();
                                break;
                            }
                            VideoListActivity.this.list.add((VideoListVO)paramAnonymousVarArgs.get(i));
                            i += 1;
                        }
                        return null;
                    }
                    catch (Exception paramAnonymousVarArgs)
                    {
                        paramAnonymousVarArgs.printStackTrace();
                    }
                }
            }

            protected void onPostExecute(Void paramAnonymousVoid)
            {
                VideoListActivity.this.videoListAdapter.notifyDataSetChanged();
                VideoListActivity.this.videoListView.hideHeaderView();
            }
        }.execute(new Void[0]);
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
    {
        startActivity(new Intent(this, VideoClassActivity.class));
        finish();
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    public void onLoadingMore()
    {
        new AsyncTask()
        {
            protected Void doInBackground(Void... paramAnonymousVarArgs)
            {
                try
                {
                    Thread.sleep(2000L);
                    System.out.println("������������");
                }
                catch (InterruptedException paramAnonymousVarArgs)
                {
                    try
                    {
                        paramAnonymousVarArgs = VideoListActivity.this;
                        paramAnonymousVarArgs.requestNum += 1;
                        paramAnonymousVarArgs = ConnWeb.ConnForJson(VideoListActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/class/VideoListInfoAjax", "requestNum=" + VideoListActivity.this.requestNum + "&ThreeClassId=" + VideoListActivity.this.ThreeClassId, "GET", 0, null, null);
                        paramAnonymousVarArgs = (List)JsonUtil.getGson().fromJson(paramAnonymousVarArgs, new TypeToken() {}.getType());
                        int i = 0;
                        for (;;)
                        {
                            int j = paramAnonymousVarArgs.size();
                            if (i >= j)
                            {
                                return null;
                                paramAnonymousVarArgs = paramAnonymousVarArgs;
                                paramAnonymousVarArgs.printStackTrace();
                                break;
                            }
                            VideoListActivity.this.list.add((VideoListVO)paramAnonymousVarArgs.get(i));
                            i += 1;
                        }
                        return null;
                    }
                    catch (Exception paramAnonymousVarArgs)
                    {
                        paramAnonymousVarArgs.printStackTrace();
                    }
                }
            }

            protected void onPostExecute(Void paramAnonymousVoid)
            {
                VideoListActivity.this.videoListAdapter.notifyDataSetChanged();
                VideoListActivity.this.videoListView.hideFooterView();
            }
        }.execute(new Void[0]);
    }
}
