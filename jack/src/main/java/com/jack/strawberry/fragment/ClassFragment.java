package com.jack.strawberry.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.jack.strawberry.R;
import com.jack.strawberry.adapter.KnowAdapter;
import com.jack.strawberry.utils.Utils;
import com.jack.strawberry.vo.KnowVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClassFragment extends Fragment  {

    private View view;
    private List<KnowVO> knowVOList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_class, null);
        getData();
        initView();
        return view;
    }

    private void getData() {
        try {
            knowVOList = new ArrayList<>();
            String homeJson = Utils.getStrFromAssets(getActivity(), "class");
            JSONObject homeObj = new JSONObject(homeJson);

            JSONArray knowArray = homeObj.optJSONArray("know");
            for (int i = 0; i < knowArray.length(); i++) {
                JSONObject itemObj = knowArray.optJSONObject(i);
                KnowVO knowVO = new KnowVO();
                knowVO.setKnowId(itemObj.optString("id"));
                knowVO.setKnowImage(R.drawable.image13);
                knowVO.setKnowTitle(itemObj.optString("title"));
                knowVO.setKnowTime(itemObj.optString("time"));
                knowVO.setKnowLinkUrl(itemObj.optString("url"));
                knowVOList.add(knowVO);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        view.findViewById(R.id.back_but).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.tv_header)).setText("课堂");
        ListView listView = view.findViewById(R.id.home_listview);
        KnowAdapter knowAdapter = new KnowAdapter(getActivity(), knowVOList);
        listView.setAdapter(knowAdapter);
    }

}
