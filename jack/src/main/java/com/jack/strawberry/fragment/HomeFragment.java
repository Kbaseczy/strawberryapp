package com.jack.strawberry.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.jack.strawberry.R;
import com.jack.strawberry.adapter.KnowAdapter;
import com.jack.strawberry.adapter.LunBoAdapter;
import com.jack.strawberry.utils.Utils;
import com.jack.strawberry.vo.ImageVO;
import com.jack.strawberry.vo.KnowVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View view;
    private Context context;
    private int[] imageIds = new int[]{R.drawable.image10, R.drawable.image11, R.drawable.image12};
    private List<ImageVO> imageVOList;
    private List<KnowVO> knowVOList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        context = getContext();
        getData();
        initView();
        return view;
    }

    private void getData() {
        try {
            imageVOList = new ArrayList<>();
            knowVOList = new ArrayList<>();

            String homeJson = Utils.getStrFromAssets(context, "home");
            JSONObject homeObj = new JSONObject(homeJson);

            JSONArray imageArray = homeObj.optJSONArray("image");
            JSONArray knowArray = homeObj.optJSONArray("know");

            for (int i = 0; i < imageArray.length(); i++) {
                JSONObject imageObj = imageArray.optJSONObject(i);
                ImageVO imageVO = new ImageVO();
                imageVO.imageResource = imageIds[i];
                imageVO.desc = imageObj.optString("desc");
                imageVO.linkURL = imageObj.optString("link");
                imageVOList.add(imageVO);
            }

            for (int i = 0; i < knowArray.length(); i++) {
                JSONObject itemObj = knowArray.optJSONObject(i);
                KnowVO knowVO = new KnowVO();
                knowVO.setKnowId(itemObj.optString("id"));
                knowVO.setKnowImage(R.drawable.image12);
                knowVO.setKnowTitle(itemObj.optString("title"));
                knowVO.setKnowTime(itemObj.optString("time"));
                knowVO.setKnowLinkUrl(itemObj.optString("link"));
                knowVOList.add(knowVO);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        view.findViewById(R.id.back_but).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.tv_header)).setText("首页");

        ViewPager viewPager = view.findViewById(R.id.guide_viewPager);
        LunBoAdapter lunBoAdapter = new LunBoAdapter(context, imageVOList);
        viewPager.setAdapter(lunBoAdapter);

        ListView listView = view.findViewById(R.id.home_listview);
        KnowAdapter knowAdapter = new KnowAdapter(context, knowVOList);
        listView.setAdapter(knowAdapter);
    }


}
