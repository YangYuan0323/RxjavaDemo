package com.rxjavademo.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OnSubscribleThread<T> implements OnSubscrible<T> {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    Observable<T> source;//持有上一个的引用

    public OnSubscribleThread(Observable<T> source) {
        this.source = source;
    }

    @Override
    public void call(final Subscrible<? super T> subscrible) {
        Runnable runnable = new Runnable(){

            @Override
            public void run() {
                source.subscrible(subscrible);
            }
        };
        executorService.submit(runnable);
    }
}
