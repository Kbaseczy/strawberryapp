package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cai.strawberryapp.Adapter.LeaveListAdapter;
import com.cai.strawberryapp.BaseClass.ConnWeb;
import com.cai.strawberryapp.BaseClass.FooterMenu;
import com.cai.strawberryapp.BaseClass.HeaderMenu;
import com.cai.strawberryapp.BaseClass.JsonUtil;
import com.cai.strawberryapp.VO.LeaveQueVO;
import com.cai.strawberryapp.View.OnRefreshListener;
import com.cai.strawberryapp.View.RefreshListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeaveActivity
        extends Activity
        implements OnRefreshListener
{
    private ImageView iv_new_que;
    LeaveListAdapter leaveListAdapter;
    private RefreshListView leaveListView;
    private Button leave_but;
    private List<LeaveQueVO> list;
    private LinearLayout ll_container;
    private int mLastY = 0;
    private int mYDown = 0;
    private List<LeaveQueVO> mlist;
    private int requestNum = 0;
    private RelativeLayout rl_lunbo_progressbar;
    private Handler setKnowImgHandler;
    private Handler setLeaveListHandler;
    private Handler setMoreKnowImgHandler;
    private Handler setReflushKnowImgHandler;
    private AsyncTask task1;
    private AsyncTask task2;
    private AsyncTask task3;
    private String username;

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

    public void addNewQueClick()
    {
        this.iv_new_que.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                paramAnonymousView = new Intent(LeaveActivity.this, NewQueActivity.class);
                LeaveActivity.this.startActivity(paramAnonymousView);
            }
        });
    }

    public void getLeaveListInfo()
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Object localObject = ConnWeb.ConnForJson(LeaveActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/LeaveListInfoAjax", "requestNum=0&username=" + LeaveActivity.this.username, "GET", 0, null, null);
                    LeaveActivity.this.list = ((List)JsonUtil.getGson().fromJson((String)localObject, new TypeToken() {}.getType()));
                    localObject = new Message();
                    ((Message)localObject).obj = LeaveActivity.this.list;
                    LeaveActivity.this.setLeaveListHandler.sendMessage((Message)localObject);
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
        try
        {
            this.username = ((String)localBundle.get("username"));
            this.mlist = new ArrayList();
            this.leave_but = ((Button)findViewById(2131361803));
            this.rl_lunbo_progressbar = ((RelativeLayout)findViewById(2131361817));
            this.ll_container = ((LinearLayout)findViewById(2131361821));
            this.leave_but.setBackgroundResource(2130903058);
            this.leaveListView = ((RefreshListView)findViewById(2131361822));
            this.iv_new_que = ((ImageView)findViewById(2131361823));
            if (!this.username.equals("a"))
            {
                new HeaderMenu(this, false, "������������", null).initHeaderBut();
                new FooterMenu(this).initFooterBut();
                return;
            }
        }
        catch (Exception localException)
        {
            for (;;)
            {
                this.username = "a";
                continue;
                new HeaderMenu(this, false, "������", null).initHeaderBut();
            }
        }
    }

    public void leaveListViewItemClick()
    {
        this.leaveListView.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
            {
                switch (paramAnonymousMotionEvent.getAction())
                {
                }
                for (;;)
                {
                    if ((LeaveActivity.this.mYDown - LeaveActivity.this.mLastY < 10) && (LeaveActivity.this.mYDown - LeaveActivity.this.mLastY > -10)) {
                        LeaveActivity.this.leaveListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
                            {
                                paramAnonymous2AdapterView = (TextView)paramAnonymous2View.findViewById(2131361941);
                                TextView localTextView1 = (TextView)paramAnonymous2View.findViewById(2131361942);
                                TextView localTextView2 = (TextView)paramAnonymous2View.findViewById(2131361943);
                                TextView localTextView3 = (TextView)paramAnonymous2View.findViewById(2131361944);
                                TextView localTextView4 = (TextView)paramAnonymous2View.findViewById(2131361949);
                                TextView localTextView5 = (TextView)paramAnonymous2View.findViewById(2131361947);
                                paramAnonymous2View = (Button)paramAnonymous2View.findViewById(2131361946);
                                Intent localIntent = new Intent(LeaveActivity.this, LeaveDetailActivity.class);
                                Bundle localBundle = new Bundle();
                                localBundle.putString("queId", paramAnonymous2AdapterView.getText().toString());
                                localBundle.putString("queUser", localTextView1.getText().toString());
                                localBundle.putString("queTitle", localTextView2.getText().toString());
                                localBundle.putString("queContent", localTextView3.getText().toString());
                                localBundle.putString("queTime", localTextView4.getText().toString());
                                localBundle.putString("queAudioTime", localTextView5.getText().toString());
                                localBundle.putString("queAudioPath", paramAnonymous2View.getText().toString());
                                localIntent.putExtras(localBundle);
                                LeaveActivity.this.startActivity(localIntent);
                            }
                        });
                    }
                    return false;
                    LeaveActivity.this.mYDown = ((int)paramAnonymousMotionEvent.getRawY());
                    continue;
                    LeaveActivity.this.mLastY = ((int)paramAnonymousMotionEvent.getRawY());
                }
            }
        });
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130968583);
        init();
        getLeaveListInfo();
        leaveListViewItemClick();
        addNewQueClick();
        this.setMoreKnowImgHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                LeaveActivity.this.leaveListAdapter.notifyDataSetChanged();
                LeaveActivity.this.leaveListView.hideFooterView();
                super.handleMessage(paramAnonymousMessage);
            }
        };
        this.setReflushKnowImgHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                LeaveActivity.this.mlist = ((List)paramAnonymousMessage.obj);
                LeaveActivity.this.leaveListAdapter = new LeaveListAdapter(LeaveActivity.this, LeaveActivity.this.mlist);
                LeaveActivity.this.leaveListView.setAdapter(LeaveActivity.this.leaveListAdapter);
                LeaveActivity.this.leaveListView.setOnRefreshListener(LeaveActivity.this);
                LeaveActivity.this.leaveListView.hideHeaderView();
                super.handleMessage(paramAnonymousMessage);
            }
        };
        this.setKnowImgHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                LeaveActivity.this.mlist = ((List)paramAnonymousMessage.obj);
                try
                {
                    Thread.sleep(200L);
                    LeaveActivity.this.rl_lunbo_progressbar.setVisibility(8);
                    LeaveActivity.this.ll_container.setVisibility(0);
                    LeaveActivity.this.leaveListAdapter = new LeaveListAdapter(LeaveActivity.this, LeaveActivity.this.mlist);
                    LeaveActivity.this.leaveListView.setAdapter(LeaveActivity.this.leaveListAdapter);
                    LeaveActivity.this.leaveListView.setOnRefreshListener(LeaveActivity.this);
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
        this.setLeaveListHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                super.handleMessage(paramAnonymousMessage);
                new AsyncTask()
                {
                    protected Void doInBackground(Void... paramAnonymous2VarArgs)
                    {
                        paramAnonymous2VarArgs = this.val$list.iterator();
                        for (;;)
                        {
                            if (!paramAnonymous2VarArgs.hasNext()) {
                                return null;
                            }
                            LeaveQueVO localLeaveQueVO = (LeaveQueVO)paramAnonymous2VarArgs.next();
                            if (!localLeaveQueVO.getQueImgPath().equals("")) {
                                localLeaveQueVO.setQueImg(ConnWeb.downLoadImg("http://118.190.155.221:8098/StrawAdminWeb" + localLeaveQueVO.getQueImgPath()));
                            }
                        }
                    }

                    protected void onPostExecute(Void paramAnonymous2Void)
                    {
                        paramAnonymous2Void = new Message();
                        paramAnonymous2Void.obj = this.val$list;
                        LeaveActivity.this.setKnowImgHandler.sendMessage(paramAnonymous2Void);
                    }
                }.execute(new Void[0]);
            }
        };
    }

    public void onDownPullRefresh()
    {
        new AsyncTask()
        {
            protected Void doInBackground(Void... paramAnonymousVarArgs)
            {
                LeaveActivity.this.requestNum = 0;
                System.out.println("������");
                try
                {
                    paramAnonymousVarArgs = ConnWeb.ConnForJson(LeaveActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/LeaveListInfoAjax", "requestNum=0&username=" + LeaveActivity.this.username, "GET", 0, null, null);
                    paramAnonymousVarArgs = (List)JsonUtil.getGson().fromJson(paramAnonymousVarArgs, new TypeToken() {}.getType());
                    LeaveActivity.this.mlist.clear();
                    int i = 0;
                    for (;;)
                    {
                        if (i >= paramAnonymousVarArgs.size()) {
                            return null;
                        }
                        LeaveActivity.this.mlist.add((LeaveQueVO)paramAnonymousVarArgs.get(i));
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
                new AsyncTask()
                {
                    protected Void doInBackground(Void... paramAnonymous2VarArgs)
                    {
                        paramAnonymous2VarArgs = LeaveActivity.this.mlist.iterator();
                        for (;;)
                        {
                            if (!paramAnonymous2VarArgs.hasNext()) {
                                return null;
                            }
                            LeaveQueVO localLeaveQueVO = (LeaveQueVO)paramAnonymous2VarArgs.next();
                            localLeaveQueVO.setQueImg(ConnWeb.downLoadImg("http://118.190.155.221:8098/StrawAdminWeb" + localLeaveQueVO.getQueImgPath()));
                        }
                    }

                    protected void onPostExecute(Void paramAnonymous2Void)
                    {
                        if (isCancelled()) {
                            return;
                        }
                        paramAnonymous2Void = new Message();
                        paramAnonymous2Void.obj = LeaveActivity.this.list;
                        LeaveActivity.this.setReflushKnowImgHandler.sendMessage(paramAnonymous2Void);
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
        this.task1 = new AsyncTask()
        {
            protected Void doInBackground(Void... paramAnonymousVarArgs)
            {
                System.out.println("������������");
                try
                {
                    paramAnonymousVarArgs = LeaveActivity.this;
                    paramAnonymousVarArgs.requestNum += 1;
                    paramAnonymousVarArgs = ConnWeb.ConnForJson(LeaveActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/leave/LeaveListInfoAjax", "requestNum=" + LeaveActivity.this.requestNum + "&username=" + LeaveActivity.this.username, "GET", 0, null, null);
                    paramAnonymousVarArgs = (List)JsonUtil.getGson().fromJson(paramAnonymousVarArgs, new TypeToken() {}.getType());
                    int i = 0;
                    for (;;)
                    {
                        if (i >= paramAnonymousVarArgs.size()) {
                            return null;
                        }
                        LeaveActivity.this.mlist.add((LeaveQueVO)paramAnonymousVarArgs.get(i));
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
                if ((LeaveActivity.this.task2 != null) && (LeaveActivity.this.task2.getStatus() == AsyncTask.Status.RUNNING)) {
                    LeaveActivity.this.task2.cancel(true);
                }
                LeaveActivity.this.task2 = new AsyncTask()
                {
                    protected Void doInBackground(Void... paramAnonymous2VarArgs)
                    {
                        paramAnonymous2VarArgs = LeaveActivity.this.mlist.iterator();
                        for (;;)
                        {
                            if (!paramAnonymous2VarArgs.hasNext()) {
                                return null;
                            }
                            LeaveQueVO localLeaveQueVO = (LeaveQueVO)paramAnonymous2VarArgs.next();
                            if (!localLeaveQueVO.getQueImgPath().equals("")) {
                                localLeaveQueVO.setQueImg(ConnWeb.downLoadImg("http://118.190.155.221:8098/StrawAdminWeb" + localLeaveQueVO.getQueImgPath()));
                            }
                        }
                    }

                    protected void onPostExecute(Void paramAnonymous2Void)
                    {
                        paramAnonymous2Void = new Message();
                        LeaveActivity.this.setMoreKnowImgHandler.sendMessage(paramAnonymous2Void);
                    }
                }.execute(new Void[0]);
                paramAnonymousVoid = new Message();
                LeaveActivity.this.setMoreKnowImgHandler.sendMessage(paramAnonymousVoid);
            }
        }.execute(new Void[0]);
    }

    protected void onPause()
    {
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
}
