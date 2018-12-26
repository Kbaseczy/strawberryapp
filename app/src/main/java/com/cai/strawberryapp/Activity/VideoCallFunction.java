package com.cai.strawberryapp.Activity;

import android.media.MediaPlayer;
import java.util.Timer;
import java.util.TimerTask;

public abstract interface VideoCallFunction
{
    public abstract void stop(MediaPlayer paramMediaPlayer, Timer paramTimer, TimerTask paramTimerTask);
}
