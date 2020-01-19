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
     * 功能:收纳参数
     * 静态类:  https://cloud.tencent.com/developer/article/1497439
     * 1) 静态类只能作为内部类创建
     * 2) 静态内部类作用,只是为了降低包的深度,实现高内聚,静态内部类不依赖外部类,只能使用外部类的静态属性,方法。 A.B b = new A.B()
     *    非静态内部类作用,不能有静态属性和方法,对外部类有引用,可以使用外部类的所有变量和方法, A a = new A(), A.B b = a.new B()
     * 3) 静态方法/属性: 类装载时加载到内存共享区域,直到JVM关闭才会销毁。
     *    非静态方法/属性: 对象实例化后才会分配内存,实例对象被JVM回收后,也跟着回收。
     *
     * 抽象类/接口：
     * 1) 接口中所有成员属性为 public static final,不能被继承; 接口中所有方法都是public
     * 2) 抽象类中方法只能为public,protected,default 三种
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
