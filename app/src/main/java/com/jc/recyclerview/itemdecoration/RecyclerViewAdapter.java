package com.jc.recyclerview.itemdecoration;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jc.recyclerview.itemdecoration.bean.RecyclerBean;

import java.util.List;

/**
 * Created by 江俊超 on 2019/1/23.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<RecyclerBean> mDatas;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public RecyclerViewAdapter(List<RecyclerBean> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mText.setText(mDatas.get(position).getRoomName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mText;

        public ViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.mText);
        }
    }
}
