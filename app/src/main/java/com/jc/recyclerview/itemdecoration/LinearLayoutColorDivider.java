package com.jc.recyclerview.itemdecoration;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LinearLayoutColorDivider extends RecyclerView.ItemDecoration {
    private final Drawable mDivider;
    private final int mSize;

    public LinearLayoutColorDivider(Resources resources, @ColorRes int color,
                                    @DimenRes int size) {
        mDivider = new ColorDrawable(resources.getColor(color));
        mSize = resources.getDimensionPixelSize(size);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left;
        int right;
        int top;
        int bottom;

        left = parent.getPaddingLeft();
        right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();
            top = child.getBottom() + params.bottomMargin;
            bottom = top + mSize;
            mDivider.setBounds(left, top, right, bottom);
            if (i % 3 == 0)
                mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        //ItemView的position==0 或者 当前ItemView的data的tag和上一个ItemView的不相等，则为当前ItemView设置top 偏移量

        outRect.set(0, 0, 0, 0);
    }
}