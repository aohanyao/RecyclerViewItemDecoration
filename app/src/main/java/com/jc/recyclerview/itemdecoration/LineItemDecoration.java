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

import com.jc.recyclerview.itemdecoration.bean.RecyclerBean;

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

    private String TAG = getClass().getSimpleName();

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
            RecyclerBean lastRecyclerBean = null;
            if (position == 0) { // 绘制第一个
                mPaint.setColor(Color.GRAY);
                lastRecyclerBean = mDatas.get(position);
                drawH(c, left, right, top, bottom, lastRecyclerBean);

                // 绘制左边的楼层信息
                // 左边的楼层信息
                c.drawRect(new Rect(left, child.getBottom() - child.getHeight(),
                        mLeftWidth + child.getPaddingLeft(), child.getBottom() + 2), mPaint);

            } else {
                // 不等于最后一个
                RecyclerBean currentRecyclerBean = mDatas.get(position);
                lastRecyclerBean = mDatas.get(position - 1);
                if (!lastRecyclerBean.getStageName().equals(currentRecyclerBean.getStageName())) {
                    drawH(c, left, right, top, bottom, lastRecyclerBean);
                    // 绘制分期间隔底部的分割线
                    mPaint.setColor(Color.BLUE);
                    c.drawRect(new Rect(left, top, right, top - mLineHeight), mPaint);
                }
                // 绘制左边的楼层信息
                // 当前这个和上一个不一样，绘制当前这个
                if (!currentRecyclerBean.getFloorName().equals(lastRecyclerBean.getFloorName())) {

                    // 分期之间不要绘制楼层的线条
                    // 绘制楼层之间分割线
                    if (i <= mDatas.size() - 2) {
                        RecyclerBean nextRecyclerBean = mDatas.get(position + 1);
                        if (currentRecyclerBean.getStageName().equals(nextRecyclerBean.getStageName())) {
                            mPaint.setColor(Color.parseColor("#cccccc"));
                            c.drawRect(new Rect(left, child.getBottom(), right, child.getBottom() + 2), mPaint);
                        }
                    }

                    mPaint.setColor(Color.parseColor("#cccccc"));
                    // 左边的楼层信息
                    Rect floorRect = new Rect(left, child.getBottom() - child.getHeight(),
                            mLeftWidth + child.getPaddingLeft(), child.getBottom() + 2);
                    c.drawRect(floorRect, mPaint);
                    mPaint.setColor(Color.BLACK);
                    c.drawText(currentRecyclerBean.getFloorName(),
                            mLeftWidth - 80,
                            floorRect.bottom - 100,
                            mPaint);
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
        c.drawText(recyclerBean.getStageName(), dp2px(10), top + dp2px(25), mPaint);
        // 绘制底部的分割线
        mPaint.setColor(Color.parseColor("#cccccc"));


    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        // 从item的下标开始计算的，
//        outRect.set(0, position % 4 == 0 ? mTitleHeight : 0, 0, 0);
        int top = 0;
        int bottom = 0;


        //ItemView的position==0 或者 当前ItemView的data的tag和上一个ItemView的不相等，则为当前ItemView设置top 偏移量
        if (position == 0) {
            top = mTitleHeight;
        } else {
            RecyclerBean currentRecyclerBean = mDatas.get(position);
            RecyclerBean nextRecyclerBean = mDatas.get(position - 1);
            if (!currentRecyclerBean.getStageName().equals(nextRecyclerBean.getStageName())) {
                top = mTitleHeight + mLineHeight;
//                bottom=mLineHeight;
            }
        }


        outRect.set(mLeftWidth, top, 0, bottom);
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
