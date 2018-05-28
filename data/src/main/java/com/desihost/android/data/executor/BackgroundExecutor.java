package com.desihost.android.data.executor;

import android.support.annotation.NonNull;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import executor.ThreadExecutor;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

// this could have used the computational thread or the io thread, but instead its offers a new thread

@Singleton
public class BackgroundExecutor implements ThreadExecutor {

    @Inject
    BackgroundExecutor(){}

    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}
