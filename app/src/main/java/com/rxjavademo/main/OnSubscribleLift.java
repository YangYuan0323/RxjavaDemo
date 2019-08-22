package com.rxjavademo.main;

/**
 *
 * @param <T>
 * @param <R>
 */
public class OnSubscribleLift<T,R> implements OnSubscrible<R>{

    private OnSubscrible<T> onSubscrible;//持有上一个的引用
    private Func1<? super T, ? extends R> mTransform;

    public OnSubscribleLift(OnSubscrible<T> onSubscrible, Func1<? super T, ? extends R> mTransform) {
        this.onSubscrible = onSubscrible;
        this.mTransform = mTransform;
    }

    /**
     * 将需要转换成的当做参数传进去
     * @param subscrible
     */
    @Override
    public void call(Subscrible<? super R> subscrible) {
        Subscrible<? super T>  result  = new OperaChange<>(subscrible,mTransform);
        onSubscrible.call(result);

    }

    /**
     * 内部类
     * @param <T>
     * @param <R>
     */
    class OperaChange<T,R> extends Subscrible<T>{

        private Subscrible<? super R> subscrible;
        private Func1<? super T, ? extends R> transform;

        public OperaChange(Subscrible<? super R> subscrible, Func1<? super T, ? extends R> transform) {
            this.subscrible = subscrible;
            this.transform = transform;
        }

        @Override
        public void onNext(T t) {
            //变换
            R r = this.transform.call(t);
            subscrible.onNext(r);


        }
    }
}
