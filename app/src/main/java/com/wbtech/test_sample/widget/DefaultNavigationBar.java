package com.wbtech.test_sample.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wbtech.test_sample.R;

public class DefaultNavigationBar extends AbsNavigationBar {
    /**
     * 设置参数
     *
     * @param buidler
     */
    protected DefaultNavigationBar(Buidler buidler) {
        super(buidler);
    }

    public static class Buidler extends AbsNavigationBar.Buidler{

        public Buidler(Context context, ViewGroup parent) {
            super(context, R.layout.toolbar_layout, parent);
        }

        @Override
        public AbsNavigationBar create() {
            return new DefaultNavigationBar(this);
        }

        public Buidler setText(String text){
            setText(R.id.title,text);
            return this;
        }

        public Buidler setOnClickListener(View.OnClickListener onClickListener){
            setOnClickListener(R.id.title,onClickListener);
            return this;
        }

    }
}
