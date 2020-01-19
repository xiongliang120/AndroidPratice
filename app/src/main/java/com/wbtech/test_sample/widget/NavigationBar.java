package com.wbtech.test_sample.widget;

import android.content.Context;
import android.view.ViewGroup;

public class NavigationBar extends AbsNavigationBar {
    /**
     * 设置参数
     *
     * @param buidler
     */
    protected NavigationBar(Buidler buidler) {
        super(buidler);
    }

    public static class Buidler extends AbsNavigationBar.Buidler{

        public Buidler(Context context, int layoutId, ViewGroup parent) {
            super(context, layoutId, parent);
        }

        /**
         * 具体创建什么类型NavigationBar 交给子类去处理
         */
        @Override
        public AbsNavigationBar create() {
            return new NavigationBar(this);  //this 为什么可以获取到属性
        }
    }


}
