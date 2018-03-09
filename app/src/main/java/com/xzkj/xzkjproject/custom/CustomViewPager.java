package com.xzkj.xzkjproject.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * <p>
 * 自定义一个能手动控制移动viewpager
 * </p>
 * <br/>
 * <p>
 * TODO (类的详细的功能描述)
 * </p>
 *
 * @author xky
 * @since 1.0.0
 */
public class CustomViewPager extends ViewPager {

    private boolean mIsScrollable = true;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * <p>
     * 设置是否可以滚动
     * </p>
     * <br/>
     * <p>
     * TODO(详细描述)
     * </p>
     *
     * @param isScrollable
     * @author xky
     * @since 1.0.0
     */
    public void setScrollable(boolean isScrollable) {
        mIsScrollable = isScrollable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        try {
            return mIsScrollable ? super.onInterceptTouchEvent(arg0) : false;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        try {
            return mIsScrollable ? super.onTouchEvent(arg0) : false;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
