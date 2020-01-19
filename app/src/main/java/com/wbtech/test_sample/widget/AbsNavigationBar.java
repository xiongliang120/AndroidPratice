package com.wbtech.test_sample.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class AbsNavigationBar implements INavigationBar{
    private Buidler buidler;
    private View layoutView;
    /**
     * 设置参数
     * @param buidler
     */
    protected AbsNavigationBar(Buidler buidler) {
        this.buidler = buidler;
        attachViewToParent();
        attachViewParams();
    }

    /**
     * 将布局添加到父布局
     */
    private void attachViewToParent(){
        layoutView = LayoutInflater.from(buidler.context).inflate(buidler.layoutId,buidler.parent);
    }

    /**
     * 给布局设置参数
     */
    private void attachViewParams(){
        for(Map.Entry<Integer,String> entry: buidler.textMap.entrySet()){
            TextView textView = layoutView.findViewById(entry.getKey());
            textView.setText(entry.getValue());
        }

        for(Map.Entry<Integer,View.OnClickListener> entry: buidler.onClickMap.entrySet()){
            View view = layoutView.findViewById(entry.getKey());
            view.setOnClickListener(entry.getValue());
        }

    }


    /**
     * 收纳参数
     */
    public abstract static class Buidler {
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

        public abstract AbsNavigationBar create();
    }
}
