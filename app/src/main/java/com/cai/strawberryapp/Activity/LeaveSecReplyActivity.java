package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cai.strawberryapp.Adapter.SecReplyAdapter;
import com.cai.strawberryapp.BaseClass.ConnWeb;
import com.cai.strawberryapp.BaseClass.JsonUtil;
import com.cai.strawberryapp.VO.LeaveSecAnsVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class LeaveSecReplyActivity
        extends Activity
{
    private SecReplyAdapter adapter;
    String audioPath;
    String audioTime;
    private Button backbut;
    private Button but_sec_ans_submit;
    private EditText et_reply;
    private boolean flagEdit = true;
    private LinearLayout footer;
    private List<Bitmap> fristReplyImgList;
    private Handler getAnsMoreImgPathHandler;
    private RelativeLayout header;
    private TextView headercontent;
    private ListView lv_sec_ans_listview;
    private Map<String, String> map;
    private Handler setSecReplyListViewHandler;
    private Map<String, String> submitMap = new HashMap();
    private Handler submitSecResultHandler;
    String tv_first_reply_content;
    String tv_first_reply_id;
    String tv_first_reply_time;
    String tv_first_reply_user;
    private ProgressDialog waitingDialog;

    public void ScrollViewClick()
    {
        this.lv_sec_ans_listview.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
            {
                if (paramAnonymousMotionEvent.getAction() == 0)
                {
                    paramAnonymousView = (InputMethodManager)LeaveSecReplyActivity.this.getSystemService("input_method");
                    if ((LeaveSecReplyActivity.this.getCurrentFocus() != null) && (LeaveSecReplyActivity.this.getCurrentFocus().getWindowToken() != null)) {
                        paramAnonymousView.hideSoftInputFromWindow(LeaveSecReplyActivity.this.getCurrentFocus().getWindowToken(), 2);
                    }
                }
                return false;
            }
        });
    }

    public void backButClick()
    {
        this.backbut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                paramAnonymousView = LeaveSecReplyActivity.this.adapter.getMediaPlayer();
                if ((paramAnonymousView != null) && (paramAnonymousView.isPlaying())) {
                    paramAnonymousView.stop();
                }
                LeaveSecReplyActivity.this.finish();
            }
        });
    }

    public void et_replyClick()
    {
        this.et_reply.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (LeaveSecReplyActivity.this.flagEdit)
                {
                    ((InputMethodManager)LeaveSecReplyActivity.this.getSystemService("input_method")).toggleSoftInput(0, 2);
                    LeaveSecReplyActivity.this.flagEdit = false;
                    LeaveSecReplyActivity.this.et_reply.setFocusable(true);
                    LeaveSecReplyActivity.this.et_reply.setFocusableInTouchMode(true);
                    LeaveSecReplyActivity.this.et_reply.requestFocus();
                    LeaveSecReplyActivity.this.setHeaderHeight();
                    LeaveSecReplyActivity.this.setFooterHeight();
                    LeaveSecReplyActivity.this.et_reply.requestFocusFromTouch();
                    return;
                }
                LeaveSecReplyActivity.this.et_reply.setFocusable(true);
                LeaveSecReplyActivity.this.et_reply.setFocusableInTouchMode(true);
                LeaveSecReplyActivity.this.et_reply.requestFocus();
                LeaveSecReplyActivity.this.setHeaderHeight();
                LeaveSecReplyActivity.this.setFooterHeight();
                LeaveSecReplyActivity.this.et_reply.requestFocusFromTouch();
            }
        });
    }

    public void getAnsMoreImgPath()
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Object localObject = ConnWeb.ConnForJson(LeaveSecReplyActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/MoreAnsImgPathAjax", "fristAnsId=" + LeaveSecReplyActivity.this.tv_first_reply_id, "GET", 0, null, null);
                    System.out.println("================ddddddd================");
                    localObject = (List)JsonUtil.getGson().fromJson((String)localObject, new TypeToken() {}.getType());
                    int i = 0;
                    for (;;)
                    {
                        if (i >= ((List)localObject).size())
                        {
                            Message localMessage = new Message();
                            localMessage.obj = localObject;
                            LeaveSecReplyActivity.this.getAnsMoreImgPathHandler.sendMessage(localMessage);
                            return;
                        }
                        LeaveSecReplyActivity.this.fristReplyImgList.add(LeaveSecReplyActivity.this.returnBitMap("http://118.190.155.221:8098/StrawAdminWeb/resources/img/leaveuserimg/ansimg/" + (String)((List)localObject).get(i)));
                        i += 1;
                    }
                    return;
                }
                catch (Exception localException)
                {
                    localException.printStackTrace();
                }
            }
        }.start();
    }

    public void getSecReplyInfo()
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Object localObject = ConnWeb.ConnForJson(LeaveSecReplyActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/SecReplyInfoAjax", "fristAnsId=" + LeaveSecReplyActivity.this.tv_first_reply_id, "GET", 0, null, null);
                    System.out.println("================ddddddd================");
                    localObject = (List)JsonUtil.getGson().fromJson((String)localObject, new TypeToken() {}.getType());
                    Message localMessage = new Message();
                    localMessage.obj = localObject;
                    LeaveSecReplyActivity.this.setSecReplyListViewHandler.sendMessage(localMessage);
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
        Bundle localBundle = getIntent().getExtras();
        this.tv_first_reply_id = localBundle.get("tv_first_reply_id").toString();
        this.tv_first_reply_user = localBundle.get("tv_first_reply_user").toString();
        this.tv_first_reply_content = localBundle.get("tv_first_reply_content").toString();
        this.tv_first_reply_time = localBundle.get("tv_first_reply_time").toString();
        this.audioPath = localBundle.get("ansAudioPath").toString();
        this.audioTime = localBundle.get("ansAudioTime").toString();
        this.map = new HashMap();
        this.fristReplyImgList = new ArrayList();
        this.map.put("tv_first_reply_id", this.tv_first_reply_id);
        this.map.put("tv_first_reply_user", this.tv_first_reply_user);
        this.map.put("tv_first_reply_content", this.tv_first_reply_content);
        this.map.put("tv_first_reply_time", this.tv_first_reply_time);
        this.map.put("audioPath", this.audioPath);
        this.map.put("audioTime", this.audioTime);
        this.lv_sec_ans_listview = ((ListView)findViewById(2131361834));
        this.et_reply = ((EditText)findViewById(2131361836));
        this.header = ((RelativeLayout)findViewById(2131361833));
        this.backbut = ((Button)findViewById(2131361814));
        this.headercontent = ((TextView)findViewById(2131361815));
        this.headercontent.setText("");
        this.footer = ((LinearLayout)findViewById(2131361835));
        this.but_sec_ans_submit = ((Button)findViewById(2131361837));
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130968585);
        init();
        getAnsMoreImgPath();
        backButClick();
        et_replyClick();
        ScrollViewClick();
        submitClick();
        this.getAnsMoreImgPathHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                LeaveSecReplyActivity.this.getSecReplyInfo();
                super.handleMessage(paramAnonymousMessage);
            }
        };
        this.setSecReplyListViewHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                LeaveSecReplyActivity.this.adapter = new SecReplyAdapter(LeaveSecReplyActivity.this, (List)paramAnonymousMessage.obj, LeaveSecReplyActivity.this.map, LeaveSecReplyActivity.this.fristReplyImgList);
                LeaveSecReplyActivity.this.lv_sec_ans_listview.setAdapter(LeaveSecReplyActivity.this.adapter);
                super.handleMessage(paramAnonymousMessage);
            }
        };
        this.submitSecResultHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                Intent localIntent;
                Bundle localBundle;
                if (paramAnonymousMessage.obj.equals("200"))
                {
                    LeaveSecReplyActivity.this.waitingDialog.dismiss();
                    Toast.makeText(LeaveSecReplyActivity.this, "���������������", 1000).show();
                    localIntent = new Intent(LeaveSecReplyActivity.this, LeaveSecReplyActivity.class);
                    localBundle = new Bundle();
                    localBundle.putString("tv_first_reply_id", LeaveSecReplyActivity.this.tv_first_reply_id);
                    localBundle.putString("tv_first_reply_user", LeaveSecReplyActivity.this.tv_first_reply_user);
                    localBundle.putString("tv_first_reply_content", LeaveSecReplyActivity.this.tv_first_reply_content);
                    localBundle.putString("tv_first_reply_time", LeaveSecReplyActivity.this.tv_first_reply_time);
                    localBundle.putString("ansAudioPath", LeaveSecReplyActivity.this.audioPath);
                    localBundle.putString("ansAudioTime", LeaveSecReplyActivity.this.audioTime);
                    localIntent.putExtras(localBundle);
                    LeaveSecReplyActivity.this.startActivity(localIntent);
                    LeaveSecReplyActivity.this.finish();
                }
                for (;;)
                {
                    super.handleMessage(paramAnonymousMessage);
                    return;
                    if (paramAnonymousMessage.obj.equals("-1"))
                    {
                        LeaveSecReplyActivity.this.waitingDialog.dismiss();
                        Toast.makeText(LeaveSecReplyActivity.this, "������������������������������", 1000).show();
                        localIntent = new Intent(LeaveSecReplyActivity.this, LeaveSecReplyActivity.class);
                        localBundle = new Bundle();
                        localBundle.putString("tv_first_reply_id", LeaveSecReplyActivity.this.tv_first_reply_id);
                        localBundle.putString("tv_first_reply_user", LeaveSecReplyActivity.this.tv_first_reply_user);
                        localBundle.putString("tv_first_reply_content", LeaveSecReplyActivity.this.tv_first_reply_content);
                        localBundle.putString("tv_first_reply_time", LeaveSecReplyActivity.this.tv_first_reply_time);
                        localBundle.putString("ansAudioPath", LeaveSecReplyActivity.this.audioPath);
                        localBundle.putString("ansAudioTime", LeaveSecReplyActivity.this.audioTime);
                        localIntent.putExtras(localBundle);
                        LeaveSecReplyActivity.this.startActivity(localIntent);
                        LeaveSecReplyActivity.this.finish();
                    }
                }
            }
        };
    }

    protected void onPause()
    {
        MediaPlayer localMediaPlayer = this.adapter.getMediaPlayer();
        if (localMediaPlayer != null)
        {
            localMediaPlayer.stop();
            localMediaPlayer.release();
        }
        super.onPause();
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
        if (paramMotionEvent.getAction() == 0)
        {
            InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
            if ((getCurrentFocus() != null) && (getCurrentFocus().getWindowToken() != null)) {
                localInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            }
        }
        return super.onTouchEvent(paramMotionEvent);
    }

    /* Error */
    public Bitmap returnBitMap(String paramString)
    {
        // Byte code:
        //   0: aconst_null
        //   1: astore_2
        //   2: aconst_null
        //   3: astore_3
        //   4: new 317	java/net/URL
        //   7: dup
        //   8: aload_1
        //   9: invokespecial 320	java/net/URL:<init>	(Ljava/lang/String;)V
        //   12: astore 4
        //   14: aload_2
        //   15: astore_1
        //   16: aload 4
        //   18: invokevirtual 324	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   21: checkcast 326	java/net/HttpURLConnection
        //   24: astore_3
        //   25: aload_2
        //   26: astore_1
        //   27: aload_3
        //   28: iconst_1
        //   29: invokevirtual 330	java/net/HttpURLConnection:setDoInput	(Z)V
        //   32: aload_2
        //   33: astore_1
        //   34: aload_3
        //   35: iconst_0
        //   36: invokevirtual 333	java/net/HttpURLConnection:setUseCaches	(Z)V
        //   39: aload_2
        //   40: astore_1
        //   41: aload_3
        //   42: invokevirtual 336	java/net/HttpURLConnection:connect	()V
        //   45: aload_2
        //   46: astore_1
        //   47: aload_3
        //   48: invokevirtual 340	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
        //   51: astore_3
        //   52: aload_2
        //   53: astore_1
        //   54: aload_3
        //   55: invokestatic 346	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   58: astore_2
        //   59: aload_2
        //   60: astore_1
        //   61: aload_3
        //   62: invokevirtual 351	java/io/InputStream:close	()V
        //   65: aload_2
        //   66: areturn
        //   67: astore_2
        //   68: aload_3
        //   69: astore_1
        //   70: aload_2
        //   71: invokevirtual 354	java/io/IOException:printStackTrace	()V
        //   74: aload_1
        //   75: areturn
        //   76: astore_2
        //   77: goto -7 -> 70
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	LeaveSecReplyActivity
        //   0	80	1	paramString	String
        //   1	65	2	localBitmap	Bitmap
        //   67	4	2	localIOException1	java.io.IOException
        //   76	1	2	localIOException2	java.io.IOException
        //   3	66	3	localObject	Object
        //   12	5	4	localURL	java.net.URL
        // Exception table:
        //   from	to	target	type
        //   4	14	67	java/io/IOException
        //   16	25	76	java/io/IOException
        //   27	32	76	java/io/IOException
        //   34	39	76	java/io/IOException
        //   41	45	76	java/io/IOException
        //   47	52	76	java/io/IOException
        //   54	59	76	java/io/IOException
        //   61	65	76	java/io/IOException
    }

    public void setFooterHeight()
    {
        this.footer.measure(0, 0);
        this.footer.setLayoutParams(new LinearLayout.LayoutParams(this.footer.getWidth(), this.footer.getHeight(), 0.0F));
    }

    public void setHeaderHeight()
    {
        this.header.measure(0, 0);
        this.header.setLayoutParams(new LinearLayout.LayoutParams(this.header.getWidth(), this.header.getHeight(), 0.0F));
    }

    public void submitClick()
    {
        this.but_sec_ans_submit.setOnClickListener(new View.OnClickListener()
        {
            private void submitReplyWeb()
            {
                new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            String str = ConnWeb.ConnForJson(LeaveSecReplyActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/SubmitSecReplyAjax", "SubmitSecReply=" + this.val$j, "POST", 0, null, null);
                            System.out.println("================xxxxxxxx================");
                            Message localMessage = new Message();
                            localMessage.obj = str;
                            LeaveSecReplyActivity.this.submitSecResultHandler.sendMessage(localMessage);
                            return;
                        }
                        catch (Exception localException)
                        {
                            localException.printStackTrace();
                        }
                    }
                }.start();
            }

            public void onClick(View paramAnonymousView)
            {
                if ((LeaveSecReplyActivity.this.et_reply.getText().toString() == null) || (LeaveSecReplyActivity.this.et_reply.getText().toString().equals("")))
                {
                    Toast.makeText(LeaveSecReplyActivity.this, "���������������������������������", 1000).show();
                    return;
                }
                LeaveSecReplyActivity.this.waitingDialog = new ProgressDialog(LeaveSecReplyActivity.this);
                LeaveSecReplyActivity.this.waitingDialog.setTitle("������������������");
                LeaveSecReplyActivity.this.waitingDialog.setMessage("���������...");
                LeaveSecReplyActivity.this.waitingDialog.setIndeterminate(true);
                LeaveSecReplyActivity.this.waitingDialog.setCancelable(false);
                LeaveSecReplyActivity.this.waitingDialog.show();
                LeaveSecReplyActivity.this.submitMap.put("content", LeaveSecReplyActivity.this.et_reply.getText().toString());
                try
                {
                    paramAnonymousView = new FileInputStream(new File(LeaveSecReplyActivity.this.getCacheDir(), "userInfo.properties"));
                    Properties localProperties = new Properties();
                    localProperties.load(paramAnonymousView);
                    paramAnonymousView.close();
                    LeaveSecReplyActivity.this.submitMap.put("user", localProperties.getProperty("username"));
                    LeaveSecReplyActivity.this.submitMap.put("fristReplyId", LeaveSecReplyActivity.this.tv_first_reply_id);
                    submitReplyWeb();
                    return;
                }
                catch (Exception paramAnonymousView)
                {
                    for (;;)
                    {
                        paramAnonymousView.printStackTrace();
                    }
                }
            }
        });
    }
}
