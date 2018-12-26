package com.cai.strawberryapp.BaseClass;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HeaderMenu
{
    private Button backBut;
    private TextView headerTitle;
    private String headerTitleText;
    private boolean isBackBut = true;
    private Activity mActivity;
    private MediaPlayer mp;

    public HeaderMenu(Activity paramActivity, boolean paramBoolean, String paramString, MediaPlayer paramMediaPlayer)
    {
        this.mActivity = paramActivity;
        this.isBackBut = paramBoolean;
        this.mp = paramMediaPlayer;
        this.headerTitleText = paramString;
        this.backBut = ((Button)paramActivity.findViewById(2131361814));
        this.headerTitle = ((TextView)paramActivity.findViewById(2131361815));
    }

    private void backButClick()
    {
        this.backBut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                HeaderMenu.this.mActivity.finish();
            }
        });
    }

    private void setHeaderTitle()
    {
        this.headerTitle.setText(this.headerTitleText);
    }

    public Button getbackBut()
    {
        return this.backBut;
    }

    public void initHeaderBut()
    {
        setHeaderTitle();
        if (this.isBackBut) {
            backButClick();
        }
        while (this.isBackBut) {
            return;
        }
        this.backBut.setVisibility(8);
    }
}
