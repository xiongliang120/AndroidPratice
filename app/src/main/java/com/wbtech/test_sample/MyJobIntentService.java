package com.wbtech.test_sample;

import android.app.job.JobService;
import android.content.Intent;
import android.support.v4.app.JobIntentService;
import android.util.Log;

public class MyJobIntentService extends JobIntentService {
    @Override
    protected void onHandleWork(Intent intent) {
        int jobType = intent.getIntExtra("jobid", 0);
        if (jobType == 0) {
            Log.i("xiongliang","执行任务1");



        }else if(jobType ==1){
            Log.i("xiongliang","执行任务2");
        }
    }
}
