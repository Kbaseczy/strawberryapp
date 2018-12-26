package com.cai.strawberryapp.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cai.strawberryapp.Adapter.KnowAdapter;
import com.cai.strawberryapp.Adapter.LunBoAdapter;
import com.cai.strawberryapp.BaseClass.ConnWeb;
import com.cai.strawberryapp.BaseClass.FooterMenu;
import com.cai.strawberryapp.BaseClass.HeaderMenu;
import com.cai.strawberryapp.BaseClass.JsonUtil;
import com.cai.strawberryapp.VO.KnowVO;
import com.cai.strawberryapp.VO.MessageVO;
import com.cai.strawberryapp.View.OnRefreshListener;
import com.cai.strawberryapp.View.RefreshListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class IndexActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, OnRefreshListener
{
    private Handler autoLunBoHandler;
    private int c = 0;
    private CountDownLatch countDownLatch;
    private Button home_but;
    private ImageView image0;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5 = null;
    private boolean isDestroy = false;
    private KnowAdapter knowAdapter;
    private List<KnowVO> list;
    private List<String> listId = new ArrayList();
    private List<String> listTitle = new ArrayList();
    private RefreshListView listView;
    private int mLastY = 0;
    private int mYDown = 0;
    private int msgWhat = 0;
    private LunBoAdapter pagerAdapter = null;
    private int requestNum = 0;
    private RelativeLayout rl_lunbo_progressbar;
    private Handler setKnowHandler;
    private Handler setKnowImgHandler;
    private Handler setLunBoInfoHandler;
    private Handler setMoreKnowImgHandler;
    private Handler setReflushKnowImgHandler;
    private boolean startAutoLunbo = false;
    private AsyncTask task;
    private AsyncTask task1;
    private AsyncTask task2;
    private AsyncTask task3;
    private TextView textView0;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5 = null;
    private int threadNumber = 2;
    private TextView tv_id0;
    private TextView tv_id1;
    private TextView tv_id2;
    private TextView tv_id3;
    private TextView tv_id4;
    private TextView tv_id5 = null;
    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private View view5;
    private View view6;
    private List<View> viewList;
    private ViewPager viewPager;

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

    public void LunBoTouch()
    {
        this.viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
            {
                switch (paramAnonymousMotionEvent.getAction())
                {
                }
                for (;;)
                {
                    return false;
                    IndexActivity.this.autoLunBoHandler.removeMessages(IndexActivity.this.msgWhat);
                    continue;
                    IndexActivity.this.autoLunBoHandler.removeMessages(IndexActivity.this.msgWhat);
                    continue;
                    IndexActivity.this.autoLunBoHandler.sendEmptyMessageDelayed(IndexActivity.this.msgWhat, 2000L);
                }
            }
        });
    }

    public void getKnowInfo()
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Object localObject = ConnWeb.ConnForJson(IndexActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/home/KnowInfoAjax", "requestNum=" + IndexActivity.this.requestNum, "GET", 0, null, null);
                    System.out.println("================getKnowInfo()================");
                    IndexActivity.this.list = ((List)JsonUtil.getGson().fromJson((String)localObject, new TypeToken() {}.getType()));
                    localObject = new Message();
                    ((Message)localObject).obj = IndexActivity.this.list;
                    IndexActivity.this.setKnowHandler.sendMessage((Message)localObject);
                    return;
                }
                catch (Exception localException)
                {
                    localException.printStackTrace();
                }
            }
        }.start();
    }

    public void getLunboImage()
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    String str = ConnWeb.ConnForJson(IndexActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/home/LunboImgAjax", null, "GET", 0, null, null);
                    System.out.println("================getLunboImage()================");
                    List localList = (List)JsonUtil.getGson().fromJson(str, new TypeToken() {}.getType());
                    FileOutputStream localFileOutputStream = new FileOutputStream(new File(IndexActivity.this.getCacheDir(), "LunBoInfo.txt"));
                    str = "";
                    int i = 0;
                    for (;;)
                    {
                        if (i >= localList.size())
                        {
                            localFileOutputStream.write(str.getBytes());
                            localFileOutputStream.close();
                            return;
                        }
                        Object localObject = new FileOutputStream(new File(IndexActivity.this.getCacheDir(), "LunBoImage" + i + ".jpg"));
                        new MessageVO();
                        MessageVO localMessageVO = (MessageVO)localList.get(i);
                        ((FileOutputStream)localObject).write(localMessageVO.getMesImage());
                        ((FileOutputStream)localObject).close();
                        str = str + "##CAI##" + localMessageVO.getMesId() + "##TITLE##" + localMessageVO.getMesTitle();
                        localObject = new Message();
                        ((Message)localObject).what = i;
                        ((Message)localObject).obj = localMessageVO;
                        IndexActivity.this.countDownLatch.countDown();
                        IndexActivity.this.setLunBoInfoHandler.sendMessage((Message)localObject);
                        if (!IndexActivity.this.startAutoLunbo)
                        {
                            IndexActivity.this.startAutoLunbo = true;
                            IndexActivity.this.autoLunBoHandler.sendEmptyMessageDelayed(IndexActivity.this.msgWhat, 2000L);
                        }
                        i += 1;
                    }
                    return;
                }
                catch (IOException localIOException)
                {
                    localIOException.printStackTrace();
                }
            }
        }.start();
    }

    public void init()
    {
        this.countDownLatch = new CountDownLatch(this.threadNumber);
        this.home_but = ((Button)findViewById(2131361799));
        this.home_but.setBackgroundResource(2130903065);
        this.rl_lunbo_progressbar = ((RelativeLayout)findViewById(2131361817));
        this.viewPager = ((ViewPager)findViewById(2131361816));
        this.listView = ((RefreshListView)findViewById(2131361819));
        new HeaderMenu(this, false, "������", null).initHeaderBut();
        new FooterMenu(this).initFooterBut();
    }

    public void initView()
    {
        LayoutInflater localLayoutInflater = getLayoutInflater();
        this.view1 = localLayoutInflater.inflate(2130968606, null);
        this.view2 = localLayoutInflater.inflate(2130968603, null);
        this.view3 = localLayoutInflater.inflate(2130968604, null);
        this.view4 = localLayoutInflater.inflate(2130968605, null);
        this.view5 = localLayoutInflater.inflate(2130968606, null);
        this.view6 = localLayoutInflater.inflate(2130968603, null);
        this.image0 = ((ImageView)this.view1.findViewById(2131361921));
        this.image1 = ((ImageView)this.view2.findViewById(2131361912));
        this.image2 = ((ImageView)this.view3.findViewById(2131361915));
        this.image3 = ((ImageView)this.view4.findViewById(2131361918));
        this.image4 = ((ImageView)this.view5.findViewById(2131361921));
        this.image5 = ((ImageView)this.view6.findViewById(2131361912));
        this.textView0 = ((TextView)this.view1.findViewById(2131361922));
        this.textView1 = ((TextView)this.view2.findViewById(2131361913));
        this.textView2 = ((TextView)this.view3.findViewById(2131361916));
        this.textView3 = ((TextView)this.view4.findViewById(2131361919));
        this.textView4 = ((TextView)this.view5.findViewById(2131361922));
        this.textView5 = ((TextView)this.view6.findViewById(2131361913));
        this.tv_id0 = ((TextView)this.view1.findViewById(2131361923));
        this.tv_id1 = ((TextView)this.view2.findViewById(2131361914));
        this.tv_id2 = ((TextView)this.view3.findViewById(2131361917));
        this.tv_id3 = ((TextView)this.view4.findViewById(2131361920));
        this.tv_id4 = ((TextView)this.view5.findViewById(2131361923));
        this.tv_id5 = ((TextView)this.view6.findViewById(2131361914));
    }

    public void knowListClick()
    {
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                int i;
                if ((IndexActivity.this.getCacheDir() != null) && (IndexActivity.this.getCacheDir().exists()) && (IndexActivity.this.getCacheDir().isDirectory()))
                {
                    paramAnonymousAdapterView = IndexActivity.this.getCacheDir().listFiles();
                    i = paramAnonymousAdapterView.length;
                    paramAnonymousInt = 0;
                }
                for (;;)
                {
                    if (paramAnonymousInt >= i)
                    {
                        paramAnonymousAdapterView = (TextView)paramAnonymousView.findViewById(R.id.knowId);
                        paramAnonymousView = new Intent(IndexActivity.this, NewShowActivity.class);
                        localBundle = new Bundle();
                        localBundle.putString("mesId", paramAnonymousAdapterView.getText().toString());
                        localBundle.putString("flag", "know");
                        paramAnonymousView.putExtras(localBundle);
                        IndexActivity.this.startActivity(paramAnonymousView);
                        return;
                    }
                    Bundle localBundle = paramAnonymousAdapterView[paramAnonymousInt];
                    if (!localBundle.getName().equals("userInfo.properties")) {
                        localBundle.delete();
                    }
                    paramAnonymousInt += 1;
                }
            }
        });
    }

    public void lunBoClick()
    {
        this.image1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                int j;
                int i;
                if ((IndexActivity.this.getCacheDir() != null) && (IndexActivity.this.getCacheDir().exists()) && (IndexActivity.this.getCacheDir().isDirectory()))
                {
                    paramAnonymousView = IndexActivity.this.getCacheDir().listFiles();
                    j = paramAnonymousView.length;
                    i = 0;
                }
                for (;;)
                {
                    if (i >= j)
                    {
                        paramAnonymousView = new Intent(IndexActivity.this, NewShowActivity.class);
                        localBundle = new Bundle();
                        localBundle.putString("mesId", IndexActivity.this.tv_id1.getText().toString());
                        localBundle.putString("flag", "mes");
                        paramAnonymousView.putExtras(localBundle);
                        IndexActivity.this.startActivity(paramAnonymousView);
                        return;
                    }
                    Bundle localBundle = paramAnonymousView[i];
                    if (!localBundle.getName().equals("userInfo.properties")) {
                        localBundle.delete();
                    }
                    i += 1;
                }
            }
        });
        this.image2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                int j;
                int i;
                if ((IndexActivity.this.getCacheDir() != null) && (IndexActivity.this.getCacheDir().exists()) && (IndexActivity.this.getCacheDir().isDirectory()))
                {
                    paramAnonymousView = IndexActivity.this.getCacheDir().listFiles();
                    j = paramAnonymousView.length;
                    i = 0;
                }
                for (;;)
                {
                    if (i >= j)
                    {
                        paramAnonymousView = new Intent(IndexActivity.this, NewShowActivity.class);
                        localBundle = new Bundle();
                        localBundle.putString("mesId", IndexActivity.this.tv_id2.getText().toString());
                        localBundle.putString("flag", "mes");
                        paramAnonymousView.putExtras(localBundle);
                        IndexActivity.this.startActivity(paramAnonymousView);
                        return;
                    }
                    Bundle localBundle = paramAnonymousView[i];
                    if (!localBundle.getName().equals("userInfo.properties")) {
                        localBundle.delete();
                    }
                    i += 1;
                }
            }
        });
        this.image3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                int j;
                int i;
                if ((IndexActivity.this.getCacheDir() != null) && (IndexActivity.this.getCacheDir().exists()) && (IndexActivity.this.getCacheDir().isDirectory()))
                {
                    paramAnonymousView = IndexActivity.this.getCacheDir().listFiles();
                    j = paramAnonymousView.length;
                    i = 0;
                }
                for (;;)
                {
                    if (i >= j)
                    {
                        paramAnonymousView = new Intent(IndexActivity.this, NewShowActivity.class);
                        localBundle = new Bundle();
                        localBundle.putString("mesId", IndexActivity.this.tv_id3.getText().toString());
                        localBundle.putString("flag", "mes");
                        paramAnonymousView.putExtras(localBundle);
                        IndexActivity.this.startActivity(paramAnonymousView);
                        return;
                    }
                    Bundle localBundle = paramAnonymousView[i];
                    if (!localBundle.getName().equals("userInfo.properties")) {
                        localBundle.delete();
                    }
                    i += 1;
                }
            }
        });
        this.image4.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                int j;
                int i;
                if ((IndexActivity.this.getCacheDir() != null) && (IndexActivity.this.getCacheDir().exists()) && (IndexActivity.this.getCacheDir().isDirectory()))
                {
                    paramAnonymousView = IndexActivity.this.getCacheDir().listFiles();
                    j = paramAnonymousView.length;
                    i = 0;
                }
                for (;;)
                {
                    if (i >= j)
                    {
                        paramAnonymousView = new Intent(IndexActivity.this, NewShowActivity.class);
                        localBundle = new Bundle();
                        localBundle.putString("mesId", IndexActivity.this.tv_id4.getText().toString());
                        localBundle.putString("flag", "mes");
                        paramAnonymousView.putExtras(localBundle);
                        IndexActivity.this.startActivity(paramAnonymousView);
                        return;
                    }
                    Bundle localBundle = paramAnonymousView[i];
                    if (!localBundle.getName().equals("userInfo.properties")) {
                        localBundle.delete();
                    }
                    i += 1;
                }
            }
        });
    }

    public void lunbo()
    {
        this.viewList = new ArrayList();
        this.viewList.add(this.view1);
        this.viewList.add(this.view2);
        this.viewList.add(this.view3);
        this.viewList.add(this.view4);
        this.viewList.add(this.view5);
        this.viewList.add(this.view6);
        this.pagerAdapter = new LunBoAdapter(this.viewList);
        this.viewPager.setAdapter(this.pagerAdapter);
        this.viewPager.setCurrentItem(2);
        this.viewPager.setOnPageChangeListener(this);
    }

    @SuppressLint("HandlerLeak")
    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_index);
        getLunboImage();
        this.setLunBoInfoHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                super.handleMessage(paramAnonymousMessage);
                label327:
                Bitmap localBitmap1;
                try
                {
                    Thread.sleep(200L);
                    IndexActivity.this.rl_lunbo_progressbar.setVisibility(8);
                    IndexActivity.this.viewPager.setVisibility(0);
                    Object localObject1 = new File(IndexActivity.this.getCacheDir(), "/LunBoImage1.jpg");
                    Object localObject2 = new File(IndexActivity.this.getCacheDir(), "/LunBoInfo.txt");
                    String str = null;
                    localArrayList = new ArrayList();
                    if ((((File)localObject1).exists()) && (((File)localObject2).exists()))
                    {
                        System.out.println("������");
                        Bitmap localBitmap2 = BitmapFactory.decodeFile(IndexActivity.this.getCacheDir() + "/LunBoImage" + paramAnonymousMessage.what + ".jpg");
                        localObject1 = str;
                        try
                        {
                            localObject2 = new FileInputStream((File)localObject2);
                            localObject1 = str;
                            BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader((InputStream)localObject2));
                            localObject1 = str;
                            str = localBufferedReader.readLine();
                            localObject1 = str;
                            ((FileInputStream)localObject2).close();
                            localObject1 = str;
                            localBufferedReader.close();
                            localObject1 = str;
                        }
                        catch (Exception localException)
                        {
                            for (;;)
                            {
                                int i;
                                Toast.makeText(IndexActivity.this, "������������������������������", 1000).show();
                                localException.printStackTrace();
                                continue;
                                localArrayList.add(localInterruptedException.split("##CAI##")[(i + 1)]);
                                i += 1;
                                continue;
                                IndexActivity.this.listId.add(((String)localArrayList.get(i)).split("##TITLE##")[0]);
                                IndexActivity.this.listTitle.add(((String)localArrayList.get(i)).split("##TITLE##")[1]);
                                i += 1;
                            }
                        }
                        i = 0;
                        if (i >= 4)
                        {
                            i = 0;
                            if (i < localArrayList.size()) {
                                break label327;
                            }
                            localObject1 = localBitmap2;
                            switch (paramAnonymousMessage.what)
                            {
                                default:
                                    return;
                            }
                        }
                    }
                }
                catch (InterruptedException localInterruptedException)
                {
                    for (;;)
                    {
                        ArrayList localArrayList;
                        localInterruptedException.printStackTrace();
                        continue;
                        System.out.println("���������");
                        localBitmap1 = BitmapFactory.decodeByteArray(((MessageVO)paramAnonymousMessage.obj).getMesImage(), 0, ((MessageVO)paramAnonymousMessage.obj).getMesImage().length);
                        IndexActivity.this.listId.add(((MessageVO)paramAnonymousMessage.obj).getMesId());
                        IndexActivity.this.listTitle.add(((MessageVO)paramAnonymousMessage.obj).getMesTitle());
                    }
                    IndexActivity.this.image1.setImageBitmap(localBitmap1);
                    IndexActivity.this.textView1.setText((CharSequence)IndexActivity.this.listTitle.get(paramAnonymousMessage.what));
                    IndexActivity.this.tv_id1.setText((CharSequence)IndexActivity.this.listId.get(paramAnonymousMessage.what));
                    return;
                }
                IndexActivity.this.image2.setImageBitmap(localBitmap1);
                IndexActivity.this.textView2.setText((CharSequence)IndexActivity.this.listTitle.get(paramAnonymousMessage.what));
                IndexActivity.this.tv_id2.setText((CharSequence)IndexActivity.this.listId.get(paramAnonymousMessage.what));
                return;
                IndexActivity.this.image3.setImageBitmap(localBitmap1);
                IndexActivity.this.textView3.setText((CharSequence)IndexActivity.this.listTitle.get(paramAnonymousMessage.what));
                IndexActivity.this.tv_id3.setText((CharSequence)IndexActivity.this.listId.get(paramAnonymousMessage.what));
                return;
                IndexActivity.this.image4.setImageBitmap(localBitmap1);
                IndexActivity.this.textView4.setText((CharSequence)IndexActivity.this.listTitle.get(paramAnonymousMessage.what));
                IndexActivity.this.tv_id4.setText((CharSequence)IndexActivity.this.listId.get(paramAnonymousMessage.what));
            }
        };
        init();
        this.autoLunBoHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                super.handleMessage(paramAnonymousMessage);
                if (!IndexActivity.this.isDestroy)
                {
                    IndexActivity.this.startAutoLunbo = true;
                    IndexActivity.this.viewPager.setCurrentItem(IndexActivity.this.viewPager.getCurrentItem() + 1);
                    IndexActivity.this.autoLunBoHandler.sendEmptyMessageDelayed(IndexActivity.this.msgWhat, 2000L);
                    System.out.println("������������������");
                    return;
                }
                System.out.println("���������������������");
                IndexActivity.this.autoLunBoHandler.removeCallbacksAndMessages(null);
            }
        };
        initView();
        setImageSize();
        lunbo();
        LunBoTouch();
        lunBoClick();
        knowListClick();
        getKnowInfo();
        this.setKnowImgHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                IndexActivity.this.knowAdapter = new KnowAdapter(IndexActivity.this, (List)paramAnonymousMessage.obj);
                IndexActivity.this.listView.setAdapter(IndexActivity.this.knowAdapter);
                IndexActivity.this.listView.setOnRefreshListener(IndexActivity.this);
                super.handleMessage(paramAnonymousMessage);
            }
        };
        this.setReflushKnowImgHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                IndexActivity.this.knowAdapter = new KnowAdapter(IndexActivity.this, (List)paramAnonymousMessage.obj);
                IndexActivity.this.listView.setAdapter(IndexActivity.this.knowAdapter);
                IndexActivity.this.listView.setOnRefreshListener(IndexActivity.this);
                IndexActivity.this.listView.hideHeaderView();
                super.handleMessage(paramAnonymousMessage);
            }
        };
        this.setMoreKnowImgHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                IndexActivity.this.knowAdapter.notifyDataSetChanged();
                IndexActivity.this.listView.hideFooterView();
                super.handleMessage(paramAnonymousMessage);
            }
        };
        this.setKnowHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                super.handleMessage(paramAnonymousMessage);
                IndexActivity.this.list = ((List)paramAnonymousMessage.obj);
                IndexActivity.this.task1 = new AsyncTask()
                {
                    protected Void doInBackground(Void... paramAnonymous2VarArgs)
                    {
                        if (isCancelled()) {}
                        for (;;)
                        {
                            return null;
                            paramAnonymous2VarArgs = IndexActivity.this.list.iterator();
                            while (paramAnonymous2VarArgs.hasNext())
                            {
                                KnowVO localKnowVO = (KnowVO)paramAnonymous2VarArgs.next();
                                localKnowVO.setKnowImage(ConnWeb.downLoadImg("http://118.190.155.221:8098/StrawAdminWeb" + localKnowVO.getKnowImagePath()));
                            }
                        }
                    }

                    protected void onPostExecute(Void paramAnonymous2Void)
                    {
                        if (isCancelled()) {
                            return;
                        }
                        paramAnonymous2Void = new Message();
                        paramAnonymous2Void.obj = IndexActivity.this.list;
                        IndexActivity.this.setKnowImgHandler.sendMessage(paramAnonymous2Void);
                    }
                }.execute(new Void[0]);
            }
        };
    }

    protected void onDestroy()
    {
        this.isDestroy = true;
        super.onDestroy();
    }

    public void onDownPullRefresh()
    {
        this.listView.setClickable(false);
        this.task = new AsyncTask()
        {
            protected Void doInBackground(Void... paramAnonymousVarArgs)
            {
                if (isCancelled()) {}
                for (;;)
                {
                    return null;
                    IndexActivity.this.requestNum = 0;
                    System.out.println("������");
                    try
                    {
                        paramAnonymousVarArgs = ConnWeb.ConnForJson(IndexActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/home/KnowInfoAjax", "requestNum=0", "GET", 0, null, null);
                        paramAnonymousVarArgs = (List)JsonUtil.getGson().fromJson(paramAnonymousVarArgs, new TypeToken() {}.getType());
                        IndexActivity.this.list.clear();
                        int i = 0;
                        while (i < paramAnonymousVarArgs.size())
                        {
                            IndexActivity.this.list.add((KnowVO)paramAnonymousVarArgs.get(i));
                            i += 1;
                        }
                        return null;
                    }
                    catch (Exception paramAnonymousVarArgs)
                    {
                        paramAnonymousVarArgs.printStackTrace();
                    }
                }
            }

            protected void onPostExecute(Void paramAnonymousVoid)
            {
                if (isCancelled()) {
                    return;
                }
                IndexActivity.this.task2 = new AsyncTask()
                {
                    protected Void doInBackground(Void... paramAnonymous2VarArgs)
                    {
                        if (isCancelled()) {}
                        for (;;)
                        {
                            return null;
                            paramAnonymous2VarArgs = IndexActivity.this.list.iterator();
                            while (paramAnonymous2VarArgs.hasNext())
                            {
                                KnowVO localKnowVO = (KnowVO)paramAnonymous2VarArgs.next();
                                localKnowVO.setKnowImage(ConnWeb.downLoadImg("http://118.190.155.221:8098/StrawAdminWeb" + localKnowVO.getKnowImagePath()));
                            }
                        }
                    }

                    protected void onPostExecute(Void paramAnonymous2Void)
                    {
                        if (isCancelled()) {
                            return;
                        }
                        paramAnonymous2Void = new Message();
                        paramAnonymous2Void.obj = IndexActivity.this.list;
                        IndexActivity.this.setReflushKnowImgHandler.sendMessage(paramAnonymous2Void);
                    }
                }.execute(new Void[0]);
            }
        }.execute(new Void[0]);
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
    {
        if (paramInt == 4) {
            showExitDialog();
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    public void onLoadingMore()
    {
        this.task3 = new AsyncTask()
        {
            protected Void doInBackground(Void... paramAnonymousVarArgs)
            {
                if (isCancelled()) {}
                for (;;)
                {
                    return null;
                    System.out.println("������������");
                    try
                    {
                        paramAnonymousVarArgs = IndexActivity.this;
                        paramAnonymousVarArgs.requestNum += 1;
                        paramAnonymousVarArgs = ConnWeb.ConnForJson(IndexActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/home/KnowInfoAjax", "requestNum=" + IndexActivity.this.requestNum, "GET", 0, null, null);
                        paramAnonymousVarArgs = (List)JsonUtil.getGson().fromJson(paramAnonymousVarArgs, new TypeToken() {}.getType());
                        int i = 0;
                        while (i < paramAnonymousVarArgs.size())
                        {
                            IndexActivity.this.list.add((KnowVO)paramAnonymousVarArgs.get(i));
                            i += 1;
                        }
                        return null;
                    }
                    catch (Exception paramAnonymousVarArgs)
                    {
                        paramAnonymousVarArgs.printStackTrace();
                    }
                }
            }

            protected void onPostExecute(Void paramAnonymousVoid)
            {
                if (isCancelled()) {}
                for (;;)
                {
                    return;
                    int i = 0;
                    while (i < IndexActivity.this.list.size())
                    {
                        new Thread()
                        {
                            public void run()
                            {
                                System.out.println(this.val$vo.getKnowImagePath() + "ss");
                                Object localObject = ConnWeb.downLoadImg("http://118.190.155.221:8098/StrawAdminWeb" + this.val$vo.getKnowImagePath());
                                this.val$vo.setKnowImage((Bitmap)localObject);
                                localObject = new Message();
                                ((Message)localObject).obj = this.val$vo;
                                IndexActivity.this.setMoreKnowImgHandler.sendMessage((Message)localObject);
                            }
                        }.start();
                        i += 1;
                    }
                }
            }
        }.execute(new Void[0]);
    }

    public void onPageScrollStateChanged(int paramInt) {}

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {}

    public void onPageSelected(int paramInt)
    {
        Bitmap localBitmap;
        if (paramInt == 1)
        {
            localBitmap = BitmapFactory.decodeFile(getCacheDir() + "/LunBoImage3.jpg");
            this.image0.setImageBitmap(localBitmap);
            this.textView0.setText((CharSequence)this.listTitle.get(3));
            this.tv_id0.setText((CharSequence)this.listId.get(3));
        }
        if ((paramInt == 3) || (paramInt == 4))
        {
            localBitmap = BitmapFactory.decodeFile(getCacheDir() + "/LunBoImage0.jpg");
            this.image5.setImageBitmap(localBitmap);
            this.textView5.setText((CharSequence)this.listTitle.get(0));
            this.tv_id5.setText((CharSequence)this.listId.get(0));
        }
        if (this.viewList.size() > 1)
        {
            if (paramInt >= 1) {
                break label193;
            }
            this.viewPager.setCurrentItem(4, false);
        }
        label193:
        while (paramInt <= 4) {
            return;
        }
        this.viewPager.setCurrentItem(1, false);
    }

    protected void onPause()
    {
        this.isDestroy = true;
        if ((this.task != null) && (this.task.getStatus() == AsyncTask.Status.RUNNING)) {
            this.task.cancel(true);
        }
        if ((this.task1 != null) && (this.task1.getStatus() == AsyncTask.Status.RUNNING)) {
            this.task1.cancel(true);
        }
        if ((this.task2 != null) && (this.task2.getStatus() == AsyncTask.Status.RUNNING)) {
            this.task2.cancel(true);
        }
        if ((this.task3 != null) && (this.task3.getStatus() == AsyncTask.Status.RUNNING)) {
            this.task3.cancel(true);
        }
        super.onPause();
    }

    protected void onRestart()
    {
        this.isDestroy = false;
        this.autoLunBoHandler.sendEmptyMessageDelayed(this.msgWhat, 2000L);
        super.onRestart();
    }

    protected void onResume()
    {
        super.onResume();
    }

    protected void onStart()
    {
        super.onStart();
    }

    protected void onStop()
    {
        this.isDestroy = true;
        super.onStop();
    }

    public void setImageSize()
    {
        Object localObject = getWindowManager();
        int i = ((WindowManager)localObject).getDefaultDisplay().getWidth();
        ((WindowManager)localObject).getDefaultDisplay().getHeight();
        localObject = this.image0.getLayoutParams();
        ViewGroup.LayoutParams localLayoutParams1 = this.image1.getLayoutParams();
        ViewGroup.LayoutParams localLayoutParams2 = this.image2.getLayoutParams();
        ViewGroup.LayoutParams localLayoutParams3 = this.image3.getLayoutParams();
        ViewGroup.LayoutParams localLayoutParams4 = this.image4.getLayoutParams();
        ViewGroup.LayoutParams localLayoutParams5 = this.image5.getLayoutParams();
        ((ViewGroup.LayoutParams)localObject).width = i;
        localLayoutParams1.width = i;
        localLayoutParams2.width = i;
        localLayoutParams3.width = i;
        localLayoutParams4.width = i;
        localLayoutParams5.width = i;
        this.image0.setLayoutParams((ViewGroup.LayoutParams)localObject);
        this.image1.setLayoutParams(localLayoutParams1);
        this.image2.setLayoutParams(localLayoutParams2);
        this.image3.setLayoutParams(localLayoutParams3);
        this.image4.setLayoutParams(localLayoutParams4);
        this.image5.setLayoutParams(localLayoutParams5);
    }
}
