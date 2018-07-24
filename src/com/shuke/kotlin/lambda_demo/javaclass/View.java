package com.shuke.kotlin.lambda_demo.javaclass;

/**
 * 模拟android的点击事件
 */
public class View {
    private OnClickListener onClickListener;
    private String id;

    public static interface OnClickListener {
        void onClick(View view);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
