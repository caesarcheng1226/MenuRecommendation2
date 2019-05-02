package com.example.menurecommendation;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {

    private LayoutInflater mInflater;
    private final LinkedList<String> mImagePathList;
    private final LinkedList<String> mCuisineNameList;

    class ImageViewHolder extends RecyclerView.ViewHolder {

        public final ImageView imageItemView;
        public final TextView textItemView;
        final ImageListAdapter mAdapter;

        public ImageViewHolder(View itemView, ImageListAdapter adapter) {
            super(itemView);
            imageItemView = itemView.findViewById(R.id.cuisineImage);
            textItemView = itemView.findViewById(R.id.cuisineName);
            this.mAdapter = adapter;
        }

    }

    public ImageListAdapter(Context context, LinkedList<String> imagePathList, LinkedList<String> cuisineNameList) {
        mInflater = LayoutInflater.from(context);
        this.mImagePathList = imagePathList;
        this.mCuisineNameList = cuisineNameList;
    }

    @Override public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.imagelist_item, parent, false);
        return new ImageViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ImageListAdapter.ImageViewHolder holder, int position) {
        String mImagePath = mImagePathList.get(position);
        String mCuisineName = mCuisineNameList.get(position);
        Uri uri = Uri.parse(mImagePath);
        holder.imageItemView.setImageURI(uri);
        holder.textItemView.setText(mCuisineName);
    }

    @Override
    public int getItemCount() {
        return mImagePathList.size();
    }
}
