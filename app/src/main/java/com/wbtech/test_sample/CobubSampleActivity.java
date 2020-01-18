
package com.wbtech.test_sample;

//import com.wbtech.ums.UmsAgent.SendPolicy;
//import com.wbtech.ums.UmsAgent.SendPolicy;

//import com.tesla.tmd.UmsAgent;
//import com.tesla.tmd.UmsAgent.SendPolicy;

//import com.tesla.tmd.UmsAgent;
//import com.tesla.tmd.UmsAgent.SendPolicy;

//import com.tendcloud.tenddata.TCAgent;


//import com.tesla.tmd.UmsAgent;
//import com.tesla.tmd.UmsAgent.SendPolicy;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.thread.EventThread;
import com.wbtech.test_sample.event.ServiceToActivityEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Before you run App,you need check: 
 * 1.Import appkey (generated in server ) to
 * AndroidManifest.xml such as <meta-data android:name="UMS_APPKEY"
 * android:value="bb08202a625c2b5cae5e2632f604352f "/> 
 * 
 * 2.Permissions in AndroidManifest.xml 
 * <uses-permission
 * android:name="android.permission.INTERNET"/> <uses-permission
 * android:name="android.permission.WRITE_EXTERNAL_STORAGE"/> <uses-permission
 * android:name="android.permission.READ_PHONE_STATE"/> <uses-permission
 * android:name="android.permission.ACCESS_FINE_LOCATION"/> <uses-permission
 * android:name="android.permission.ACCESS_WIFI_STATE"/> <uses-permission
 * android:name="android.permission.GET_TASKS"/> <uses-permission
 * android:name="android.permission.READ_LOGS"/> <uses-permission
 * android:name="android.permission.ACCESS_NETWORK_STATE"/> <uses-permission
 * android:name="android.permission.INSTALL_PACKAGES"/>
 */
public class CobubSampleActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxBus.get().register(this);
        EventBus.getDefault().register(this);
        Thread

//        startJonIntentService();
          startJobService();
//        startService();
//        startTimerTask();


    }

    /**
     * 启动JobIntentService
      */
    public void startJonIntentService(){
        Intent intent = new Intent();
        MyJobIntentService.enqueueWork(this, MyJobIntentService.class,0, intent);
    }

    /**
     * 启动JobService
     */
    public void startJobService(){
        int jobId1 = 120;
        int jobId2 = 121;

        JobScheduler mJobScheduler = (JobScheduler) getApplicationContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        //job1
        JobInfo.Builder builder = new JobInfo.Builder(jobId1,new ComponentName(getApplicationContext(),MyJobService.class));
        //以下约束条件要至少满足一个，否则调用JobInfo.Buidler的build方法时会抛异常IllegalArgumentException
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);//任何网络环境下都可以执行
        builder.setRequiresCharging(false);//是否在只有插入充电器的时候执行
        builder.setRequiresDeviceIdle(false);//是否手机系统处于空闲状态下执行
        mJobScheduler.schedule(builder.build());

        //job2
        JobInfo.Builder builder1 = new JobInfo.Builder(jobId2, new ComponentName(getApplicationContext(),MyJobService.class));
        //以下约束条件要至少满足一个，否则调用JobInfo.Buidler的build方法时会抛异常IllegalArgumentException
        builder1.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);//任何网络环境下都可以执行
        builder1.setRequiresCharging(false);//是否在只有插入充电器的时候执行
        builder1.setRequiresDeviceIdle(false);//是否手机系统处于空闲状态下执行
        mJobScheduler.schedule(builder1.build());

    }

    /**
     * 启动普通Service
     */
    public void startService(){
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }

    /***
     * 启动定时任务 -- ScheduledThreadPoolExecutor
     * scheduleWithFixedDelay -- 从上一个任务结束后开始计算
     * scheduleAtFixedRate -- 从上一个任务开始执行后开始计算,但是对于运行时长超过延时时长，会等上一个任务结束后开始计算
     * schedule           -- 保证间隔是稳定的
     */
    public void startTimerTask(){
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(2);

        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.i("xiongliang","ScheduledThreadPool 执行定时任务1");
            }
        }, 0, 5, TimeUnit.SECONDS);
//
//
        scheduled.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Log.i("xiongliang","ScheduledThreadPool 执行定时任务2");
            }
        }, 0, 12, TimeUnit.SECONDS);

        scheduled.schedule(new Runnable() {
            @Override
            public void run() {
                Log.i("xiongliang","ScheduledThreadPool 执行定时任务3");
            }
        },12, TimeUnit.SECONDS);

    }



    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().post("123");
        super.onDestroy();
    }

    @Subscribe(thread = EventThread.MAIN_THREAD)
    public void serviceToActivity(ServiceToActivityEvent serviceToActivityEvent){
          Log.i("xiongliang","Activity 接受到Service 传递的消息");
    }
}
