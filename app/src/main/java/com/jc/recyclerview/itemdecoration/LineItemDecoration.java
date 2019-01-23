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
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
    }


    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RecyclerBean recyclerBean = mDatas.get(i);
            final View child = parent.getChildAt(i);
            final int top = child.getTop() - mTitleHeight;
            final int bottom = top + mTitleHeight;

            if (i == 0) { // 绘制第一个
                mPaint.setColor(Color.GRAY);
                c.drawRect(new Rect(left, top, right, bottom), mPaint);
            } else {
                // 不等于最后一个
                RecyclerBean nextRecyclerBean = mDatas.get(i - 1);
                if (!nextRecyclerBean.getText().equals(recyclerBean.getText())) {
                    c.drawRect(new Rect(left, top, right, bottom), mPaint);
                }
            }


        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        // 返回总宽度？？？
        outRect.set(0, mTitleHeight, -1, -1);
    }
}
