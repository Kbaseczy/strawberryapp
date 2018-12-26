package com.cai.strawberryapp.BaseClass;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.cai.strawberryapp.Activity.IndexActivity;
import com.cai.strawberryapp.Activity.LeaveActivity;
import com.cai.strawberryapp.Activity.UserActivity;
import com.cai.strawberryapp.Activity.VideoClassActivity;

public class FooterMenu
{
    private LinearLayout ll_classBut;
    private LinearLayout ll_homeBut;
    private LinearLayout ll_leaveBut;
    private LinearLayout ll_userBut;
    private Activity mActivity;

    public FooterMenu(Activity paramActivity)
    {
        this.mActivity = paramActivity;
        this.ll_homeBut = ((LinearLayout)paramActivity.findViewById(R.id.ll_home_but));
        this.ll_classBut = ((LinearLayout)paramActivity.findViewById(2131361800));
        this.ll_leaveBut = ((LinearLayout)paramActivity.findViewById(2131361802));
        this.ll_userBut = ((LinearLayout)paramActivity.findViewById(2131361804));
    }

    private void classButClick()
    {
        this.ll_classBut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                paramAnonymousView = new Intent(FooterMenu.this.mActivity, VideoClassActivity.class);
                FooterMenu.this.mActivity.startActivity(paramAnonymousView);
                FooterMenu.this.mActivity.finish();
            }
        });
    }

    private void homeButClick()
    {
        this.ll_homeBut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                paramAnonymousView = new Intent(FooterMenu.this.mActivity, IndexActivity.class);
                FooterMenu.this.mActivity.startActivity(paramAnonymousView);
                FooterMenu.this.mActivity.finish();
            }
        });
    }

    private void leaveButClick()
    {
        this.ll_leaveBut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                paramAnonymousView = new Intent(FooterMenu.this.mActivity, LeaveActivity.class);
                FooterMenu.this.mActivity.startActivity(paramAnonymousView);
                FooterMenu.this.mActivity.finish();
            }
        });
    }

    private void userButClick()
    {
        this.ll_userBut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                paramAnonymousView = new Intent(FooterMenu.this.mActivity, UserActivity.class);
                FooterMenu.this.mActivity.startActivity(paramAnonymousView);
                FooterMenu.this.mActivity.finish();
            }
        });
    }

    public void initFooterBut()
    {
        homeButClick();
        classButClick();
        leaveButClick();
        userButClick();
    }
}
