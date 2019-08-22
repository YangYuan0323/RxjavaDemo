package com.rxjavademo.main;

/**
 * 转换接口,专门用于变换的操作
 */
public interface Func1<T,R> {
    R call(T t);
}
