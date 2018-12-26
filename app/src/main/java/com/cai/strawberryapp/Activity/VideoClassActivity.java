package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.cai.strawberryapp.Adapter.VideoFirstClassAdapter;
import com.cai.strawberryapp.Adapter.VideoSecClassAdapter;
import com.cai.strawberryapp.BaseClass.ConnWeb;
import com.cai.strawberryapp.BaseClass.FooterMenu;
import com.cai.strawberryapp.BaseClass.HeaderMenu;
import com.cai.strawberryapp.BaseClass.JsonUtil;
import com.cai.strawberryapp.Fragment.ThreeClassFragment;
import com.cai.strawberryapp.VO.VideoClassVO;
import com.cai.strawberryapp.VO.VideoThreeClassVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.PrintStream;
import java.util.List;

public class VideoClassActivity
        extends Activity
{
    private Button backbut;
    private Button class_but;
    private FrameLayout fl_videoclass;
    boolean flag = true;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private ImageView iv_videoclass_hintpic;
    private List<List<VideoClassVO>> list;
    private ListView lv_second_class;
    private int secClassHeight = 0;
    private Handler setVideoFirstClassHandler;
    private Handler setVideoThreeClassHandler;
    private TextView tv_sec_class;
    private ListView videoClassListView;
    private int windowHeight = 0;
    private int windowWidth = 0;

    private void showExitDialog()
    {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setMessage("���������������������");
        localBuilder.setPositiveButton("������", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                System.exit(0);
            }
        });
        localBuilder.setNeutralButton("������", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                paramAnonymousDialogInterface.cancel();
            }
        });
        localBuilder.show();
    }

    public void firstItemClick()
    {
        this.videoClassListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                VideoClassActivity.this.lv_second_class = ((ListView)paramAnonymousView.findViewById(2131361960));
                paramAnonymousView = new VideoSecClassAdapter(VideoClassActivity.this, (List)VideoClassActivity.this.list.get(paramAnonymousInt));
                VideoClassActivity.this.lv_second_class.measure(0, 0);
                int i;
                if (VideoClassActivity.this.lv_second_class.getVisibility() == 8)
                {
                    VideoClassActivity.this.lv_second_class.setVisibility(0);
                    if (paramAnonymousInt == 0)
                    {
                        i = 0;
                        if (i < VideoClassActivity.this.list.size()) {}
                    }
                    else
                    {
                        ViewGroup.LayoutParams localLayoutParams = VideoClassActivity.this.lv_second_class.getLayoutParams();
                        i = VideoClassActivity.this.secClassHeight;
                        localLayoutParams.height = (((List)VideoClassActivity.this.list.get(paramAnonymousInt)).size() * i);
                        VideoClassActivity.this.lv_second_class.setLayoutParams(localLayoutParams);
                        System.out.println("������������");
                        VideoClassActivity.this.lv_second_class.setAdapter(paramAnonymousView);
                        label187:
                        if (paramAnonymousInt != 0) {
                            i = 0;
                        }
                    }
                }
                for (;;)
                {
                    if (i >= VideoClassActivity.this.list.size() - 1)
                    {
                        VideoClassActivity.this.secItemClick();
                        return;
                        if (i != paramAnonymousInt) {
                            ((ListView)paramAnonymousAdapterView.getChildAt(i - paramAnonymousAdapterView.getFirstVisiblePosition()).findViewById(2131361960)).setVisibility(8);
                        }
                        i += 1;
                        break;
                        VideoClassActivity.this.lv_second_class.setVisibility(8);
                        break label187;
                    }
                    if (i != paramAnonymousInt) {
                        ((ListView)paramAnonymousAdapterView.getChildAt(i - paramAnonymousAdapterView.getFirstVisiblePosition()).findViewById(2131361960)).setVisibility(8);
                    }
                    i += 1;
                }
            }
        });
    }

    public void getThreeClassInfo(final String paramString)
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Object localObject = ConnWeb.ConnForJson(VideoClassActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/class/VideoThreeClassAjax", "SecClassId=" + paramString, "GET", 0, null, null);
                    System.out.println("================aaaaaaa================");
                    localObject = (List)JsonUtil.getGson().fromJson((String)localObject, new TypeToken() {}.getType());
                    Message localMessage = new Message();
                    localMessage.obj = localObject;
                    VideoClassActivity.this.setVideoThreeClassHandler.sendMessage(localMessage);
                    return;
                }
                catch (Exception localException)
                {
                    localException.printStackTrace();
                }
            }
        }.start();
    }

    public void getVideoFirstClass()
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Object localObject = ConnWeb.ConnForJson(VideoClassActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/class/VideoFirstClassAjax", null, "GET", 0, null, null);
                    System.out.println("================ddddddd================");
                    VideoClassActivity.this.list = ((List)JsonUtil.getGson().fromJson((String)localObject, new TypeToken() {}.getType()));
                    localObject = new Message();
                    ((Message)localObject).obj = VideoClassActivity.this.list;
                    VideoClassActivity.this.setVideoFirstClassHandler.sendMessage((Message)localObject);
                    return;
                }
                catch (Exception localException)
                {
                    localException.printStackTrace();
                }
            }
        }.start();
    }

    public void getWindowData()
    {
        this.windowWidth = getWindowManager().getDefaultDisplay().getWidth();
        this.windowHeight = getWindowManager().getDefaultDisplay().getHeight();
        if (this.windowHeight < 800) {
            this.secClassHeight = 60;
        }
        do
        {
            return;
            if ((this.windowHeight >= 800) && (this.windowHeight < 1040))
            {
                this.secClassHeight = 60;
                return;
            }
            if ((this.windowHeight >= 1040) && (this.windowHeight < 1600))
            {
                this.secClassHeight = 80;
                return;
            }
            if ((this.windowHeight >= 1600) && (this.windowHeight < 1950))
            {
                this.secClassHeight = 120;
                return;
            }
            if ((this.windowHeight >= 1950) && (this.windowHeight < 2200))
            {
                this.secClassHeight = 140;
                return;
            }
        } while ((this.windowHeight < 2200) || (this.windowHeight >= 2600));
        this.secClassHeight = 160;
    }

    public void init()
    {
        this.iv_videoclass_hintpic = ((ImageView)findViewById(2131361882));
        this.fl_videoclass = ((FrameLayout)findViewById(2131361883));
        this.class_but = ((Button)findViewById(2131361801));
        this.class_but.setBackgroundResource(2130903051);
        this.videoClassListView = ((ListView)findViewById(2131361881));
        this.fm = getFragmentManager();
        HeaderMenu localHeaderMenu = new HeaderMenu(this, false, "������", null);
        this.backbut = localHeaderMenu.getbackBut();
        localHeaderMenu.initHeaderBut();
        new FooterMenu(this).initFooterBut();
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130968595);
        init();
        getWindowData();
        getVideoFirstClass();
        this.setVideoFirstClassHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                VideoFirstClassAdapter localVideoFirstClassAdapter = new VideoFirstClassAdapter(VideoClassActivity.this, (List)paramAnonymousMessage.obj);
                VideoClassActivity.this.videoClassListView.setAdapter(localVideoFirstClassAdapter);
                VideoClassActivity.this.firstItemClick();
                super.handleMessage(paramAnonymousMessage);
            }
        };
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
    {
        if (paramInt == 4) {
            showExitDialog();
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    public void secItemClick()
    {
        this.lv_second_class.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                VideoClassActivity.this.tv_sec_class = ((TextView)paramAnonymousView.findViewById(2131361958));
                paramAnonymousAdapterView = (TextView)paramAnonymousView.findViewById(2131361957);
                VideoClassActivity.this.getThreeClassInfo((String)paramAnonymousAdapterView.getText());
                VideoClassActivity.this.setVideoThreeClassHandler = new Handler()
                {
                    public void handleMessage(Message paramAnonymous2Message)
                    {
                        VideoClassActivity.this.iv_videoclass_hintpic.setVisibility(8);
                        VideoClassActivity.this.fl_videoclass.setVisibility(0);
                        VideoClassActivity.this.ft = VideoClassActivity.this.fm.beginTransaction();
                        VideoClassActivity.this.ft.replace(2131361883, new ThreeClassFragment(VideoClassActivity.this, (List)paramAnonymous2Message.obj));
                        VideoClassActivity.this.ft.commit();
                        super.handleMessage(paramAnonymous2Message);
                    }
                };
            }
        });
    }
}
