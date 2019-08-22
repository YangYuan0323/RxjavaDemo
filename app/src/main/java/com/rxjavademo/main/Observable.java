package com.rxjavademo.main;

/**
 * observable主要营造一个场景
 * @param <T> 泛型
 */
public class Observable<T> {

    //被观察者
    private OnSubscrible<T> onSubscrible;

    public Observable(OnSubscrible<T> onSubscrible) {
        this.onSubscrible = onSubscrible;
    }

    public static <T> Observable<T> create(OnSubscrible<T> onSubscrible){
        return new Observable<>(onSubscrible);
    }

    /**
     * 订阅
     * @param subscrible
     */
    public void subscrible(Subscrible<? super T> subscrible){
        onSubscrible.call(subscrible);
    };

    public <R> Observable<R> map(Func1<? super T, ? extends R> func1){
        return new Observable<R>(new OnSubscribleLift<T, R>(onSubscrible, func1));
    }

    public Observable<T> subscribleOnIO(){
      return create(new OnSubscribleThread<T>(this));
    };
}
