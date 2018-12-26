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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cai.strawberryapp.BaseClass.ConnWeb;
import com.cai.strawberryapp.BaseClass.HeaderMenu;
import com.cai.strawberryapp.BaseClass.IDUtil;
import com.cai.strawberryapp.BaseClass.JsonUtil;
import com.cai.strawberryapp.BaseClass.TimeFormat;
import com.cai.strawberryapp.BaseClass.UriToPath;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.http.Header;

public class NewQueActivity
        extends Activity
{
    private int all_pic_num = 0;
    private String audioPath;
    private String audioTime;
    private Button bt_add_audio;
    private Button bt_new_que_audio;
    private Button but_new_que_submit;
    private CountDownTimer cdt1 = null;
    private EditText et_new_que_content;
    private EditText et_new_que_title;
    private File file;
    private FrameLayout fl_add_pic;
    private boolean flag = false;
    private HorizontalScrollView hs_add_new_que_img;
    private String id = "";
    private ImageView imgNew;
    private boolean isClickAddBut = false;
    private LinearLayout ll_add_que_pic;
    private LinearLayout ll_new_que_content;
    private LayoutInflater mInflater;
    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;
    private PopupWindow popupwindow;
    private long remainAudioTime = 0L;
    private String result = "";
    private Handler submitAudioResultHandler;
    private List<byte[]> submitImgList;
    private int submitImgNum = 0;
    private Handler submitImgResultHandler;
    private List<String> submitStrList;
    private Handler submitStrResultHandler;
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
                paramView.compress(Bitmap.CompressFormat.JPEG, 20, (OutputStream)localObject);
            }
        }
    }

    public void RLClick(View paramView)
    {
        final TextView localTextView = (TextView)paramView.findViewById(2131361978);
        ((RelativeLayout)paramView.findViewById(2131361831)).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                paramAnonymousView = (String)localTextView.getText();
                Intent localIntent = new Intent(NewQueActivity.this, AllScreamShowActivity.class);
                Bundle localBundle = new Bundle();
                localBundle.putString("uri", paramAnonymousView);
                localIntent.putExtras(localBundle);
                NewQueActivity.this.startActivity(localIntent);
            }
        });
    }

    public void RLLongClick(final View paramView)
    {
        ((RelativeLayout)paramView.findViewById(2131361831)).setOnLongClickListener(new View.OnLongClickListener()
        {
            public boolean onLongClick(View paramAnonymousView)
            {
                paramAnonymousView = NewQueActivity.this;
                paramAnonymousView.all_pic_num -= 1;
                NewQueActivity.this.ll_add_que_pic.removeView(paramView);
                NewQueActivity.this.fl_add_pic.setVisibility(0);
                return false;
            }
        });
    }

    public void addPic()
    {
        View localView = this.mInflater.inflate(2130968618, this.ll_add_que_pic, false);
        addPicImgClick(localView);
        this.ll_add_que_pic.addView(localView);
    }

    public void addPicImgClick(final View paramView)
    {
        ImageView localImageView = (ImageView)paramView.findViewById(2131361977);
        TextView localTextView = (TextView)paramView.findViewById(2131361978);
        this.imgNew = localImageView;
        this.tvNew = localTextView;
        localImageView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                NewQueActivity.this.RLClick(paramView);
                NewQueActivity.this.RLLongClick(paramView);
                paramAnonymousView = new Intent();
                paramAnonymousView.setAction("android.intent.action.PICK");
                paramAnonymousView.addCategory("android.intent.category.DEFAULT");
                paramAnonymousView.setType("image/*");
                NewQueActivity.this.startActivityForResult(paramAnonymousView, 0);
                System.out.println("sadadasdadasdadas");
            }
        });
    }

    public void bt_add_audioClick()
    {
        this.bt_add_audio.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                System.out.println("111sss111");
                if (NewQueActivity.this.bt_add_audio.getText().equals("������"))
                {
                    paramAnonymousView = (InputMethodManager)NewQueActivity.this.getSystemService("input_method");
                    if ((NewQueActivity.this.getCurrentFocus() != null) && (NewQueActivity.this.getCurrentFocus().getWindowToken() != null)) {
                        paramAnonymousView.hideSoftInputFromWindow(NewQueActivity.this.getCurrentFocus().getWindowToken(), 2);
                    }
                    NewQueActivity.this.bt_new_que_audio.setVisibility(0);
                    NewQueActivity.this.et_new_que_content.setVisibility(8);
                    NewQueActivity.this.bt_add_audio.setBackgroundResource(2130903047);
                    NewQueActivity.this.bt_add_audio.setText("������");
                    return;
                }
                NewQueActivity.this.bt_new_que_audio.setVisibility(8);
                NewQueActivity.this.et_new_que_content.setVisibility(0);
                NewQueActivity.this.bt_new_que_audio.setText("������������������");
                new File(NewQueActivity.this.getCacheDir(), "/audio.mp3").delete();
                NewQueActivity.this.bt_add_audio.setBackgroundResource(2130903041);
                NewQueActivity.this.bt_add_audio.setText("������");
            }
        });
    }

    public void bt_new_que_audioClick()
    {
        this.bt_new_que_audio.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
            {
                switch (paramAnonymousMotionEvent.getAction())
                {
                }
                label64:
                label556:
                label857:
                for (;;)
                {
                    return false;
                    NewQueActivity.this.ll_add_que_pic.setClickable(false);
                    int i;
                    if (NewQueActivity.this.getPackageManager().checkPermission("android.permission.RECORD_AUDIO", "com.cai.strawberryapp") == 0)
                    {
                        i = 1;
                        if (i == 0) {
                            break label556;
                        }
                        NewQueActivity.this.flag = true;
                        paramAnonymousView = new File(NewQueActivity.this.getCacheDir(), "/audio.mp3");
                        if ((paramAnonymousView != null) && (paramAnonymousView.exists())) {}
                    }
                    else
                    {
                        try
                        {
                            NewQueActivity.this.popupwindow = new PopupWindow();
                            paramAnonymousView = NewQueActivity.this.getLayoutInflater().inflate(2130968607, null);
                            System.out.println("������");
                            NewQueActivity.this.popupwindow = new PopupWindow(paramAnonymousView, 500, 100, true);
                            NewQueActivity.this.popupwindow.setTouchable(true);
                            NewQueActivity.this.popupwindow.setOutsideTouchable(true);
                            NewQueActivity.this.popupwindow.showAsDropDown(NewQueActivity.this.ll_new_que_content, NewQueActivity.this.ll_new_que_content.getWidth() / 2 - 250, -NewQueActivity.this.ll_new_que_content.getHeight() - 20);
                            NewQueActivity.this.mediaRecorder = new MediaRecorder();
                            NewQueActivity.this.mediaRecorder.setAudioSource(1);
                            NewQueActivity.this.mediaRecorder.setOutputFormat(0);
                            NewQueActivity.this.mediaRecorder.setOutputFile(NewQueActivity.this.audioPath);
                            NewQueActivity.this.mediaRecorder.setAudioEncoder(0);
                            NewQueActivity.this.mediaRecorder.prepare();
                            NewQueActivity.this.mediaRecorder.start();
                            System.out.println("������");
                            NewQueActivity.this.bt_new_que_audio.setText("������������...");
                            NewQueActivity.this.cdt1 = new CountDownTimer(60000L, 1000L)
                            {
                                public void onFinish()
                                {
                                    System.out.println("������������");
                                    NewQueActivity.this.popupwindow.dismiss();
                                    NewQueActivity.this.remainAudioTime = 0L;
                                    Toast.makeText(NewQueActivity.this, "���������������������1.0������", 1000).show();
                                    NewQueActivity.this.mediaRecorder.stop();
                                    NewQueActivity.this.mediaRecorder.release();
                                    NewQueActivity.this.mediaRecorder = null;
                                }

                                public void onTick(long paramAnonymous2Long)
                                {
                                    NewQueActivity.this.remainAudioTime = paramAnonymous2Long;
                                }
                            };
                            NewQueActivity.this.cdt1.start();
                        }
                        catch (IllegalStateException paramAnonymousView)
                        {
                            paramAnonymousView.printStackTrace();
                            continue;
                            i = 0;
                            break label64;
                        }
                        catch (IOException paramAnonymousView)
                        {
                            paramAnonymousView.printStackTrace();
                        }
                        continue;
                    }
                    System.out.println("������");
                    try
                    {
                        if ((NewQueActivity.this.mediaPlayer != null) && (NewQueActivity.this.mediaPlayer.isPlaying()))
                        {
                            NewQueActivity.this.mediaPlayer.stop();
                            NewQueActivity.this.mediaPlayer.release();
                            NewQueActivity.this.mediaPlayer = null;
                        }
                        NewQueActivity.this.mediaPlayer = new MediaPlayer();
                        NewQueActivity.this.mediaPlayer.setAudioStreamType(3);
                        NewQueActivity.this.mediaPlayer.setDataSource(NewQueActivity.this.getCacheDir() + "/audio.mp3");
                        NewQueActivity.this.mediaPlayer.prepare();
                        NewQueActivity.this.mediaPlayer.start();
                    }
                    catch (Exception paramAnonymousView)
                    {
                        Toast.makeText(NewQueActivity.this, "������������������", 1000).show();
                        paramAnonymousView.printStackTrace();
                    }
                    continue;
                    NewQueActivity.this.flag = false;
                    Toast.makeText(NewQueActivity.this, "���������������������������", 1000).show();
                    continue;
                    if (NewQueActivity.this.flag)
                    {
                        NewQueActivity.this.ll_add_que_pic.setClickable(true);
                        System.out.println("������");
                        if (NewQueActivity.this.remainAudioTime >= 59000.0D)
                        {
                            NewQueActivity.this.bt_new_que_audio.setText("������������������");
                            NewQueActivity.this.mediaRecorder.stop();
                            NewQueActivity.this.mediaRecorder.release();
                            NewQueActivity.this.mediaRecorder = null;
                            NewQueActivity.this.popupwindow.dismiss();
                            new File(NewQueActivity.this.getCacheDir(), "/audio.mp3").delete();
                            Toast.makeText(NewQueActivity.this, "���������������������", 1000).show();
                        }
                        for (;;)
                        {
                            if (NewQueActivity.this.cdt1 == null) {
                                break label857;
                            }
                            NewQueActivity.this.cdt1.cancel();
                            break;
                            NewQueActivity.this.popupwindow.dismiss();
                            System.out.println("������");
                            paramAnonymousView = TimeFormat.timeFormat(60000.0D - NewQueActivity.this.remainAudioTime);
                            paramAnonymousView = paramAnonymousView.substring(3, paramAnonymousView.length());
                            NewQueActivity.this.audioTime = paramAnonymousView;
                            NewQueActivity.this.bt_new_que_audio.setText("������������������:" + paramAnonymousView);
                            if (NewQueActivity.this.mediaRecorder != null)
                            {
                                NewQueActivity.this.mediaRecorder.stop();
                                NewQueActivity.this.mediaRecorder.release();
                                NewQueActivity.this.mediaRecorder = null;
                            }
                        }
                    }
                }
            }
        });
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
            FileInputStream localFileInputStream = new FileInputStream(new File(getCacheDir(), "userInfo.properties"));
            Properties localProperties = new Properties();
            localProperties.load(localFileInputStream);
            localFileInputStream.close();
            this.username = localProperties.getProperty("username");
            this.audioPath = (getCacheDir() + "/audio.mp3");
            this.ll_new_que_content = ((LinearLayout)findViewById(2131361845));
            this.et_new_que_title = ((EditText)findViewById(2131361844));
            this.et_new_que_content = ((EditText)findViewById(2131361846));
            this.hs_add_new_que_img = ((HorizontalScrollView)findViewById(2131361849));
            this.ll_add_que_pic = ((LinearLayout)findViewById(2131361850));
            this.but_new_que_submit = ((Button)findViewById(2131361851));
            this.bt_add_audio = ((Button)findViewById(2131361848));
            this.bt_new_que_audio = ((Button)findViewById(2131361847));
            this.submitImgList = new ArrayList();
            this.submitStrList = new ArrayList();
            this.mInflater = LayoutInflater.from(this);
            new HeaderMenu(this, true, "���������", null).initHeaderBut();
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
            localObject1 = this.mInflater.inflate(2130968618, this.ll_add_que_pic, false);
            this.fl_add_pic = ((FrameLayout)((View)localObject1).findViewById(2131361976));
            addPicImgClick((View)localObject1);
            this.ll_add_que_pic.addView((View)localObject1);
            if (this.all_pic_num == 4) {
                this.fl_add_pic.setVisibility(8);
            }
        }
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130968588);
        init();
        bt_add_audioClick();
        bt_new_que_audioClick();
        addPic();
        submit();
        getWindowData();
        this.submitStrResultHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                NewQueActivity.this.file = new File(NewQueActivity.this.getCacheDir(), "/audio.mp3");
                if (NewQueActivity.this.file.exists())
                {
                    NewQueActivity.this.submitAudio();
                    return;
                }
                if (NewQueActivity.this.submitImgList.size() > 0) {
                    NewQueActivity.this.submitImg();
                }
                for (;;)
                {
                    super.handleMessage(paramAnonymousMessage);
                    return;
                    Intent localIntent;
                    if (paramAnonymousMessage.obj.equals("200"))
                    {
                        NewQueActivity.this.waitingDialog.dismiss();
                        Toast.makeText(NewQueActivity.this, "���������������", 1000).show();
                        localIntent = new Intent(NewQueActivity.this, LeaveActivity.class);
                        NewQueActivity.this.startActivity(localIntent);
                        NewQueActivity.this.finish();
                    }
                    else if (paramAnonymousMessage.obj.equals("-1"))
                    {
                        NewQueActivity.this.waitingDialog.dismiss();
                        Toast.makeText(NewQueActivity.this, "������������������������������", 1000).show();
                        localIntent = new Intent(NewQueActivity.this, LeaveActivity.class);
                        NewQueActivity.this.startActivity(localIntent);
                        NewQueActivity.this.finish();
                    }
                }
            }
        };
        this.submitImgResultHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                Object localObject = NewQueActivity.this;
                ((NewQueActivity)localObject).submitImgNum += 1;
                if (NewQueActivity.this.submitImgNum == NewQueActivity.this.submitImgList.size())
                {
                    if (!paramAnonymousMessage.obj.equals("200")) {
                        break label109;
                    }
                    NewQueActivity.this.waitingDialog.dismiss();
                    Toast.makeText(NewQueActivity.this, "���������������", 1000).show();
                    localObject = new Intent(NewQueActivity.this, LeaveActivity.class);
                    NewQueActivity.this.startActivity((Intent)localObject);
                    NewQueActivity.this.finish();
                }
                for (;;)
                {
                    super.handleMessage(paramAnonymousMessage);
                    return;
                    label109:
                    if (paramAnonymousMessage.obj.equals("-1"))
                    {
                        NewQueActivity.this.waitingDialog.dismiss();
                        Toast.makeText(NewQueActivity.this, "������������������������������", 1000).show();
                        localObject = new Intent(NewQueActivity.this, LeaveActivity.class);
                        NewQueActivity.this.startActivity((Intent)localObject);
                        NewQueActivity.this.finish();
                    }
                }
            }
        };
        this.submitAudioResultHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                if (NewQueActivity.this.submitImgList.size() > 0) {
                    NewQueActivity.this.submitImg();
                }
                for (;;)
                {
                    super.handleMessage(paramAnonymousMessage);
                    return;
                    Intent localIntent;
                    if (paramAnonymousMessage.obj.equals("200"))
                    {
                        new File(NewQueActivity.this.getCacheDir(), "/audio.mp3").delete();
                        NewQueActivity.this.waitingDialog.dismiss();
                        Toast.makeText(NewQueActivity.this, "���������������", 1000).show();
                        localIntent = new Intent(NewQueActivity.this, LeaveActivity.class);
                        NewQueActivity.this.startActivity(localIntent);
                        NewQueActivity.this.finish();
                    }
                    else if (paramAnonymousMessage.obj.equals("-1"))
                    {
                        NewQueActivity.this.waitingDialog.dismiss();
                        Toast.makeText(NewQueActivity.this, "������������������������������", 1000).show();
                        localIntent = new Intent(NewQueActivity.this, LeaveActivity.class);
                        NewQueActivity.this.startActivity(localIntent);
                        NewQueActivity.this.finish();
                    }
                }
            }
        };
    }

    protected void onDestroy()
    {
        new File(getCacheDir(), "audio.mp3").delete();
        super.onDestroy();
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
        InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
        if ((getCurrentFocus() != null) && (getCurrentFocus().getWindowToken() != null)) {
            localInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
        return super.onTouchEvent(paramMotionEvent);
    }

    public void submit()
    {
        this.but_new_que_submit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (NewQueActivity.this.bt_add_audio.getText().equals("������"))
                {
                    NewQueActivity.this.submitImgList.clear();
                    NewQueActivity.this.submitStrList.clear();
                    NewQueActivity.this.traversalView(NewQueActivity.this.ll_add_que_pic);
                    if ((NewQueActivity.this.et_new_que_title.getText().toString() == null) || ((NewQueActivity.this.et_new_que_title.getText().toString().equals("")) && (NewQueActivity.this.et_new_que_content.getText().toString() == null)) || (NewQueActivity.this.et_new_que_content.getText().toString().equals("")))
                    {
                        Toast.makeText(NewQueActivity.this, "���������������������������������", 1000).show();
                        return;
                    }
                    NewQueActivity.this.waitingDialog = new ProgressDialog(NewQueActivity.this);
                    NewQueActivity.this.waitingDialog.setTitle("������������������");
                    NewQueActivity.this.waitingDialog.setMessage("���������...");
                    NewQueActivity.this.waitingDialog.setIndeterminate(true);
                    NewQueActivity.this.waitingDialog.setCancelable(false);
                    NewQueActivity.this.waitingDialog.show();
                    NewQueActivity.this.submitStrList.add(NewQueActivity.this.username);
                    NewQueActivity.this.submitStrList.add(NewQueActivity.this.et_new_que_title.getText().toString());
                    NewQueActivity.this.submitStrList.add(NewQueActivity.this.et_new_que_content.getText().toString());
                    NewQueActivity.this.submitReplyWeb();
                    return;
                }
                NewQueActivity.this.submitImgList.clear();
                NewQueActivity.this.submitStrList.clear();
                System.out.println("������");
                NewQueActivity.this.traversalView(NewQueActivity.this.ll_add_que_pic);
                if ((NewQueActivity.this.et_new_que_title.getText().toString() == null) || (NewQueActivity.this.et_new_que_title.getText().toString().equals("")))
                {
                    Toast.makeText(NewQueActivity.this, "���������������������������������", 1000).show();
                    return;
                }
                NewQueActivity.this.file = new File(NewQueActivity.this.getCacheDir(), "/audio.mp3");
                if (!NewQueActivity.this.file.exists())
                {
                    Toast.makeText(NewQueActivity.this, "���������������������", 1000).show();
                    return;
                }
                NewQueActivity.this.waitingDialog = new ProgressDialog(NewQueActivity.this);
                NewQueActivity.this.waitingDialog.setTitle("������������������");
                NewQueActivity.this.waitingDialog.setMessage("���������...");
                NewQueActivity.this.waitingDialog.setIndeterminate(true);
                NewQueActivity.this.waitingDialog.setCancelable(false);
                NewQueActivity.this.waitingDialog.show();
                NewQueActivity.this.submitStrList.add(NewQueActivity.this.username);
                NewQueActivity.this.submitStrList.add(NewQueActivity.this.et_new_que_title.getText().toString());
                NewQueActivity.this.submitStrList.add("");
                NewQueActivity.this.submitReplyWeb();
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
            localAsyncHttpClient.post("http://118.190.155.221:8098/StrawAdminWeb/leave/SubmitNewQueAjax", localRequestParams, new AsyncHttpResponseHandler()
            {
                public void onFailure(int paramAnonymousInt, Header[] paramAnonymousArrayOfHeader, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable) {}

                public void onSuccess(int paramAnonymousInt, Header[] paramAnonymousArrayOfHeader, byte[] paramAnonymousArrayOfByte)
                {
                    paramAnonymousArrayOfHeader = new Message();
                    paramAnonymousArrayOfHeader.obj = NewQueActivity.this.result;
                    NewQueActivity.this.submitAudioResultHandler.sendMessage(paramAnonymousArrayOfHeader);
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
                        NewQueActivity.this.result = ConnWeb.upLoadImg("http://118.190.155.221:8098/StrawAdminWeb/leave/SubmitNewQueAjax", "Id=" + NewQueActivity.this.id + "&flag=img" + "&img=" + this.val$img, "POST", 0, null, null);
                        System.out.println("================img================");
                        Message localMessage = new Message();
                        localMessage.obj = NewQueActivity.this.result;
                        NewQueActivity.this.submitImgResultHandler.sendMessage(localMessage);
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
                    NewQueActivity.this.result = ConnWeb.ConnForJson(NewQueActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/SubmitNewQueAjax", "Id=" + NewQueActivity.this.id + "&str=" + this.val$str, "POST", 0, null, null);
                    System.out.println("================xxxxxxxx================");
                    Message localMessage = new Message();
                    localMessage.obj = NewQueActivity.this.result;
                    NewQueActivity.this.submitStrResultHandler.sendMessage(localMessage);
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
}
