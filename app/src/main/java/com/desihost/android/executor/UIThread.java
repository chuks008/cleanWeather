package com.desihost.android.executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import executor.PostExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

// this class is separated from the dependency injection so that it could provide a way
// to tell the app what thread the task should show results in
// the post execution thread interface just offered a method to do so

@Singleton
public class UIThread implements PostExecutionThread {

    // make it available in the object graph with @Inject

    @Inject
    UIThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
