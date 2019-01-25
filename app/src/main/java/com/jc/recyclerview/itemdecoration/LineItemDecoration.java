package com.jc.recyclerview.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by 江俊超 on 2019/1/23.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class LineItemDecoration extends RecyclerView.ItemDecoration {

    private int mTitleHeight = 80;

    private Paint mPaint;
    List<RecyclerBean> mDatas;

    public LineItemDecoration(Context context, List<RecyclerBean> datas) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDatas = datas;
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        drawVertical(c, parent);
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            final View child = parent.getChildAt(i);
            final int top = child.getTop() - mTitleHeight;
            final int bottom = top + mTitleHeight;
            int position = parent.getChildAdapterPosition(child);
            // 而不是从View的下标开始计算
//            if (position % 4 == 0) {
//                c.drawRect(new Rect(left, top, right, bottom), mPaint);
//            }

            if (position == 0) { // 绘制第一个
                mPaint.setColor(Color.GRAY);
                c.drawRect(new Rect(left, top, right, bottom), mPaint);
            } else {
                // 不等于最后一个
                RecyclerBean recyclerBean = mDatas.get(position);
                RecyclerBean nextRecyclerBean = mDatas.get(position - 1);
                if (!nextRecyclerBean.getText().equals(recyclerBean.getText())) {
                    c.drawRect(new Rect(left, top, right, bottom), mPaint);
                }
            }


        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        // 从item的下标开始计算的，
//        outRect.set(0, position % 4 == 0 ? mTitleHeight : 0, 0, 0);


        //ItemView的position==0 或者 当前ItemView的data的tag和上一个ItemView的不相等，则为当前ItemView设置top 偏移量
        if (position == 0) {
            outRect.set(0, mTitleHeight, 0, 0);
        } else {
            RecyclerBean recyclerBean = mDatas.get(position);
            RecyclerBean nextRecyclerBean = mDatas.get(position - 1);
            if (!recyclerBean.getText().equals(nextRecyclerBean.getText())) {
                outRect.set(0, mTitleHeight, 0, 0);
            }
        }
    }
}
