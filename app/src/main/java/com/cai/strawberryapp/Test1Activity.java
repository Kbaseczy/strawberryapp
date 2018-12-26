package com.cai.strawberryapp;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.io.IOException;
import java.io.PrintStream;

public class Test1Activity
        extends Activity
{
    MediaRecorder mediaRecorder;

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130968592);
        paramBundle = (Button)findViewById(2131361865);
        Button localButton = (Button)findViewById(2131361866);
        paramBundle.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                try
                {
                    System.out.println("1");
                    Test1Activity.this.mediaRecorder = new MediaRecorder();
                    System.out.println("2");
                    Test1Activity.this.mediaRecorder.setAudioSource(1);
                    System.out.println("3");
                    Test1Activity.this.mediaRecorder.setOutputFormat(0);
                    System.out.println("4");
                    Test1Activity.this.mediaRecorder.setAudioEncoder(0);
                    System.out.println("5");
                    Test1Activity.this.mediaRecorder.setAudioChannels(1);
                    Test1Activity.this.mediaRecorder.setAudioSamplingRate(8000);
                    Test1Activity.this.mediaRecorder.setOutputFile(Test1Activity.this.getCacheDir() + "/audio.mp3");
                    System.out.println("6");
                    Test1Activity.this.mediaRecorder.prepare();
                    System.out.println("7");
                    Test1Activity.this.mediaRecorder.start();
                    return;
                }
                catch (IllegalStateException paramAnonymousView)
                {
                    paramAnonymousView.printStackTrace();
                    return;
                }
                catch (IOException paramAnonymousView)
                {
                    paramAnonymousView.printStackTrace();
                }
            }
        });
        localButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Test1Activity.this.mediaRecorder.stop();
                Test1Activity.this.mediaRecorder.release();
                Test1Activity.this.mediaRecorder = null;
            }
        });
    }
}
