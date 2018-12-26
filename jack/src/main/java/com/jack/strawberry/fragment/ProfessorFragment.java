package com.jack.strawberry.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jack.strawberry.R;
import com.jack.strawberry.adapter.RecyclerAdapterProfessor;
import com.jack.strawberry.vo.ProfessorAnwser;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfessorFragment extends Fragment {
    View view;
    private List<ProfessorAnwser.LeaveBean> list;
    public void setList(List<ProfessorAnwser.LeaveBean> list) {
        this.list = list;
    }

    public static ProfessorFragment newInstance(){
        return new ProfessorFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_professor, container, false);
        initView();
        return view;
    }

    private void initView(){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_professor);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        RecyclerAdapterProfessor adapterProfessor = new RecyclerAdapterProfessor(getActivity());
        Log.v("sdfasdf",list.get(6).getProblem()+"<-----youmeiyou");
        adapterProfessor.setLeaveBeans(list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterProfessor);
    }
}
