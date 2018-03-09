package com.xzkj.xzkjproject.custom;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.xzkj.xzkjproject.R;
import com.xzkj.xzkjproject.custom.indicator.UnderlinePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>自定义一个带标题的viewpager【最多4个】</p><br/>
 *
 * @author xky
 * @since 1.0.0
 */
public class BottomTitleViewPager extends RelativeLayout implements OnClickListener, OnPageChangeListener {

    private Context mContext;
    private LayoutInflater mInflater;
    private View mView;
    private LinearLayout mTitleContainer;
    private TextView mTitle1;
    private TextView mTitle2;
    private TextView mTitle3;
    private TextView mTitle4;
    private LinearLayout mLineContainer;
    private ImageView mLine1;
    private ImageView mLine2;
    private ImageView mLine3;
    private ImageView mLine4;
    private CustomViewPager mViewPager;
    private int mLastPosition = 0;
    private String[] mTitles;
    private PagerAdapter mAdapter;
    private UnderlinePageIndicator mIndicator;
    private OnPageSelectListener mPageListener;
    private List<TextView> mTitleViews;
    private TextView mTitle5;
    private ImageView mLine5;
    private RelativeLayout mTitleContainer1;
    private RelativeLayout mTitleContainer2;
    private RelativeLayout mTitleContainer3;
    private RelativeLayout mTitleContainer4;
    private RelativeLayout mTitleContainer5;
    private ImageView mTitleImg1;
    private ImageView mTitleImg2;
    private ImageView mTitleImg3;
    private ImageView mTitleImg4;
    private ImageView mTitleImg5;
    private List<ImageView> mIcons;
    private ImageView mNew_msg_show;

    public BottomTitleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        initView();
        setListener();
    }


    /**
     * <p>设置回掉</p><br/>
     *
     * @author xky
     * @since 1.0.0
     */
    private void setListener() {
        mTitleContainer1.setOnClickListener(this);
        mTitleContainer2.setOnClickListener(this);
        mTitleContainer3.setOnClickListener(this);
//		mTitleContainer4.setOnClickListener(this);
//		mTitleContainer5.setOnClickListener(this);

//		mViewPager.setOnPageChangeListener(this);
        mIndicator.setOnPageChangeListener(this);
    }

    private void initView() {
        mView = mInflater.inflate(R.layout.custom_titleviewpager, this);
        mNew_msg_show = (ImageView) mView.findViewById(R.id.new_msg_show);
        mIndicator = (UnderlinePageIndicator) mView.findViewById(R.id.indicator);
        mTitleContainer = (LinearLayout) mView.findViewById(R.id.vp_title_container);

        mTitleContainer1 = (RelativeLayout) mView.findViewById(R.id.vp_title1_container);
        mTitleContainer2 = (RelativeLayout) mView.findViewById(R.id.vp_title2_container);
        mTitleContainer3 = (RelativeLayout) mView.findViewById(R.id.vp_title3_container);
//		mTitleContainer4 = (RelativeLayout) mView.findViewById(R.id.vp_title4_container);
//		mTitleContainer5 = (RelativeLayout) mView.findViewById(R.id.vp_title5_container);

        mTitleImg1 = (ImageView) mView.findViewById(R.id.vp_img1);
        mTitleImg2 = (ImageView) mView.findViewById(R.id.vp_img2);
        mTitleImg3 = (ImageView) mView.findViewById(R.id.vp_img3);
//		mTitleImg4 = (ImageView) mView.findViewById(R.id.vp_img4);
//		mTitleImg5 = (ImageView) mView.findViewById(R.id.vp_img5);


        mTitle1 = (TextView) mView.findViewById(R.id.vp_title1);
        mTitle2 = (TextView) mView.findViewById(R.id.vp_title2);
        mTitle3 = (TextView) mView.findViewById(R.id.vp_title3);
//		mTitle4 = (TextView) mView.findViewById(R.id.vp_title4);
//		mTitle5 = (TextView) mView.findViewById(R.id.vp_title5);
        mTitleViews = new ArrayList<TextView>();
        mTitleViews.add(mTitle1);
        mTitleViews.add(mTitle2);
        mTitleViews.add(mTitle3);
//		mTitleViews.add(mTitle4);

//		mCenterViewContainer = mView.findViewById(R.id.bottom_center_view_container);
//		if (Device.checkCanRecored()){
//			mCenterViewContainer.setVisibility(View.VISIBLE);
//		}else {
//			mCenterViewContainer.setVisibility(View.GONE);
//		}
//		mTitleViews.add(mTitle5);


        mIcons = new ArrayList<ImageView>();
        mIcons.add(mTitleImg1);
        mIcons.add(mTitleImg2);
        mIcons.add(mTitleImg3);
//		mIcons.add(mTitleImg4);
//		mIcons.add(mTitleImg5);

        mLineContainer = (LinearLayout) mView.findViewById(R.id.vp_line_contaienr);
        mLine1 = (ImageView) mView.findViewById(R.id.vp_line1);
        mLine2 = (ImageView) mView.findViewById(R.id.vp_line2);
        mLine3 = (ImageView) mView.findViewById(R.id.vp_line3);
//		mLine4 = (ImageView) mView.findViewById(R.id.vp_line4);
//		mLine5 = (ImageView) mView.findViewById(R.id.vp_line5);

        mViewPager = (CustomViewPager) mView.findViewById(R.id.viewpager);

        mViewPager.setScrollable(false);


//		HorizontalListView.setOnHorizonScrollListener(new OnHorizonScrollListener() {
//
//			@Override
//			public void onHorizonScroll(boolean isHorizonScroll) {
//				if( isHorizonScroll){
//					mViewPager.setScrollable(false);
//				}else{
//					mViewPager.setScrollable(true);
//				}
//			}
//		});

//		mViewPager.setOffscreenPageLimit(5);

        mSelectIcons = new ArrayList<Integer>();
        mSelectIcons.add(R.mipmap.nav_1_2);
        mSelectIcons.add(R.mipmap.nav_2_2);
        mSelectIcons.add(R.mipmap.nav_3_2);
//		mSelectIcons.add(R.drawable.tabbar_mine_pre);
//		mSelectIcons.add(R.drawable.exclusive_ic);


        mNormalIcons = new ArrayList<Integer>();
        mNormalIcons.add(R.mipmap.nav_1_1);
        mNormalIcons.add(R.mipmap.nav_2_1);
        mNormalIcons.add(R.mipmap.nav_3_1);
//		mNormalIcons.add(R.drawable.tabbar_mine_n);
//		mNormalIcons.add(R.drawable.game_ic_gray);d
//		mNormalIcons.add(R.drawable.exclusive_ic_gray);

    }


    @Override
    public void onClick(View v) {
        int index = 1;
        switch (v.getId()) {
            case R.id.vp_title1_container:
                index = 0;
                break;
            case R.id.vp_title2_container:
                index = 1;
                break;
            case R.id.vp_title3_container:
                index = 2;
                break;
//		case R.id.vp_title4_container:
//			 index = 3;
//			break;
//		case R.id.vp_title5_container:
//			index = 4;
//			break;

            default:
                break;
        }
        mViewPager.setCurrentItem(index);
    }

    /***********************
     * OnPageChangeListener
     **********************************/
    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    private int lastCachePosition = 1;
    private List<Integer> mSelectIcons;
    private List<Integer> mNormalIcons;

    @Override
    public void onPageSelected(int position) {
        changeLine(position, getTitleLength());
        if (mPageListener != null) {
            mPageListener.onPageSelect(position);
        }
        if (position >= lastCachePosition) {
            lastCachePosition = position;
            mViewPager.setOffscreenPageLimit(lastCachePosition);
        }
        changeColor(position);
    }
    /***********************end**********************************/
    /**
     * <p>改变动画</p><br/>
     *
     * @param position
     * @author xky
     * @since 1.0.0
     */
    private void changeLine(int position, int size) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int width = dm.widthPixels / size;
        TranslateAnimation anim = new TranslateAnimation(mLastPosition * width, position * width, 0, 0);
        mLastPosition = position;
        anim.setFillAfter(true);
        anim.setDuration(300);
        mLine1.startAnimation(anim);
    }

    /**
     * <p>改变标题颜色</p><br/>
     *
     * @param position
     * @author xky
     * @since 1.0.0
     */
    private void changeColor(int position) {
        //TODO
        tvChangeNormal();
        mTitleViews.get(position).setTextColor(mContext.getResources().getColor(R.color.tele_viewpager_title_color));
        mIcons.get(position).setImageResource(mSelectIcons.get(position));
    }

    private void tvChangeNormal() {
        for (TextView tv : mTitleViews) {
            tv.setTextColor(mContext.getResources().getColor(R.color.color_normal_));
        }
        for (int i = 0; i < mIcons.size(); i++) {
            mIcons.get(i).setImageResource(mNormalIcons.get(i));
        }
    }

    private int getTitleLength() {
        return mTitles == null ? 0 : mTitles.length;
    }

    /**
     * <p>为viewpager设置标题</p><br/>
     *
     * @param titles
     * @author xky
     * @since 1.0.0
     */
    public void setTitles(String[] titles) {
        mTitles = titles;
        initTitle();
    }

    /**
     * <p>为title赋值</p><br/>
     *
     * @author xky
     * @since 1.0.0
     */
    private void initTitle() {
        hiddenLineAndTitle();
        for (int i = 0; i < getTitleLength(); i++) {
            switch (i) {
                case 0:
                    setText(mTitle1, mLine1, mTitles[i], i, mTitleContainer1);
                    break;
                case 1:
                    setText(mTitle2, mLine2, mTitles[i], i, mTitleContainer2);
                    break;
                case 2:
                    setText(mTitle3, mLine3, mTitles[i], i, mTitleContainer3);
                    break;
//			case 3:
//				setText(mTitle4,mLine4,mTitles[i],i,mTitleContainer4);
//				break;
//			case 4:
//				setText(mTitle5,mLine5,mTitles[i],i,mTitleContainer5);
//				break;

                default:
                    break;
            }
        }
    }

    /**
     * <p>隐藏</p><br/>
     *
     * @author xky
     * @since 1.0.0
     */
    private void hiddenLineAndTitle() {
        mTitleContainer1.setVisibility(View.GONE);
        mTitleContainer2.setVisibility(View.GONE);
        mTitleContainer3.setVisibility(View.GONE);
//		mTitleContainer4.setVisibility(View.GONE);
//		mTitleContainer5.setVisibility(View.GONE);

        mLine1.setVisibility(View.GONE);
        mLine2.setVisibility(View.GONE);
        mLine3.setVisibility(View.GONE);
//		mLine4.setVisibility(View.GONE);
//		mLine5.setVisibility(View.GONE);
    }

    /**
     * <p>设置当前的条目</p><br/>
     * <p>TODO(详细描述)</p>
     *
     * @param position
     * @author xky
     * @since 1.0.0
     */
    public void setCurrentItem(int position) {
        if (position < 0) {
            position = 0;
        }
        if (position > (mTitles.length - 1)) {
            position = mTitles.length - 1;
        }
        changeColor(position);
        mViewPager.setCurrentItem(position);
    }

    /**
     * <p>设置值</p><br/>
     *
     * @param tv
     * @param iv
     * @param text
     * @param mTitleContainer12
     * @author xky
     * @since 1.0.0
     */
    private void setText(TextView tv, ImageView iv, String text, int position, RelativeLayout mTitleContainer12) {
        tv.setText(text);
        mTitleContainer12.setVisibility(View.VISIBLE);
        if (position == 0) {
            iv.setVisibility(View.VISIBLE);
        } else {
            iv.setVisibility(View.INVISIBLE);
        }
    }

    public void setAdapter(PagerAdapter adapter) {
        if (adapter != null) {
            mViewPager.setAdapter(adapter);
            mIndicator.setViewPager(mViewPager);
            mIndicator.setFades(false);
            mIndicator.setSelectedColor(mContext.getResources().getColor(R.color.line_yellow));
        }
        mAdapter = adapter;
    }

    /**
     * <p>页面选中时调用</p><br/>
     *
     * @author xky
     * @since 1.0.0
     */
    public interface OnPageSelectListener {
        public void onPageSelect(int position);
    }

    public void setOnPageSelectListener(OnPageSelectListener pageListener) {
        mPageListener = pageListener;
    }

    public void setNewRedView(int type, int visibility) {
        switch (type) {
            case 0:
                mNew_msg_show.setVisibility(visibility);
                break;
        }

    }

}
