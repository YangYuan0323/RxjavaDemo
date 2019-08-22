package com.rxjavademo.main;

/**
 * 观察者
 * @param <T>
 */
public abstract class Subscrible<T> {
    public abstract  void onNext(T t);
}
