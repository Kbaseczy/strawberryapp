package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.cai.strawberryapp.BaseClass.MyVideoPlayer;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;

public class VideoPlayActivity
        extends Activity
        implements VideoCallFunction
{
    private Button backBut;
    private MediaPlayer mp;
    private MyVideoPlayer mvp;
    private Button pauseBut;
    private RelativeLayout rl;
    private RelativeLayout rl_play_buffer;
    private RelativeLayout rl_play_progress;
    private SeekBar sbar;
    private TextView sbarTime;
    private Handler setVideoPlayHandler;
    private String sourcePath;
    private SurfaceView sv;
    private String threadId;
    private TextView title;
    private TextView tv_play_buffer;
    private String videoPath;
    private String videoTitle;

    public void backBut() {}

    public void init()
    {
        Bundle localBundle = getIntent().getExtras();
        this.threadId = localBundle.getString("threadId");
        this.videoPath = localBundle.getString("videoPath");
        this.videoTitle = localBundle.getString("videoTitle");
        this.sv = ((SurfaceView)findViewById(2131361886));
        this.rl = ((RelativeLayout)findViewById(2131361890));
        this.rl_play_progress = ((RelativeLayout)findViewById(2131361887));
        this.rl_play_buffer = ((RelativeLayout)findViewById(2131361888));
        this.tv_play_buffer = ((TextView)findViewById(2131361889));
        this.backBut = ((Button)findViewById(2131361892));
        this.sbar = ((SeekBar)findViewById(2131361894));
        this.pauseBut = ((Button)findViewById(2131361891));
        this.title = ((TextView)findViewById(2131361893));
        this.sbarTime = ((TextView)findViewById(2131361895));
        this.title.setText(this.videoTitle);
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130968597);
        getWindow().addFlags(128);
        System.out.println("==========onCreate===========");
        init();
        this.sourcePath = ("http://118.190.155.221:8098" + this.videoPath);
        this.mvp = new MyVideoPlayer(this.sv, this.rl_play_buffer, this.rl, this.rl_play_progress, this.sbar, this.sbarTime, this.pauseBut, this.sourcePath, this);
        this.mvp.Play();
        this.mvp.MyProgressBar();
        pauseBut();
        this.mp = this.mvp.getMediaplayer();
        backBut();
        this.backBut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                VideoPlayActivity.this.mvp.MyProgressBarCloss();
                VideoPlayActivity.this.mvp = null;
                VideoPlayActivity.this.finish();
            }
        });
    }

    protected void onDestroy()
    {
        System.out.println("==========onDestroy===========");
        if (this.mvp != null) {
            this.mvp.MyProgressBarCloss();
        }
        this.mvp = null;
        super.onDestroy();
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
    {
        if (paramInt == 4) {
            return true;
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
        this.mvp.PlayerManager(paramMotionEvent);
        return super.onTouchEvent(paramMotionEvent);
    }

    public void pauseBut()
    {
        this.pauseBut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (VideoPlayActivity.this.pauseBut.getText().equals("������"))
                {
                    VideoPlayActivity.this.pauseBut.setBackgroundResource(2130903095);
                    VideoPlayActivity.this.pauseBut.setText("������");
                    VideoPlayActivity.this.mvp.pause();
                }
                while (!VideoPlayActivity.this.pauseBut.getText().equals("������")) {
                    return;
                }
                VideoPlayActivity.this.pauseBut.setBackgroundResource(2130903088);
                VideoPlayActivity.this.pauseBut.setText("������");
                VideoPlayActivity.this.mvp.restart();
            }
        });
    }

    public void stop(MediaPlayer paramMediaPlayer, Timer paramTimer, TimerTask paramTimerTask)
    {
        paramMediaPlayer.stop();
        paramMediaPlayer.release();
        finish();
    }
}
