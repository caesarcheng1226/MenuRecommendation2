package com.example.menurecommendation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class RecipeFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    LinkedHashMap<String, List<String>> expandableListDetail;

    DataPassListener mCallback;

    public interface DataPassListener{
        public void passData(String data);
    }

    int[] listviewImage = new int[]{
            R.drawable.meat_banner, R.drawable.vegetable_banner,
            R.drawable.soup_banner, R.drawable.seafood_banner,
            R.drawable.fruit_banner,
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);

        expandableListView = rootView.findViewById(R.id.expandableListView);
        expandableListDetail = RecipeData.getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail, listviewImage);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                mCallback.passData(parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString());
                return false;
            }
        });

        return rootView;
    }
}
