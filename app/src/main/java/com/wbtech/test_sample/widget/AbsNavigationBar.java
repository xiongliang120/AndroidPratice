package com.wbtech.test_sample.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

public class AbsNavigationBar implements INavigationBar{
    /**
     * 设置参数
     * @param buidler
     */
    protected AbsNavigationBar(Buidler buidler) {
        attachViewToParent(buidler.context);
    }

    /**
     * 将布局添加到父布局
     */
    private void attachViewToParent(Context context){
        View layout = LayoutIn
    }

    /**
     * 给布局设置参数
     */
    private void attachViewParams(){

    }


    /**
     * 收纳参数
     */
    public static class Buidler {
        private Context context;
        private int layoutId;
        private ViewGroup parent;
        private Map<Integer,String> textMap = null;
        private Map<Integer, View.OnClickListener> onClickMap = null;

        public Buidler(Context context,int layoutId,ViewGroup parent) {
            this.context = context;
            this.layoutId = layoutId;
            this.parent = parent;
            this.textMap  = new HashMap<>();
            this.onClickMap = new HashMap<>();
        }


        public Buidler setText(int resouceId,String text){
            textMap.put(resouceId,text);
            return this;
        }

        public Buidler setOnClickListener(int resourceId,View.OnClickListener clickListener){
            onClickMap.put(resourceId,clickListener);
            return this;
        }

    }
}
