package com.example.menurecommendation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

public class RecipeSearchFragment extends Fragment {

    final static String DATA_RECEIVE = "data_receive";

    private RecyclerView mRecyclerView;
    private ImageListAdapter mAdapter;
    private String searchWord;
    private LinkedList<String> mImagePathList;
    private LinkedList<String> mCuisineNameList;

    private int span = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_searching, container, false);
        Bundle args = getArguments();
        if (args != null)
            searchWord = args.getString(DATA_RECEIVE);
        mRecyclerView = rootView.findViewById(R.id.recyclerview);
        mAdapter = new ImageListAdapter(getContext(), mImagePathList, mCuisineNameList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), span));
        return rootView;
    }

}
