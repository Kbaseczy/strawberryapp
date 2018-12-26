package com.cai.strawberryapp.BaseClass;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.cai.strawberryapp.Activity.VideoPlayActivity;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;

public class MyVideoPlayer
{
    private Activity activity;
    private Button backBut;
    private CountDownTimer cdt = null;
    private Context context;
    private boolean flag = false;
    private SurfaceHolder holder;
    private MediaPlayer mediaplayer;
    private Button pauseBut;
    private int pausePosition;
    private Handler pauseUIHandler;
    private int position;
    private RelativeLayout rl;
    private RelativeLayout rl_play_buffer;
    private RelativeLayout rl_play_progress;
    private SeekBar sbar;
    private TextView sbarTime;
    private String sourcePath;
    private SurfaceView sv;
    private TimerTask task;
    private Handler timeUIHandler;
    private Timer timer;
    private double totalTime;

    public MyVideoPlayer(SurfaceView paramSurfaceView, RelativeLayout paramRelativeLayout1, RelativeLayout paramRelativeLayout2, RelativeLayout paramRelativeLayout3, SeekBar paramSeekBar, TextView paramTextView, Button paramButton, String paramString, Context paramContext)
    {
        this.sv = paramSurfaceView;
        this.context = paramContext;
        this.rl = paramRelativeLayout2;
        this.sbar = paramSeekBar;
        this.sourcePath = paramString;
        this.sbarTime = paramTextView;
        this.pauseBut = paramButton;
        this.rl_play_progress = paramRelativeLayout3;
        this.rl_play_buffer = paramRelativeLayout1;
    }

    public void MyProgressBar()
    {
        this.sbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
            {
                paramAnonymousSeekBar = new Message();
                paramAnonymousSeekBar.obj = Integer.valueOf(paramAnonymousInt);
                MyVideoPlayer.this.timeUIHandler.sendMessage(paramAnonymousSeekBar);
            }

            public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
            {
                MyVideoPlayer.this.rl.setVisibility(0);
                if (MyVideoPlayer.this.cdt != null) {
                    MyVideoPlayer.this.cdt.cancel();
                }
                MyVideoPlayer.this.mediaplayer.pause();
            }

            public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
            {
                Message localMessage = new Message();
                MyVideoPlayer.this.pauseUIHandler.sendMessage(localMessage);
                int i = paramAnonymousSeekBar.getProgress();
                MyVideoPlayer.this.mediaplayer.start();
                if ((MyVideoPlayer.this.mediaplayer != null) && (MyVideoPlayer.this.mediaplayer.isPlaying())) {
                    MyVideoPlayer.this.mediaplayer.seekTo(i);
                }
                MyVideoPlayer.this.cdt = new CountDownTimer(3000L, 1000L)
                {
                    public void onFinish()
                    {
                        MyVideoPlayer.this.rl.setVisibility(4);
                    }

                    public void onTick(long paramAnonymous2Long) {}
                };
                MyVideoPlayer.this.cdt.start();
            }
        });
        this.timer = new Timer();
        this.task = new TimerTask()
        {
            public void run()
            {
                if ((MyVideoPlayer.this.mediaplayer != null) && (MyVideoPlayer.this.mediaplayer.isPlaying()))
                {
                    int i = MyVideoPlayer.this.mediaplayer.getCurrentPosition();
                    int j = MyVideoPlayer.this.mediaplayer.getDuration();
                    MyVideoPlayer.this.sbar.setMax(j);
                    MyVideoPlayer.this.sbar.setProgress(i);
                    Message localMessage = new Message();
                    localMessage.obj = Integer.valueOf(i);
                    MyVideoPlayer.this.timeUIHandler.sendMessage(localMessage);
                }
            }
        };
        this.timer.schedule(this.task, 500L, 500L);
    }

    public void MyProgressBarCloss()
    {
        if ((this.timer != null) && (this.task != null))
        {
            this.timer.cancel();
            this.task.cancel();
            this.timer = null;
            this.task = null;
        }
    }

    public void Play()
    {
        this.timeUIHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                super.handleMessage(paramAnonymousMessage);
                double d1 = MyVideoPlayer.this.totalTime;
                double d2 = ((Integer)paramAnonymousMessage.obj).intValue();
                if ((((Integer)paramAnonymousMessage.obj).intValue() > 0) && (MyVideoPlayer.this.rl_play_progress.getVisibility() != 8)) {
                    MyVideoPlayer.this.rl_play_progress.setVisibility(8);
                }
                MyVideoPlayer.this.sbarTime.setText(TimeFormat.timeFormat(d1 - d2));
            }
        };
        this.pauseUIHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                super.handleMessage(paramAnonymousMessage);
                if (MyVideoPlayer.this.pauseBut.getText().equals("������"))
                {
                    MyVideoPlayer.this.pauseBut.setBackgroundResource(2130903088);
                    MyVideoPlayer.this.pauseBut.setText("������");
                }
            }
        };
        this.holder = this.sv.getHolder();
        this.holder.setType(3);
        this.holder.addCallback(new SurfaceHolder.Callback()
        {
            public void surfaceChanged(SurfaceHolder paramAnonymousSurfaceHolder, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}

            public void surfaceCreated(SurfaceHolder paramAnonymousSurfaceHolder)
            {
                try
                {
                    MyVideoPlayer.this.mediaplayer = new MediaPlayer();
                    MyVideoPlayer.this.mediaplayer.setAudioStreamType(3);
                    MyVideoPlayer.this.mediaplayer.setDataSource(MyVideoPlayer.this.sourcePath);
                    MyVideoPlayer.this.mediaplayer.setDisplay(paramAnonymousSurfaceHolder);
                    MyVideoPlayer.this.mediaplayer.prepareAsync();
                    MyVideoPlayer.this.mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
                    {
                        public void onPrepared(MediaPlayer paramAnonymous2MediaPlayer)
                        {
                            MyVideoPlayer.this.mediaplayer.start();
                            MyVideoPlayer.this.totalTime = MyVideoPlayer.this.mediaplayer.getDuration();
                            MyVideoPlayer.this.sbarTime.setText(TimeFormat.timeFormat(MyVideoPlayer.this.totalTime));
                            new CountDownTimer(3000L, 1000L)
                            {
                                public void onFinish()
                                {
                                    MyVideoPlayer.this.rl.setVisibility(4);
                                }

                                public void onTick(long paramAnonymous3Long) {}
                            }.start();
                        }
                    });
                    MyVideoPlayer.this.mediaplayer.setOnInfoListener(new MediaPlayer.OnInfoListener()
                    {
                        public boolean onInfo(MediaPlayer paramAnonymous2MediaPlayer, int paramAnonymous2Int1, int paramAnonymous2Int2)
                        {
                            System.out.println("what" + paramAnonymous2Int1);
                            if (MyVideoPlayer.this.rl_play_progress.getVisibility() == 8)
                            {
                                if ((paramAnonymous2Int1 == 703) || (paramAnonymous2Int1 == 701)) {
                                    MyVideoPlayer.this.rl_play_buffer.setVisibility(0);
                                }
                            }
                            else {
                                return false;
                            }
                            MyVideoPlayer.this.rl_play_buffer.setVisibility(8);
                            return false;
                        }
                    });
                    MyVideoPlayer.this.mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
                    {
                        public void onCompletion(MediaPlayer paramAnonymous2MediaPlayer)
                        {
                            MyVideoPlayer.this.MyProgressBarCloss();
                            ((VideoPlayActivity)MyVideoPlayer.this.context).stop(paramAnonymous2MediaPlayer, MyVideoPlayer.this.timer, MyVideoPlayer.this.task);
                        }
                    });
                    return;
                }
                catch (Exception paramAnonymousSurfaceHolder)
                {
                    Toast.makeText(MyVideoPlayer.this.context, "������������", 1000).show();
                    paramAnonymousSurfaceHolder.printStackTrace();
                }
            }

            public void surfaceDestroyed(SurfaceHolder paramAnonymousSurfaceHolder) {}
        });
    }

    public void PlayerManager(MotionEvent paramMotionEvent)
    {
        switch (paramMotionEvent.getAction())
        {
        }
        do
        {
            do
            {
                return;
                if (this.rl.getVisibility() == 4)
                {
                    this.rl.setVisibility(0);
                    return;
                }
            } while (this.rl.getVisibility() != 0);
            this.rl.setVisibility(4);
            return;
        } while (this.rl.getVisibility() != 0);
        this.cdt = new CountDownTimer(3000L, 1000L)
        {
            public void onFinish()
            {
                MyVideoPlayer.this.rl.setVisibility(4);
            }

            public void onTick(long paramAnonymousLong) {}
        };
        this.cdt.start();
    }

    public int getCurrentPosition()
    {
        return this.mediaplayer.getCurrentPosition();
    }

    public MediaPlayer getMediaplayer()
    {
        return this.mediaplayer;
    }

    public void pause()
    {
        this.pausePosition = this.mediaplayer.getCurrentPosition();
        this.mediaplayer.pause();
    }

    public void restart()
    {
        this.mediaplayer.start();
        this.mediaplayer.seekTo(this.pausePosition);
    }

    public void seekTo(int paramInt)
    {
        this.mediaplayer.seekTo(paramInt);
    }
}
