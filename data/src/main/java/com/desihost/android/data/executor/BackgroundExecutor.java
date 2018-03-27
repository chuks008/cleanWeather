package com.desihost.android.data.executor;

import android.support.annotation.NonNull;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import executor.ThreadExecutor;

// this could have used the computational thread or the io thread, but instead its offers a new thread

@Singleton
public class BackgroundExecutor implements ThreadExecutor {

    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    BackgroundExecutor() {
        this.threadPoolExecutor = new ThreadPoolExecutor(3,5,10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new BackgroundThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        this.threadPoolExecutor.execute(runnable);
    }


    private static class BackgroundThreadFactory implements ThreadFactory {

        private int counter = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "android_"+counter++);
        }
    }
}
