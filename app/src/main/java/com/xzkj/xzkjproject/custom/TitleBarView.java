package com.xzkj.xzkjproject.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xzkj.xzkjproject.R;
import com.zhy.autolayout.AutoRelativeLayout;


/**
 * Created by sunqi on 2018/1/3.
 */

public class TitleBarView extends RelativeLayout implements View.OnClickListener {
    private AutoRelativeLayout left_view_btn, right_view_btn;
    private TextView left_view_text, right_view_text;
    private ImageView left_view_icon, right_view_icon;
    private TextView title_text;
    private InTitltOnClickLisente titltOnClickLisente;

    public TitleBarView(Context context) {
        this(context, null);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_title_view, null, false);
        left_view_btn = view.findViewById(R.id.left_view_btn);
        right_view_btn = view.findViewById(R.id.right_view_btn);
        left_view_text = view.findViewById(R.id.left_view_text);
        right_view_text = view.findViewById(R.id.right_view_text);
        left_view_icon = view.findViewById(R.id.left_view_icon);
        title_text = view.findViewById(R.id.title_text);
        right_view_icon = view.findViewById(R.id.right_view_icon);

        left_view_btn.setOnClickListener(this);
        right_view_btn.setOnClickListener(this);
        addView(view);
    }

    public TitleBarView setTitle(String title) {
        title_text.setText(title);
        title_text.setVisibility(VISIBLE);
        return this;
    }

    public TitleBarView setLeftViewText(String leftText) {
        left_view_text.setText(leftText);
        left_view_text.setVisibility(VISIBLE);
        return this;
    }

    public TitleBarView setRightViewText(String rightText) {
        right_view_text.setText(rightText);
        right_view_text.setVisibility(VISIBLE);
        return this;
    }

    public TitleBarView setLeftViewIcon(int resId) {
        left_view_icon.setImageResource(resId);
        left_view_icon.setVisibility(VISIBLE);
        return this;
    }

    public TitleBarView setRightViewIcon(int resId) {
        right_view_icon.setImageResource(resId);
        right_view_icon.setVisibility(VISIBLE);
        return this;
    }

    public TitleBarView setLiftIsShow(int visible) {
        left_view_btn.setVisibility(visible);
        return this;
    }

    public TitleBarView setRightIsShow(int visible) {
        right_view_btn.setVisibility(visible);
        return this;
    }

    public void setLeftRightOnClickCallback(InTitltOnClickLisente clickCallback) {
        this.titltOnClickLisente = clickCallback;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_view_btn:
                if (titltOnClickLisente != null) {
                    titltOnClickLisente.onLeftOnClick();
                }
                break;
            case R.id.right_view_btn:
                if (titltOnClickLisente != null) {
                    titltOnClickLisente.onRightOnClick();
                }
                break;
        }
    }

    public interface InTitltOnClickLisente {
        void onLeftOnClick();

        void onRightOnClick();

    }
}
