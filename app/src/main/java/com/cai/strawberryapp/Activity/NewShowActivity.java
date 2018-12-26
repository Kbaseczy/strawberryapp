package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.cai.strawberryapp.BaseClass.ConnWeb;
import com.cai.strawberryapp.BaseClass.HeaderMenu;
import com.cai.strawberryapp.BaseClass.JsonUtil;
import com.cai.strawberryapp.VO.KnowVO;
import com.cai.strawberryapp.VO.MessageVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.PrintStream;
import java.util.List;

public class NewShowActivity
        extends Activity
{
    private Bitmap bm;
    private TextView come;
    private TextView content;
    private ImageView imageView;
    private Handler setKnowImgInfoHandler;
    private Handler setKnowInfoHandler;
    private Handler setMesInfoHandler;
    private TextView time;
    private TextView title;

    public void allScreenPic()
    {
        this.imageView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                new Intent(NewShowActivity.this, AllScreamShowActivity.class);
            }
        });
    }

    public void getKnowInfo(final String paramString1, final String paramString2)
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Object localObject = ConnWeb.ConnForJson(NewShowActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/home/KnowMesDetailAjax", "mesId=" + paramString1 + "&flag=" + paramString2, "GET", 0, null, null);
                    System.out.println("================eeeeeee================");
                    localObject = (List)JsonUtil.getGson().fromJson((String)localObject, new TypeToken() {}.getType());
                    Message localMessage = new Message();
                    localMessage.obj = ((List)localObject).get(0);
                    NewShowActivity.this.setKnowInfoHandler.sendMessage(localMessage);
                    return;
                }
                catch (Exception localException)
                {
                    localException.printStackTrace();
                }
            }
        }.start();
    }

    public void getMesInfo(final String paramString1, final String paramString2)
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Object localObject = ConnWeb.ConnForJson(NewShowActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/home/KnowMesDetailAjax", "mesId=" + paramString1 + "&flag=" + paramString2, "GET", 0, null, null);
                    System.out.println("================eeeeeee================");
                    localObject = (List)JsonUtil.getGson().fromJson((String)localObject, new TypeToken() {}.getType());
                    Message localMessage = new Message();
                    localMessage.obj = ((List)localObject).get(0);
                    NewShowActivity.this.setMesInfoHandler.sendMessage(localMessage);
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
        this.title = ((TextView)findViewById(2131361852));
        this.imageView = ((ImageView)findViewById(2131361853));
        this.content = ((TextView)findViewById(2131361854));
        this.time = ((TextView)findViewById(2131361855));
        this.come = ((TextView)findViewById(2131361856));
        Bundle localBundle = getIntent().getExtras();
        if (localBundle.getString("flag").equals("mes"))
        {
            new HeaderMenu(this, true, "������", null).initHeaderBut();
            getMesInfo(localBundle.getString("mesId"), localBundle.getString("flag"));
            this.setMesInfoHandler = new Handler()
            {
                public void handleMessage(Message paramAnonymousMessage)
                {
                    new MessageVO();
                    MessageVO localMessageVO = (MessageVO)paramAnonymousMessage.obj;
                    NewShowActivity.this.title.setText(localMessageVO.getMesTitle());
                    NewShowActivity.this.content.setText(localMessageVO.getMesContent());
                    NewShowActivity.this.time.setText(localMessageVO.getMesTime());
                    NewShowActivity.this.come.setText("���������" + localMessageVO.getMesCome());
                    NewShowActivity.this.bm = BitmapFactory.decodeByteArray(localMessageVO.getMesImage(), 0, localMessageVO.getMesImage().length);
                    NewShowActivity.this.imageView.setImageBitmap(NewShowActivity.this.bm);
                    super.handleMessage(paramAnonymousMessage);
                }
            };
        }
        while (!localBundle.getString("flag").equals("know")) {
            return;
        }
        new HeaderMenu(this, true, "������", null).initHeaderBut();
        getKnowInfo(localBundle.getString("mesId"), localBundle.getString("flag"));
        this.setKnowInfoHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                final KnowVO localKnowVO = (KnowVO)paramAnonymousMessage.obj;
                NewShowActivity.this.title.setText(localKnowVO.getKnowTitle());
                NewShowActivity.this.content.setText(localKnowVO.getKnowContent());
                NewShowActivity.this.time.setText(localKnowVO.getKnowTime());
                NewShowActivity.this.come.setText("���������" + localKnowVO.getKnowCome());
                new Thread()
                {
                    public void run()
                    {
                        Bitmap localBitmap = ConnWeb.downLoadImg("http://118.190.155.221:8098/StrawAdminWeb" + localKnowVO.getKnowImagePath());
                        Message localMessage = new Message();
                        localMessage.obj = localBitmap;
                        NewShowActivity.this.setKnowImgInfoHandler.sendMessage(localMessage);
                    }
                }.start();
                super.handleMessage(paramAnonymousMessage);
            }
        };
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130968589);
        this.setKnowImgInfoHandler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                System.out.println("ssssssddddd" + (Bitmap)paramAnonymousMessage.obj);
                NewShowActivity.this.imageView.setImageBitmap((Bitmap)paramAnonymousMessage.obj);
                super.handleMessage(paramAnonymousMessage);
            }
        };
        init();
    }
}
