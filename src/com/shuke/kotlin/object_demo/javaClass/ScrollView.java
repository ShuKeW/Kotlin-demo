package com.shuke.kotlin.object_demo.javaClass;

import org.jetbrains.annotations.NotNull;

/**
 * 模拟android的点击事件
 */
public class ScrollView {
    private OnClickListener onClickListener;
    private OnScrollListener onScrollListener;
    private String id;

    public static interface OnClickListener {
        void onClick(ScrollView scrollView);
    }

    public static interface OnScrollListener {
        void onScrolling();

        void onScrolled();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public OnScrollListener getOnScrollListener() {
        return onScrollListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
