package com.jc.recyclerview.itemdecoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import java.util.List;

/**
 * Created by 江俊超 on 2019/1/23.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class LineItemDecoration extends RecyclerView.ItemDecoration {

    private int mTitleHeight = (int) dp2px(38);
    private int mLeftWidth = (int) dp2px(48);
    private int mLineHeight = (int) dp2px(10);

    private Paint mPaint;
    List<RecyclerBean> mDatas;

    public LineItemDecoration(Context context, List<RecyclerBean> datas) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(sp2px(18));
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
            RecyclerBean nextRecyclerBean = null;
            if (position == 0) { // 绘制第一个
                mPaint.setColor(Color.GRAY);
                nextRecyclerBean = mDatas.get(position);
                drawH(c, left, right, top, bottom, nextRecyclerBean);

                // 绘制左边的楼层信息


            } else {
                // 不等于最后一个
                RecyclerBean recyclerBean = mDatas.get(position);
                nextRecyclerBean = mDatas.get(position - 1);
                if (!nextRecyclerBean.getText().equals(recyclerBean.getText())) {
                    drawH(c, left, right, top + mLineHeight, bottom + mLineHeight, nextRecyclerBean);
                    // 绘制底部的分割线
                    c.drawRect(new Rect(left, top, right, top + mLineHeight), mPaint);
                    // 绘制左边的楼层信息
                }
            }


        }
    }

    private void drawH(Canvas c, int left, int right, int top, int bottom, RecyclerBean recyclerBean) {
        mPaint.setColor(Color.WHITE);
        c.drawRect(new Rect(left, top, right, bottom), mPaint);
        mPaint.setColor(Color.GRAY);
        // 划线条
        c.drawRect(new Rect(left, bottom - 1, right, bottom), mPaint);
        // 绘制文字
        c.drawText(recyclerBean.getText(), dp2px(10), top + dp2px(25), mPaint);
        // 绘制底部的分割线
        mPaint.setColor(Color.parseColor("#cccccc"));


    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        // 从item的下标开始计算的，
//        outRect.set(0, position % 4 == 0 ? mTitleHeight : 0, 0, 0);
        int top = 0;


        //ItemView的position==0 或者 当前ItemView的data的tag和上一个ItemView的不相等，则为当前ItemView设置top 偏移量
        if (position == 0) {
            top = mTitleHeight;
        } else {
            RecyclerBean recyclerBean = mDatas.get(position);
            RecyclerBean nextRecyclerBean = mDatas.get(position - 1);
            if (!recyclerBean.getText().equals(nextRecyclerBean.getText())) {
                top = mTitleHeight + mLineHeight;
            }
        }


        outRect.set(mLeftWidth, top, 0, 0);
    }

    public float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, Resources.getSystem().getDisplayMetrics());
    }

    public float sp2px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, Resources.getSystem().getDisplayMetrics());
    }
}
