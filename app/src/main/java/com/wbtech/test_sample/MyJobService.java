package com.wbtech.test_sample;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.hwangjr.rxbus.RxBus;
import com.wbtech.test_sample.event.ServiceToActivityEvent;


/**
 * JobService 分析地址：https://blog.csdn.net/u011311586/article/details/83027820
 *
 */
public class MyJobService extends JobService {
    /**
     * JobService 默认在主线程处理,对于耗时操作,必须开子线程处理
     *
     * 返回值:
     * false -- 表示JobScheduler认为Job任务已经处理完,会直接解绑JobService, 不会执行onStopJob;
     * true -- 任务没有处理完,会执行onStopJob
     *
     *
     * @param params
     * @return
     */
    @Override
    public boolean onStartJob(JobParameters params) {
        //do Task
        Log.i("xiongliang","MyJobService 执行 onStartJob  JobId="+ params.getJobId());
        if( params.getJobId() == 120){
            RxBus.get().post(new ServiceToActivityEvent("11"));
            doServiceJob1();
        }else if(params.getJobId() == 121){
            doServiceJob2();
        }
        return true;
    }


    public void doServiceJob1(){
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(120, new ComponentName(this, MyJobService.class));  //指定哪个JobService执行操作
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            builder.setMinimumLatency(10000);
        }else{
            builder.setPeriodic(10000);
        }
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);//任何网络环境下都可以执行
        builder.setRequiresCharging(false);//是否在只有插入充电器的时候执行
        builder.setRequiresDeviceIdle(false);//是否手机系统处于空闲状态下执行

        jobScheduler.schedule(builder.build());
    }

    public void doServiceJob2(){
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(120, new ComponentName(this, MyJobService.class));  //指定哪个JobService执行操作
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            builder.setMinimumLatency(2000);
        }else{
            builder.setPeriodic(2000);
        }
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);//任何网络环境下都可以执行
        builder.setRequiresCharging(false);//是否在只有插入充电器的时候执行
        builder.setRequiresDeviceIdle(false);//是否手机系统处于空闲状态下执行

        jobScheduler.schedule(builder.build());
    }


    /**
     *  返回值:
     *  true -- 表示把任务重新调度
     * @param params
     * @return
     */
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i("xiongliang","MyJobService 执行onStopJob");
        return false;
    }

    @Override
    public void onDestroy() {
        Log.i("xiongliang","onDestroy");
        super.onDestroy();

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("xiongliang","onUnbind");
        return super.onUnbind(intent);
    }
}
