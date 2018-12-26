package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cai.strawberryapp.Adapter.FristReplyAdapter;
import com.cai.strawberryapp.BaseClass.ConnWeb;
import com.cai.strawberryapp.BaseClass.IDUtil;
import com.cai.strawberryapp.BaseClass.JsonUtil;
import com.cai.strawberryapp.BaseClass.TimeFormat;
import com.cai.strawberryapp.BaseClass.UriToPath;
import com.cai.strawberryapp.VO.LeaveFristAnsVO;
import com.cai.strawberryapp.VO.LeaveSecAnsVO;
import com.cai.strawberryapp.View.OnRefreshListener;
import com.cai.strawberryapp.View.RefreshListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.http.Header;

public class LeaveDetailActivity
        extends Activity
        implements OnRefreshListener
{
    private FristReplyAdapter adapter;
    private int all_pic_num = 0;
    private String audioPath;
    private String audioTime;
    private Button backbut;
    private Button bt_frist_reply_add_audio;
    private Button bt_frist_reply_new_audio;
    private Button but_add_pic;
    private Button but_submit;
    private CountDownTimer cdt1 = null;
    private EditText et_reply;
    private File file;
    private FrameLayout fl_add_pic;
    int flag = 0;
    private boolean flag1 = false;
    private boolean flagAddImg = true;
    private boolean flagEdit = true;
    private LinearLayout footer;
    private List<byte[]> fristReplyImgList;
    private RelativeLayout header;
    private TextView headercontent;
    private String id = "";
    private ImageView imgNew;
    private boolean isClickAddBut = false;
    private boolean isKeyBoard = false;
    private List<Map<String, Object>> list;
    private LinearLayout ll_add_pic;
    private LinearLayout ll_container;
    private RefreshListView lv_ans_listview;
    private LayoutInflater mInflater;
    private int mLastY = 0;
    private int mYDown = 0;
    private Map<String, String> map;
    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;
    private String password;
    private PopupWindow popupwindow;
    private String queAudioPath;
    private String queAudioTime;
    private String queContent;
    private String queId;
    private String queTime;
    private String queTitle;
    private String queUser;
    private long remainAudioTime = 0L;
    private int requestNum = 0;
    private String result = "";
    private RelativeLayout rl_add_pic;
    private RelativeLayout rl_lunbo_progressbar;
    private Handler setFristReplyHandler;
    private Handler submitAudioResultHandler;
    private List<String> submitContentList;
    private List<byte[]> submitImgList;
    private int submitImgNum = 0;
    private Handler submitImgResultHandler;
    private Handler submitStrResultHandler;
    private Handler synShowAddPicViewHandler;
    private TextView tvNew;
    private String username;
    private ProgressDialog waitingDialog;
    private int windowHeight;
    private int windowWidth;

    private void doView(View paramView)
    {
        paramView = (TextView)paramView;
        if ((paramView.getText().toString().equals("")) || (paramView.getText().toString() == null))
        {
            System.out.println("���");
            return;
        }
        paramView = UriToPath.getRealPathFromUri(this, Uri.parse(paramView.getText().toString()));
        Object localObject = new BitmapFactory.Options();
        ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
        BitmapFactory.decodeFile(paramView, (BitmapFactory.Options)localObject);
        int i = ((BitmapFactory.Options)localObject).outHeight;
        int k = ((BitmapFactory.Options)localObject).outWidth / this.windowWidth;
        int m = i / this.windowHeight;
        int j = 1;
        i = j;
        if (k >= m)
        {
            i = j;
            if (m >= 1) {
                i = k;
            }
        }
        j = i;
        if (m >= k)
        {
            j = i;
            if (k >= 1) {
                j = m;
            }
        }
        ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
        ((BitmapFactory.Options)localObject).inSampleSize = j;
        paramView = BitmapFactory.decodeFile(paramView, (BitmapFactory.Options)localObject);
        localObject = new ByteArrayOutputStream();
        if (paramView.getByteCount() >= 1000000) {
            paramView.compress(Bitmap.CompressFormat.JPEG, 20, (OutputStream)localObject);
        }
        for (;;)
        {
            this.submitImgList.add(((ByteArrayOutputStream)localObject).toByteArray());
            return;
            if ((paramView.getByteCount() > 800000) && (paramView.getByteCount() < 1000000)) {
                paramView.compress(Bitmap.CompressFormat.JPEG, 20, (OutputStream)localObject);
            } else if ((paramView.getByteCount() > 600000) && (paramView.getByteCount() < 800000)) {
                paramView.compress(Bitmap.CompressFormat.JPEG, 20, (OutputStream)localObject);
            } else if ((paramView.getByteCount() > 400000) && (paramView.getByteCount() < 600000)) {
                paramView.compress(Bitmap.CompressFormat.JPEG, 20, (OutputStream)localObject);
            } else if ((paramView.getByteCount() > 200000) && (paramView.getByteCount() < 400000)) {
                paramView.compress(Bitmap.CompressFormat.JPEG, 20, (OutputStream)localObject);
            } else {
                paramView.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream)localObject);
            }
        }
    }

    private void doView1(View paramView)
    {
        paramView.setDrawingCacheEnabled(true);
        Bitmap localBitmap = Bitmap.createBitmap(paramView.getDrawingCache());
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
        this.fristReplyImgList.add(localByteArrayOutputStream.toByteArray());
        paramView.setDrawingCacheEnabled(false);
    }

    public void RLClick(View paramView)
    {
        final TextView localTextView = (TextView)paramView.findViewById(2131361978);
        ((RelativeLayout)paramView.findViewById(2131361831)).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                paramAnonymousView = (String)localTextView.getText();
                Intent localIntent = new Intent(LeaveDetailActivity.this, AllScreamShowActivity.class);
                Bundle localBundle = new Bundle();
                localBundle.putString("uri", paramAnonymousView);
                localIntent.putExtras(localBundle);
                LeaveDetailActivity.this.startActivity(localIntent);
            }
        });
    }

    public void RLLongClick(final View paramView)
    {
        ((RelativeLayout)paramView.findViewById(2131361831)).setOnLongClickListener(new View.OnLongClickListener()
        {
            public boolean onLongClick(View paramAnonymousView)
            {
                paramAnonymousView = LeaveDetailActivity.this;
                paramAnonymousView.all_pic_num -= 1;
                LeaveDetailActivity.this.fl_add_pic.setVisibility(0);
                LeaveDetailActivity.this.ll_add_pic.removeView(paramView);
                return false;
            }
        });
    }

    public void addPicButClick()
    {
        this.but_add_pic.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                paramAnonymousView = (InputMethodManager)LeaveDetailActivity.this.getSystemService("input_method");
                if ((LeaveDetailActivity.this.getCurrentFocus() != null) && (LeaveDetailActivity.this.getCurrentFocus().getWindowToken() != null))
                {
                    paramAnonymousView.hideSoftInputFromWindow(LeaveDetailActivity.this.getCurrentFocus().getWindowToken(), 2);
                    LeaveDetailActivity.this.isKeyBoard = false;
                }
                new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(200L);
                            Message localMessage = new Message();
                            localMessage.obj = "";
                            LeaveDetailActivity.this.synShowAddPicViewHandler.sendMessage(localMessage);
                            return;
                        }
                        catch (InterruptedException localInterruptedException)
                        {
                            localInterruptedException.printStackTrace();
                        }
                    }
                }.start();
                LeaveDetailActivity.this.setHeaderHeight();
                LeaveDetailActivity.this.setFooterHeight();
            }
        });
    }

    public void addPicImgClick(final View paramView) {
        ImageView localImageView = (ImageView)paramView.findViewById(2131361977);
        TextView localTextView = (TextView)paramView.findViewById(2131361978);
        this.imgNew = localImageView;
        this.tvNew = localTextView;
        localImageView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                LeaveDetailActivity.this.RLClick(paramView);
                LeaveDetailActivity.this.RLLongClick(paramView);
                paramAnonymousView = new Intent();
                paramAnonymousView.setAction("android.intent.action.PICK");
                paramAnonymousView.addCategory("android.intent.category.DEFAULT");
                paramAnonymousView.setType("image/*");
                LeaveDetailActivity.this.startActivityForResult(paramAnonymousView, 0);
            }
        });
    }

    public void addPicViewShow()
    {
        this.rl_add_pic.setVisibility(0);
        this.isClickAddBut = true;
        if (this.flagAddImg)
        {
            this.flagAddImg = false;
            View localView = this.mInflater.inflate(2130968618, this.ll_add_pic, false);
            addPicImgClick(localView);
            this.ll_add_pic.addView(localView);
        }
    }

    public void backButClick()
    {
        this.backbut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                paramAnonymousView = LeaveDetailActivity.this.adapter.getMediaPlayer();
                if (paramAnonymousView != null) {
                    paramAnonymousView.stop();
                }
                LeaveDetailActivity.this.finish();
            }
        });
    }

    public void bt_add_audioClick()
    {
        this.bt_frist_reply_add_audio.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (LeaveDetailActivity.this.bt_frist_reply_add_audio.getText().equals("������"))
                {
                    paramAnonymousView = (InputMethodManager)LeaveDetailActivity.this.getSystemService("input_method");
                    if ((LeaveDetailActivity.this.getCurrentFocus() != null) && (LeaveDetailActivity.this.getCurrentFocus().getWindowToken() != null)) {
                        paramAnonymousView.hideSoftInputFromWindow(LeaveDetailActivity.this.getCurrentFocus().getWindowToken(), 2);
                    }
                    LeaveDetailActivity.this.bt_frist_reply_new_audio.setVisibility(0);
                    LeaveDetailActivity.this.et_reply.setVisibility(8);
                    LeaveDetailActivity.this.bt_frist_reply_add_audio.setBackgroundResource(2130903047);
                    LeaveDetailActivity.this.bt_frist_reply_add_audio.setText("������");
                    return;
                }
                LeaveDetailActivity.this.bt_frist_reply_new_audio.setVisibility(8);
                LeaveDetailActivity.this.et_reply.setVisibility(0);
                LeaveDetailActivity.this.bt_frist_reply_new_audio.setText("������������������");
                new File(LeaveDetailActivity.this.getCacheDir(), "/audio.mp3").delete();
                LeaveDetailActivity.this.bt_frist_reply_add_audio.setBackgroundResource(2130903041);
                LeaveDetailActivity.this.bt_frist_reply_add_audio.setText("������");
            }
        });
    }

    public void bt_frist_reply_new_audioClick()
    {
        this.bt_frist_reply_new_audio.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
            {
                switch (paramAnonymousMotionEvent.getAction())
                {
                }
                label53:
                label503:
                label782:
                for (;;)
                {
                    return false;
                    int i;
                    if (LeaveDetailActivity.this.getPackageManager().checkPermission("android.permission.RECORD_AUDIO", "com.cai.strawberryapp") == 0)
                    {
                        i = 1;
                        if (i == 0) {
                            break label503;
                        }
                        LeaveDetailActivity.this.flag1 = true;
                        paramAnonymousView = new File(LeaveDetailActivity.this.getCacheDir(), "/audio.mp3");
                        if ((paramAnonymousView != null) && (paramAnonymousView.exists())) {}
                    }
                    else
                    {
                        try
                        {
                            LeaveDetailActivity.this.popupwindow = new PopupWindow();
                            paramAnonymousView = LeaveDetailActivity.this.getLayoutInflater().inflate(2130968607, null);
                            LeaveDetailActivity.this.popupwindow = new PopupWindow(paramAnonymousView, 500, 100, true);
                            LeaveDetailActivity.this.popupwindow.setTouchable(true);
                            LeaveDetailActivity.this.popupwindow.setOutsideTouchable(true);
                            LeaveDetailActivity.this.popupwindow.showAsDropDown(LeaveDetailActivity.this.footer, LeaveDetailActivity.this.footer.getWidth() / 2 - 250, -LeaveDetailActivity.this.footer.getHeight() - 300);
                            LeaveDetailActivity.this.mediaRecorder = new MediaRecorder();
                            LeaveDetailActivity.this.mediaRecorder.setAudioSource(1);
                            LeaveDetailActivity.this.mediaRecorder.setOutputFormat(0);
                            LeaveDetailActivity.this.mediaRecorder.setOutputFile(LeaveDetailActivity.this.audioPath);
                            LeaveDetailActivity.this.mediaRecorder.setAudioEncoder(0);
                            LeaveDetailActivity.this.mediaRecorder.prepare();
                            LeaveDetailActivity.this.mediaRecorder.start();
                            LeaveDetailActivity.this.bt_frist_reply_new_audio.setText("������������...");
                            LeaveDetailActivity.this.cdt1 = new CountDownTimer(60000L, 1000L)
                            {
                                public void onFinish()
                                {
                                    System.out.println("������������");
                                    LeaveDetailActivity.this.popupwindow.dismiss();
                                    LeaveDetailActivity.this.remainAudioTime = 0L;
                                    Toast.makeText(LeaveDetailActivity.this, "���������������������1.0������", 1000).show();
                                    LeaveDetailActivity.this.mediaRecorder.stop();
                                    LeaveDetailActivity.this.mediaRecorder.release();
                                    LeaveDetailActivity.this.mediaRecorder = null;
                                }

                                public void onTick(long paramAnonymous2Long)
                                {
                                    LeaveDetailActivity.this.remainAudioTime = paramAnonymous2Long;
                                }
                            };
                            LeaveDetailActivity.this.cdt1.start();
                        }
                        catch (IllegalStateException paramAnonymousView)
                        {
                            paramAnonymousView.printStackTrace();
                            continue;
                            i = 0;
                            break label53;
                        }
                        catch (IOException paramAnonymousView)
                        {
                            paramAnonymousView.printStackTrace();
                        }
                        continue;
                    }
                    try
                    {
                        if ((LeaveDetailActivity.this.mediaPlayer != null) && (LeaveDetailActivity.this.mediaPlayer.isPlaying()))
                        {
                            LeaveDetailActivity.this.mediaPlayer.stop();
                            LeaveDetailActivity.this.mediaPlayer.release();
                            LeaveDetailActivity.this.mediaPlayer = null;
                        }
                        LeaveDetailActivity.this.mediaPlayer = new MediaPlayer();
                        LeaveDetailActivity.this.mediaPlayer.setAudioStreamType(3);
                        LeaveDetailActivity.this.mediaPlayer.setDataSource(LeaveDetailActivity.this.audioPath);
                        LeaveDetailActivity.this.mediaPlayer.prepare();
                        LeaveDetailActivity.this.mediaPlayer.start();
                    }
                    catch (Exception paramAnonymousView)
                    {
                        Toast.makeText(LeaveDetailActivity.this, "������������������", 1000).show();
                        paramAnonymousView.printStackTrace();
                    }
                    continue;
                    LeaveDetailActivity.this.flag1 = false;
                    Toast.makeText(LeaveDetailActivity.this, "���������������������������", 1000).show();
                    continue;
                    if (LeaveDetailActivity.this.flag1)
                    {
                        System.out.println("������");
                        if (LeaveDetailActivity.this.remainAudioTime >= 59000.0D)
                        {
                            LeaveDetailActivity.this.bt_frist_reply_new_audio.setText("������������������");
                            LeaveDetailActivity.this.mediaRecorder.stop();
                            LeaveDetailActivity.this.mediaRecorder.release();
                            LeaveDetailActivity.this.mediaRecorder = null;
                            LeaveDetailActivity.this.popupwindow.dismiss();
                            new File(LeaveDetailActivity.this.getCacheDir(), "/audio.mp3").delete();
                            Toast.makeText(LeaveDetailActivity.this, "���������������������", 1000).show();
                        }
                        for (;;)
                        {
                            if (LeaveDetailActivity.this.cdt1 == null) {
                                break label782;
                            }
                            LeaveDetailActivity.this.cdt1.cancel();
                            break;
                            LeaveDetailActivity.this.popupwindow.dismiss();
                            paramAnonymousView = TimeFormat.timeFormat(60000.0D - LeaveDetailActivity.this.remainAudioTime);
                            paramAnonymousView = paramAnonymousView.substring(3, paramAnonymousView.length());
                            LeaveDetailActivity.this.audioTime = paramAnonymousView;
                            LeaveDetailActivity.this.bt_frist_reply_new_audio.setText("������������:" + paramAnonymousView);
                            if (LeaveDetailActivity.this.mediaRecorder != null)
                            {
                                LeaveDetailActivity.this.mediaRecorder.stop();
                                LeaveDetailActivity.this.mediaRecorder.release();
                                LeaveDetailActivity.this.mediaRecorder = null;
                            }
                        }
                    }
                }
            }
        });
    }

    public void et_replyClick()
    {
        this.et_reply.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                LeaveDetailActivity.this.isKeyBoard = true;
                if (LeaveDetailActivity.this.rl_add_pic.getVisibility() == 0) {
                    LeaveDetailActivity.this.rl_add_pic.setVisibility(8);
                }
                if (LeaveDetailActivity.this.flagEdit)
                {
                    ((InputMethodManager)LeaveDetailActivity.this.getSystemService("input_method")).toggleSoftInput(0, 2);
                    LeaveDetailActivity.this.flagEdit = false;
                    LeaveDetailActivity.this.et_reply.setFocusable(true);
                    LeaveDetailActivity.this.et_reply.setFocusableInTouchMode(true);
                    LeaveDetailActivity.this.et_reply.requestFocus();
                    LeaveDetailActivity.this.setHeaderHeight();
                    LeaveDetailActivity.this.setFooterHeight();
                    LeaveDetailActivity.this.et_reply.requestFocusFromTouch();
                    return;
                }
                LeaveDetailActivity.this.et_reply.setFocusable(true);
                LeaveDetailActivity.this.et_reply.setFocusableInTouchMode(true);
                LeaveDetailActivity.this.et_reply.requestFocus();
                LeaveDetailActivity.this.setHeaderHeight();
                LeaveDetailActivity.this.setFooterHeight();
                LeaveDetailActivity.this.et_reply.requestFocusFromTouch();
            }
        });
    }

    public void getFristReplyInfo()
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    HashMap localHashMap1 = new HashMap();
                    Object localObject1 = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/MoreQueImgAjax", "queId=" + LeaveDetailActivity.this.queId, "GET", 0, null, null);
                    System.out.println("================eeeeeee================");
                    localObject1 = (List)JsonUtil.getGson().fromJson((String)localObject1, new TypeToken() {}.getType());
                    Object localObject2 = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/FristReplyInfoAjax", "queId=" + LeaveDetailActivity.this.queId + "&flag=vo&requestNum=0", "POST", 0, null, null);
                    System.out.println("================voResult================");
                    localObject2 = (List)JsonUtil.getGson().fromJson((String)localObject2, new TypeToken() {}.getType());
                    int i = 0;
                    for (;;)
                    {
                        if (i >= ((List)localObject2).size())
                        {
                            localHashMap1.put("fristList", localObject1);
                            localHashMap1.put("list", LeaveDetailActivity.this.list);
                            localObject1 = new Message();
                            ((Message)localObject1).obj = localHashMap1;
                            LeaveDetailActivity.this.setFristReplyHandler.sendMessage((Message)localObject1);
                            return;
                        }
                        HashMap localHashMap2 = new HashMap();
                        Object localObject3 = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/FristReplyInfoAjax", "fristAnsId=" + ((LeaveFristAnsVO)((List)localObject2).get(i)).getFristAnsId() + "&queId=" + LeaveDetailActivity.this.queId + "&flag=img&requestNum=0", "POST", 0, null, null);
                        System.out.println("================imgResult================");
                        localObject3 = (List)JsonUtil.getGson().fromJson((String)localObject3, new TypeToken() {}.getType());
                        Object localObject4 = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/FristReplyInfoAjax", "fristAnsId=" + ((LeaveFristAnsVO)((List)localObject2).get(i)).getFristAnsId() + "&queId=" + LeaveDetailActivity.this.queId + "&flag=secReply&requestNum=0", "POST", 0, null, null);
                        System.out.println("================secReplyResult================");
                        localObject4 = (List)JsonUtil.getGson().fromJson((String)localObject4, new TypeToken() {}.getType());
                        localHashMap2.put("vo", ((List)localObject2).get(i));
                        localHashMap2.put("imgList", localObject3);
                        localHashMap2.put("secReplyList", localObject4);
                        LeaveDetailActivity.this.list.add(localHashMap2);
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

    public void getWindowData()
    {
        this.windowWidth = getWindowManager().getDefaultDisplay().getWidth();
        this.windowHeight = getWindowManager().getDefaultDisplay().getHeight();
    }

    public void init()
    {
        try
        {
            Object localObject = new FileInputStream(new File(getCacheDir(), "userInfo.properties"));
            Properties localProperties = new Properties();
            localProperties.load((InputStream)localObject);
            ((FileInputStream)localObject).close();
            this.username = localProperties.getProperty("username");
            this.password = localProperties.getProperty("password");
            this.audioPath = (getCacheDir() + "/audio.mp3");
            this.bt_frist_reply_add_audio = ((Button)findViewById(2131361827));
            this.bt_frist_reply_new_audio = ((Button)findViewById(2131361828));
            this.list = new ArrayList();
            this.rl_lunbo_progressbar = ((RelativeLayout)findViewById(2131361817));
            this.ll_container = ((LinearLayout)findViewById(2131361821));
            this.but_add_pic = ((Button)findViewById(2131361826));
            this.but_submit = ((Button)findViewById(2131361830));
            this.et_reply = ((EditText)findViewById(2131361829));
            this.header = ((RelativeLayout)findViewById(2131361806));
            this.backbut = ((Button)findViewById(2131361814));
            this.headercontent = ((TextView)findViewById(2131361815));
            this.headercontent.setText("");
            this.rl_add_pic = ((RelativeLayout)findViewById(2131361831));
            this.footer = ((LinearLayout)findViewById(2131361824));
            this.ll_add_pic = ((LinearLayout)findViewById(2131361832));
            this.lv_ans_listview = ((RefreshListView)findViewById(2131361825));
            this.mInflater = LayoutInflater.from(this);
            this.submitImgList = new ArrayList();
            this.submitContentList = new ArrayList();
            localObject = getIntent().getExtras();
            this.queId = ((Bundle)localObject).get("queId").toString();
            this.queUser = ((Bundle)localObject).get("queUser").toString();
            this.queTime = ((Bundle)localObject).get("queTime").toString();
            this.queTitle = ((Bundle)localObject).get("queTitle").toString();
            this.queContent = ((Bundle)localObject).get("queContent").toString();
            this.queAudioTime = ((Bundle)localObject).get("queAudioTime").toString();
            this.queAudioPath = ((Bundle)localObject).get("queAudioPath").toString();
            this.map = new HashMap();
            this.map.put("tv_que_detail_user", ((Bundle)localObject).get("queUser").toString());
            this.map.put("tv_que_detail_time", ((Bundle)localObject).get("queTime").toString());
            this.map.put("tv_que_detail_title", ((Bundle)localObject).get("queTitle").toString());
            this.map.put("tv_que_detail_content", ((Bundle)localObject).get("queContent").toString());
            this.map.put("queAudioTime", this.queAudioTime);
            this.map.put("queAudioPath", this.queAudioPath);
            return;
        }
        catch (Exception localException)
        {
            for (;;)
            {
                localException.printStackTrace();
            }
        }
    }

    public void listviewItemClick()
    {
        this.lv_ans_listview.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
            {
                switch (paramAnonymousMotionEvent.getAction())
                {
                }
                for (;;)
                {
                    if ((LeaveDetailActivity.this.mYDown - LeaveDetailActivity.this.mLastY < 10) && (LeaveDetailActivity.this.mYDown - LeaveDetailActivity.this.mLastY > -10)) {
                        LeaveDetailActivity.this.lv_ans_listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
                            {
                                if (LeaveDetailActivity.this.isKeyBoard)
                                {
                                    LeaveDetailActivity.this.isKeyBoard = false;
                                    paramAnonymous2AdapterView = (InputMethodManager)LeaveDetailActivity.this.getSystemService("input_method");
                                    if ((LeaveDetailActivity.this.getCurrentFocus() != null) && (LeaveDetailActivity.this.getCurrentFocus().getWindowToken() != null)) {
                                        paramAnonymous2AdapterView.hideSoftInputFromWindow(LeaveDetailActivity.this.getCurrentFocus().getWindowToken(), 2);
                                    }
                                }
                                while (paramAnonymous2Int <= 1) {
                                    return;
                                }
                                LeaveDetailActivity.this.fristReplyImgList = new ArrayList();
                                paramAnonymous2AdapterView = (TextView)paramAnonymous2View.findViewById(2131361924);
                                TextView localTextView1 = (TextView)paramAnonymous2View.findViewById(2131361926);
                                TextView localTextView2 = (TextView)paramAnonymous2View.findViewById(2131361927);
                                Object localObject = (LinearLayout)paramAnonymous2View.findViewById(2131361931);
                                localObject = (TextView)paramAnonymous2View.findViewById(2131361932);
                                Button localButton = (Button)paramAnonymous2View.findViewById(2131361929);
                                paramAnonymous2View = (TextView)paramAnonymous2View.findViewById(2131361930);
                                Intent localIntent = new Intent(LeaveDetailActivity.this, LeaveSecReplyActivity.class);
                                Bundle localBundle = new Bundle();
                                localBundle.putString("tv_first_reply_id", paramAnonymous2AdapterView.getText().toString());
                                localBundle.putString("tv_first_reply_user", localTextView1.getText().toString());
                                localBundle.putString("tv_first_reply_content", localTextView2.getText().toString());
                                localBundle.putString("tv_first_reply_time", ((TextView)localObject).getText().toString());
                                localBundle.putString("ansAudioPath", localButton.getText().toString());
                                localBundle.putString("ansAudioTime", paramAnonymous2View.getText().toString());
                                localIntent.putExtras(localBundle);
                                LeaveDetailActivity.this.startActivity(localIntent);
                            }
                        });
                    }
                    return false;
                    LeaveDetailActivity.this.mYDown = ((int)paramAnonymousMotionEvent.getRawY());
                    continue;
                    LeaveDetailActivity.this.mLastY = ((int)paramAnonymousMotionEvent.getRawY());
                }
            }
        });
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        if (paramInt2 != 0)
        {
            this.imgNew.setClickable(false);
            this.all_pic_num += 1;
            Object localObject1 = paramIntent.getData();
            Object localObject2 = UriToPath.getRealPathFromUri(this, (Uri)localObject1);
            BitmapFactory.Options localOptions = new BitmapFactory.Options();
            localOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile((String)localObject2, localOptions);
            int i = localOptions.outHeight;
            int k = localOptions.outWidth / this.windowWidth;
            int m = i / this.windowHeight;
            int j = 1;
            i = j;
            if (k >= m)
            {
                i = j;
                if (m >= 1) {
                    i = k;
                }
            }
            j = i;
            if (m >= k)
            {
                j = i;
                if (k >= 1) {
                    j = m;
                }
            }
            localOptions.inJustDecodeBounds = false;
            localOptions.inSampleSize = j;
            localObject2 = BitmapFactory.decodeFile((String)localObject2, localOptions);
            this.imgNew.setImageBitmap((Bitmap)localObject2);
            this.tvNew.setText(((Uri)localObject1).toString());
            localObject1 = this.mInflater.inflate(2130968618, this.ll_add_pic, false);
            this.fl_add_pic = ((FrameLayout)((View)localObject1).findViewById(2131361976));
            addPicImgClick((View)localObject1);
            this.ll_add_pic.addView((View)localObject1);
            if (this.all_pic_num == 4) {
                this.fl_add_pic.setVisibility(8);
            }
        }
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130968584);
        init();
        bt_add_audioClick();
        bt_frist_reply_new_audioClick();
        getWindowData();
        backButClick();
        getFristReplyInfo();
        et_replyClick();
        addPicButClick();
        submit();
        listviewItemClick();
        this.synShowAddPicViewHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                if (LeaveDetailActivity.this.rl_add_pic.getVisibility() == 0) {
                    LeaveDetailActivity.this.rl_add_pic.setVisibility(8);
                }
                for (;;)
                {
                    super.handleMessage(paramAnonymousMessage);
                    return;
                    LeaveDetailActivity.this.addPicViewShow();
                }
            }
        };
        this.submitStrResultHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                LeaveDetailActivity.this.file = new File(LeaveDetailActivity.this.getCacheDir(), "/audio.mp3");
                if (LeaveDetailActivity.this.file.exists()) {
                    LeaveDetailActivity.this.submitAudio();
                }
                for (;;)
                {
                    super.handleMessage(paramAnonymousMessage);
                    return;
                    if (LeaveDetailActivity.this.submitImgList.size() > 0)
                    {
                        LeaveDetailActivity.this.submitImg();
                    }
                    else
                    {
                        Intent localIntent;
                        Bundle localBundle;
                        if (paramAnonymousMessage.obj.equals("200"))
                        {
                            LeaveDetailActivity.this.waitingDialog.dismiss();
                            Toast.makeText(LeaveDetailActivity.this, "���������������", 1000).show();
                            localIntent = new Intent(LeaveDetailActivity.this, LeaveDetailActivity.class);
                            localBundle = new Bundle();
                            localBundle.putString("queId", LeaveDetailActivity.this.queId);
                            localBundle.putString("queUser", LeaveDetailActivity.this.queUser);
                            localBundle.putString("queTime", LeaveDetailActivity.this.queTime);
                            localBundle.putString("queTitle", LeaveDetailActivity.this.queTitle);
                            localBundle.putString("queContent", LeaveDetailActivity.this.queContent);
                            localBundle.putString("queAudioTime", LeaveDetailActivity.this.queAudioTime);
                            localBundle.putString("queAudioPath", LeaveDetailActivity.this.queAudioPath);
                            localIntent.putExtras(localBundle);
                            LeaveDetailActivity.this.startActivity(localIntent);
                            LeaveDetailActivity.this.finish();
                        }
                        else if (paramAnonymousMessage.obj.equals("-1"))
                        {
                            LeaveDetailActivity.this.waitingDialog.dismiss();
                            Toast.makeText(LeaveDetailActivity.this, "������������������������������", 1000).show();
                            localIntent = new Intent(LeaveDetailActivity.this, LeaveDetailActivity.class);
                            localBundle = new Bundle();
                            localBundle.putString("queId", LeaveDetailActivity.this.queId);
                            localBundle.putString("queUser", LeaveDetailActivity.this.queUser);
                            localBundle.putString("queTime", LeaveDetailActivity.this.queTime);
                            localBundle.putString("queTitle", LeaveDetailActivity.this.queTitle);
                            localBundle.putString("queContent", LeaveDetailActivity.this.queContent);
                            localBundle.putString("queAudioTime", LeaveDetailActivity.this.queAudioTime);
                            localBundle.putString("queAudioPath", LeaveDetailActivity.this.queAudioPath);
                            localIntent.putExtras(localBundle);
                            LeaveDetailActivity.this.startActivity(localIntent);
                            LeaveDetailActivity.this.finish();
                        }
                    }
                }
            }
        };
        this.submitImgResultHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                Object localObject = LeaveDetailActivity.this;
                ((LeaveDetailActivity)localObject).submitImgNum += 1;
                Bundle localBundle;
                if (LeaveDetailActivity.this.submitImgNum == LeaveDetailActivity.this.submitImgList.size())
                {
                    if (!paramAnonymousMessage.obj.equals("200")) {
                        break label214;
                    }
                    LeaveDetailActivity.this.waitingDialog.dismiss();
                    Toast.makeText(LeaveDetailActivity.this, "���������������", 1000).show();
                    localObject = new Intent(LeaveDetailActivity.this, LeaveDetailActivity.class);
                    localBundle = new Bundle();
                    localBundle.putString("queId", LeaveDetailActivity.this.queId);
                    localBundle.putString("queUser", LeaveDetailActivity.this.queUser);
                    localBundle.putString("queTime", LeaveDetailActivity.this.queTime);
                    localBundle.putString("queTitle", LeaveDetailActivity.this.queTitle);
                    localBundle.putString("queContent", LeaveDetailActivity.this.queContent);
                    localBundle.putString("queAudioTime", LeaveDetailActivity.this.queAudioTime);
                    localBundle.putString("queAudioPath", LeaveDetailActivity.this.queAudioPath);
                    ((Intent)localObject).putExtras(localBundle);
                    LeaveDetailActivity.this.startActivity((Intent)localObject);
                    LeaveDetailActivity.this.finish();
                }
                for (;;)
                {
                    super.handleMessage(paramAnonymousMessage);
                    return;
                    label214:
                    if (paramAnonymousMessage.obj.equals("-1"))
                    {
                        LeaveDetailActivity.this.waitingDialog.dismiss();
                        Toast.makeText(LeaveDetailActivity.this, "������������������������������", 1000).show();
                        localObject = new Intent(LeaveDetailActivity.this, LeaveDetailActivity.class);
                        localBundle = new Bundle();
                        localBundle.putString("queId", LeaveDetailActivity.this.queId);
                        localBundle.putString("queUser", LeaveDetailActivity.this.queUser);
                        localBundle.putString("queTime", LeaveDetailActivity.this.queTime);
                        localBundle.putString("queTitle", LeaveDetailActivity.this.queTitle);
                        localBundle.putString("queContent", LeaveDetailActivity.this.queContent);
                        localBundle.putString("queAudioTime", LeaveDetailActivity.this.queAudioTime);
                        localBundle.putString("queAudioPath", LeaveDetailActivity.this.queAudioPath);
                        ((Intent)localObject).putExtras(localBundle);
                        LeaveDetailActivity.this.startActivity((Intent)localObject);
                        LeaveDetailActivity.this.finish();
                    }
                }
            }
        };
        this.setFristReplyHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                Map localMap = (Map)paramAnonymousMessage.obj;
                try
                {
                    Thread.sleep(200L);
                    LeaveDetailActivity.this.rl_lunbo_progressbar.setVisibility(8);
                    LeaveDetailActivity.this.ll_container.setVisibility(0);
                    LeaveDetailActivity.this.adapter = new FristReplyAdapter(LeaveDetailActivity.this, (List)localMap.get("list"), LeaveDetailActivity.this.map, (List)localMap.get("fristList"));
                    LeaveDetailActivity.this.lv_ans_listview.setAdapter(LeaveDetailActivity.this.adapter);
                    LeaveDetailActivity.this.lv_ans_listview.setOnRefreshListener(LeaveDetailActivity.this);
                    super.handleMessage(paramAnonymousMessage);
                    return;
                }
                catch (InterruptedException localInterruptedException)
                {
                    for (;;)
                    {
                        localInterruptedException.printStackTrace();
                    }
                }
            }
        };
        this.submitAudioResultHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                if (LeaveDetailActivity.this.submitImgList.size() > 0) {
                    LeaveDetailActivity.this.submitImg();
                }
                for (;;)
                {
                    super.handleMessage(paramAnonymousMessage);
                    return;
                    Intent localIntent;
                    Bundle localBundle;
                    if (paramAnonymousMessage.obj.equals("200"))
                    {
                        new File(LeaveDetailActivity.this.getCacheDir(), "/audio.mp3").delete();
                        LeaveDetailActivity.this.waitingDialog.dismiss();
                        Toast.makeText(LeaveDetailActivity.this, "���������������", 1000).show();
                        localIntent = new Intent(LeaveDetailActivity.this, LeaveDetailActivity.class);
                        localBundle = new Bundle();
                        localBundle.putString("queId", LeaveDetailActivity.this.queId);
                        localBundle.putString("queUser", LeaveDetailActivity.this.queUser);
                        localBundle.putString("queTime", LeaveDetailActivity.this.queTime);
                        localBundle.putString("queTitle", LeaveDetailActivity.this.queTitle);
                        localBundle.putString("queContent", LeaveDetailActivity.this.queContent);
                        localBundle.putString("queAudioTime", LeaveDetailActivity.this.queAudioTime);
                        localBundle.putString("queAudioPath", LeaveDetailActivity.this.queAudioPath);
                        localIntent.putExtras(localBundle);
                        LeaveDetailActivity.this.startActivity(localIntent);
                        LeaveDetailActivity.this.finish();
                    }
                    else if (paramAnonymousMessage.obj.equals("-1"))
                    {
                        LeaveDetailActivity.this.waitingDialog.dismiss();
                        Toast.makeText(LeaveDetailActivity.this, "������������������������������", 1000).show();
                        localIntent = new Intent(LeaveDetailActivity.this, LeaveDetailActivity.class);
                        localBundle = new Bundle();
                        localBundle.putString("queId", LeaveDetailActivity.this.queId);
                        localBundle.putString("queUser", LeaveDetailActivity.this.queUser);
                        localBundle.putString("queTime", LeaveDetailActivity.this.queTime);
                        localBundle.putString("queTitle", LeaveDetailActivity.this.queTitle);
                        localBundle.putString("queContent", LeaveDetailActivity.this.queContent);
                        localBundle.putString("queAudioTime", LeaveDetailActivity.this.queAudioTime);
                        localBundle.putString("queAudioPath", LeaveDetailActivity.this.queAudioPath);
                        localIntent.putExtras(localBundle);
                        LeaveDetailActivity.this.startActivity(localIntent);
                        LeaveDetailActivity.this.finish();
                    }
                }
            }
        };
    }

    protected void onDestroy()
    {
        new File(getCacheDir(), "/audio.mp3").delete();
        super.onDestroy();
    }

    public void onDownPullRefresh()
    {
        new AsyncTask()
        {
            protected Void doInBackground(Void... paramAnonymousVarArgs)
            {
                LeaveDetailActivity.this.requestNum = 0;
                System.out.println("������");
                try
                {
                    paramAnonymousVarArgs = new HashMap();
                    Object localObject1 = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/MoreQueImgAjax", "queId=" + LeaveDetailActivity.this.queId, "GET", 0, null, null);
                    System.out.println("================1111111================");
                    localObject1 = (List)JsonUtil.getGson().fromJson((String)localObject1, new TypeToken() {}.getType());
                    ArrayList localArrayList = new ArrayList();
                    Object localObject2 = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/FristReplyInfoAjax", "queId=" + LeaveDetailActivity.this.queId + "&flag=vo&requestNum=" + LeaveDetailActivity.this.requestNum, "POST", 0, null, null);
                    System.out.println("================voResult1================");
                    localObject2 = (List)JsonUtil.getGson().fromJson((String)localObject2, new TypeToken() {}.getType());
                    int i = 0;
                    if (i >= ((List)localObject2).size())
                    {
                        LeaveDetailActivity.this.list.clear();
                        i = 0;
                    }
                    for (;;)
                    {
                        if (i >= localArrayList.size())
                        {
                            paramAnonymousVarArgs.put("fristList", localObject1);
                            paramAnonymousVarArgs.put("list", LeaveDetailActivity.this.list);
                            break label570;
                            HashMap localHashMap = new HashMap();
                            Object localObject3 = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/FristReplyInfoAjax", "fristAnsId=" + ((LeaveFristAnsVO)((List)localObject2).get(i)).getFristAnsId() + "&queId=" + LeaveDetailActivity.this.queId + "&flag=img&requestNum=" + LeaveDetailActivity.this.requestNum, "POST", 0, null, null);
                            System.out.println("================imgResult1================");
                            localObject3 = (List)JsonUtil.getGson().fromJson((String)localObject3, new TypeToken() {}.getType());
                            Object localObject4 = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/FristReplyInfoAjax", "fristAnsId=" + ((LeaveFristAnsVO)((List)localObject2).get(i)).getFristAnsId() + "&queId=" + LeaveDetailActivity.this.queId + "&flag=secReply&requestNum=" + LeaveDetailActivity.this.requestNum, "POST", 0, null, null);
                            System.out.println("================secReplyResult1================");
                            localObject4 = (List)JsonUtil.getGson().fromJson((String)localObject4, new TypeToken() {}.getType());
                            localHashMap.put("vo", ((List)localObject2).get(i));
                            localHashMap.put("imgList", localObject3);
                            localHashMap.put("secReplyList", localObject4);
                            localArrayList.add(localHashMap);
                            i += 1;
                            break;
                        }
                        LeaveDetailActivity.this.list.add((Map)localArrayList.get(i));
                        i += 1;
                    }
                    return null;
                }
                catch (Exception paramAnonymousVarArgs)
                {
                    paramAnonymousVarArgs.printStackTrace();
                }
            }

            protected void onPostExecute(Void paramAnonymousVoid)
            {
                LeaveDetailActivity.this.adapter.notifyDataSetChanged();
                LeaveDetailActivity.this.lv_ans_listview.hideHeaderView();
            }
        }.execute(new Void[0]);
    }

    public void onLoadingMore()
    {
        new AsyncTask()
        {
            protected Void doInBackground(Void... paramAnonymousVarArgs)
            {
                System.out.println("������������");
                try
                {
                    paramAnonymousVarArgs = LeaveDetailActivity.this;
                    paramAnonymousVarArgs.requestNum += 1;
                    paramAnonymousVarArgs = new HashMap();
                    Object localObject1 = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/MoreQueImgAjax", "queId=" + LeaveDetailActivity.this.queId, "GET", 0, null, null);
                    System.out.println("================eeeeeee================");
                    localObject1 = (List)JsonUtil.getGson().fromJson((String)localObject1, new TypeToken() {}.getType());
                    ArrayList localArrayList = new ArrayList();
                    Object localObject2 = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/FristReplyInfoAjax", "queId=" + LeaveDetailActivity.this.queId + "&flag=vo&requestNum=" + LeaveDetailActivity.this.requestNum, "POST", 0, null, null);
                    System.out.println("================voResult================");
                    localObject2 = (List)JsonUtil.getGson().fromJson((String)localObject2, new TypeToken() {}.getType());
                    int i = 0;
                    if (i >= ((List)localObject2).size()) {
                        i = 0;
                    }
                    for (;;)
                    {
                        if (i >= localArrayList.size())
                        {
                            paramAnonymousVarArgs.put("fristList", localObject1);
                            paramAnonymousVarArgs.put("list", LeaveDetailActivity.this.list);
                            break label565;
                            HashMap localHashMap = new HashMap();
                            Object localObject3 = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/FristReplyInfoAjax", "fristAnsId=" + ((LeaveFristAnsVO)((List)localObject2).get(i)).getFristAnsId() + "&queId=" + LeaveDetailActivity.this.queId + "&flag=img&requestNum=" + LeaveDetailActivity.this.requestNum, "POST", 0, null, null);
                            System.out.println("================imgResult================");
                            localObject3 = (List)JsonUtil.getGson().fromJson((String)localObject3, new TypeToken() {}.getType());
                            Object localObject4 = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/FristReplyInfoAjax", "fristAnsId=" + ((LeaveFristAnsVO)((List)localObject2).get(i)).getFristAnsId() + "&queId=" + LeaveDetailActivity.this.queId + "&flag=secReply&requestNum=" + LeaveDetailActivity.this.requestNum, "POST", 0, null, null);
                            System.out.println("================secReplyResult================");
                            localObject4 = (List)JsonUtil.getGson().fromJson((String)localObject4, new TypeToken() {}.getType());
                            localHashMap.put("vo", ((List)localObject2).get(i));
                            localHashMap.put("imgList", localObject3);
                            localHashMap.put("secReplyList", localObject4);
                            localArrayList.add(localHashMap);
                            i += 1;
                            break;
                        }
                        LeaveDetailActivity.this.list.add((Map)localArrayList.get(i));
                        i += 1;
                    }
                    return null;
                }
                catch (Exception paramAnonymousVarArgs)
                {
                    paramAnonymousVarArgs.printStackTrace();
                }
            }

            protected void onPostExecute(Void paramAnonymousVoid)
            {
                LeaveDetailActivity.this.adapter.notifyDataSetChanged();
                LeaveDetailActivity.this.lv_ans_listview.hideFooterView();
            }
        }.execute(new Void[0]);
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
            this.rl_add_pic.setVisibility(8);
            this.isKeyBoard = false;
            InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
            if ((getCurrentFocus() != null) && (getCurrentFocus().getWindowToken() != null)) {
                localInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            }
        }
        return super.onTouchEvent(paramMotionEvent);
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

    public void submit()
    {
        this.but_submit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (LeaveDetailActivity.this.bt_frist_reply_add_audio.getText().equals("������"))
                {
                    if (LeaveDetailActivity.this.isClickAddBut)
                    {
                        LeaveDetailActivity.this.submitImgList.clear();
                        LeaveDetailActivity.this.submitContentList.clear();
                        LeaveDetailActivity.this.traversalView(LeaveDetailActivity.this.ll_add_pic);
                        if ((LeaveDetailActivity.this.et_reply.getText().toString() == null) || (LeaveDetailActivity.this.et_reply.getText().toString().equals(""))) {
                            Toast.makeText(LeaveDetailActivity.this, "���������������������������������", 1000).show();
                        }
                    }
                    while (LeaveDetailActivity.this.isClickAddBut)
                    {
                        return;
                        LeaveDetailActivity.this.waitingDialog = new ProgressDialog(LeaveDetailActivity.this);
                        LeaveDetailActivity.this.waitingDialog.setTitle("������������������");
                        LeaveDetailActivity.this.waitingDialog.setMessage("���������...");
                        LeaveDetailActivity.this.waitingDialog.setIndeterminate(true);
                        LeaveDetailActivity.this.waitingDialog.setCancelable(false);
                        LeaveDetailActivity.this.waitingDialog.show();
                        LeaveDetailActivity.this.submitContentList.add(LeaveDetailActivity.this.username);
                        LeaveDetailActivity.this.submitContentList.add(LeaveDetailActivity.this.et_reply.getText().toString());
                        LeaveDetailActivity.this.submitContentList.add(LeaveDetailActivity.this.queId);
                        System.out.println("������1");
                        LeaveDetailActivity.this.submitReplyWeb();
                        return;
                    }
                    LeaveDetailActivity.this.submitImgList.clear();
                    LeaveDetailActivity.this.submitContentList.clear();
                    if ((LeaveDetailActivity.this.et_reply.getText().toString() == null) || (LeaveDetailActivity.this.et_reply.getText().toString().equals("")))
                    {
                        Toast.makeText(LeaveDetailActivity.this, "���������������������������������", 1000).show();
                        return;
                    }
                    LeaveDetailActivity.this.waitingDialog = new ProgressDialog(LeaveDetailActivity.this);
                    LeaveDetailActivity.this.waitingDialog.setTitle("������������������");
                    LeaveDetailActivity.this.waitingDialog.setMessage("���������...");
                    LeaveDetailActivity.this.waitingDialog.setIndeterminate(true);
                    LeaveDetailActivity.this.waitingDialog.setCancelable(false);
                    LeaveDetailActivity.this.waitingDialog.show();
                    LeaveDetailActivity.this.submitContentList.add(LeaveDetailActivity.this.username);
                    LeaveDetailActivity.this.submitContentList.add(LeaveDetailActivity.this.et_reply.getText().toString());
                    LeaveDetailActivity.this.submitContentList.add(LeaveDetailActivity.this.queId);
                    System.out.println("������2");
                    LeaveDetailActivity.this.submitReplyWeb();
                    return;
                }
                LeaveDetailActivity.this.submitImgList.clear();
                LeaveDetailActivity.this.submitContentList.clear();
                System.out.println("������");
                LeaveDetailActivity.this.traversalView(LeaveDetailActivity.this.ll_add_pic);
                LeaveDetailActivity.this.file = new File(LeaveDetailActivity.this.getCacheDir(), "/audio.mp3");
                if (!LeaveDetailActivity.this.file.exists())
                {
                    Toast.makeText(LeaveDetailActivity.this, "���������������������", 1000).show();
                    return;
                }
                LeaveDetailActivity.this.waitingDialog = new ProgressDialog(LeaveDetailActivity.this);
                LeaveDetailActivity.this.waitingDialog.setTitle("������������������");
                LeaveDetailActivity.this.waitingDialog.setMessage("���������...");
                LeaveDetailActivity.this.waitingDialog.setIndeterminate(true);
                LeaveDetailActivity.this.waitingDialog.setCancelable(false);
                LeaveDetailActivity.this.waitingDialog.show();
                LeaveDetailActivity.this.submitContentList.add(LeaveDetailActivity.this.username);
                LeaveDetailActivity.this.submitContentList.add("");
                LeaveDetailActivity.this.submitContentList.add(LeaveDetailActivity.this.queId);
                LeaveDetailActivity.this.submitReplyWeb();
            }
        });
    }

    public void submitAudio()
    {
        AsyncHttpClient localAsyncHttpClient = new AsyncHttpClient();
        RequestParams localRequestParams = new RequestParams();
        localRequestParams.put("Id", this.id);
        localRequestParams.put("flag", "audio");
        localRequestParams.put("audioTime", this.audioTime);
        try
        {
            localRequestParams.put("file", this.file);
            localAsyncHttpClient.post("http://118.190.155.221:8098/StrawAdminWeb/leave/SubmitReplyAjax", localRequestParams, new AsyncHttpResponseHandler()
            {
                public void onFailure(int paramAnonymousInt, Header[] paramAnonymousArrayOfHeader, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable) {}

                public void onSuccess(int paramAnonymousInt, Header[] paramAnonymousArrayOfHeader, byte[] paramAnonymousArrayOfByte)
                {
                    paramAnonymousArrayOfHeader = new Message();
                    paramAnonymousArrayOfHeader.obj = LeaveDetailActivity.this.result;
                    LeaveDetailActivity.this.submitAudioResultHandler.sendMessage(paramAnonymousArrayOfHeader);
                }
            });
            return;
        }
        catch (Exception localException)
        {
            for (;;)
            {
                localException.printStackTrace();
            }
        }
    }

    public void submitImg()
    {
        int i = 0;
        for (;;)
        {
            if (i >= this.submitImgList.size()) {
                return;
            }
            new Thread()
            {
                public void run()
                {
                    try
                    {
                        LeaveDetailActivity.this.result = ConnWeb.upLoadImg("http://118.190.155.221:8098/StrawAdminWeb/leave/SubmitReplyAjax", "Id=" + LeaveDetailActivity.this.id + "&flag=img" + "&img=" + this.val$img, "POST", 0, null, null);
                        System.out.println("================img================");
                        Message localMessage = new Message();
                        localMessage.obj = LeaveDetailActivity.this.result;
                        LeaveDetailActivity.this.submitImgResultHandler.sendMessage(localMessage);
                        return;
                    }
                    catch (Exception localException)
                    {
                        localException.printStackTrace();
                    }
                }
            }.start();
            i += 1;
        }
    }

    public void submitReplyWeb()
    {
        this.id = IDUtil.getID();
        new Thread()
        {
            public void run()
            {
                try
                {
                    LeaveDetailActivity.this.result = ConnWeb.ConnForJson(LeaveDetailActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/SubmitReplyAjax", "Id=" + LeaveDetailActivity.this.id + "&str=" + this.val$str, "POST", 0, null, null);
                    System.out.println("================xxxxxxxx================");
                    Message localMessage = new Message();
                    localMessage.obj = LeaveDetailActivity.this.result;
                    LeaveDetailActivity.this.submitStrResultHandler.sendMessage(localMessage);
                    return;
                }
                catch (Exception localException)
                {
                    localException.printStackTrace();
                }
            }
        }.start();
    }

    public void traversalView(ViewGroup paramViewGroup)
    {
        int j = paramViewGroup.getChildCount();
        int i = 0;
        if (i >= j) {
            return;
        }
        View localView = paramViewGroup.getChildAt(i);
        if ((localView instanceof ViewGroup)) {
            traversalView((ViewGroup)localView);
        }
        for (;;)
        {
            i += 1;
            break;
            if ((localView instanceof TextView)) {
                doView(localView);
            }
        }
    }

    public void traversalView1(ViewGroup paramViewGroup)
    {
        int j = paramViewGroup.getChildCount();
        int i = 0;
        if (i >= j) {
            return;
        }
        View localView = paramViewGroup.getChildAt(i);
        if ((localView instanceof ViewGroup)) {
            traversalView1((ViewGroup)localView);
        }
        for (;;)
        {
            i += 1;
            break;
            if ((localView instanceof ImageView)) {
                doView1(localView);
            }
        }
    }
}
