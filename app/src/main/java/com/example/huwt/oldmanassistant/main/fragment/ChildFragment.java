package com.example.huwt.oldmanassistant.main.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.huwt.oldmanassistant.R;
import com.example.huwt.oldmanassistant.TodoAdapter;
import com.example.huwt.oldmanassistant.db.TodoList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ChildFragment extends Fragment {
    private CircleFragment.OnFragmentInteractionListener mListener;

    private RecyclerView rvTodo;
    private RecyclerView.LayoutManager rvLmLinearVictor;
    private TodoAdapter todoAdapter;
    /**
     * 创建
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initData();
        initView();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child, container, false);
    }


    private void initData(){
        rvLmLinearVictor = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        todoAdapter = new TodoAdapter(TodoList.getTodoData()); // todo 在此处设置参数
    }
    private void initView(){
        // 查找 RecyclerView
        rvTodo = getActivity().findViewById(R.id.rv_todo);
        // 设置LayoutManager 为linearLayoutManager
        rvTodo.setLayoutManager(rvLmLinearVictor);
        // 设置Adapter
        rvTodo.setAdapter(todoAdapter);
    }



    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CircleFragment.OnFragmentInteractionListener) {
            mListener = (CircleFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
