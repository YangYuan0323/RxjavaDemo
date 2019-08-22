package com.rxjavademo.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View view) {
        Observable.create(new OnSubscrible<String>() {

            @Override
            public void call(Subscrible<? super String> subscrible) {
                subscrible.onNext("发送数据");
            }
        }).subscrible(new Subscrible<String>() {
            @Override
            public void onNext(String s) {
                Log.i(TAG, "收到：" + s);
            }
        });
    }

    public void change(View view){
        Observable.create(new OnSubscrible<String>() {

            @Override
            public void call(Subscrible<? super String> subscrible) {
                subscrible.onNext("===开始转换");
            }
        }).map(new Func1<String, Bitmap>() {
            @Override
            public Bitmap call(String s) {
                Log.i(TAG, "===收到：" + s);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                Log.i(TAG, "===转换图片：" + bitmap);
                return bitmap;
            }
        }).subscrible(new Subscrible<Bitmap>() {
            @Override
            public void onNext(Bitmap bitmap) {
                Log.i(TAG, "===转换结束：" + bitmap);
            }
        });
    }


    public void onChange(View view){
        Observable.create(new OnSubscrible<String>() {

            @Override
            public void call(Subscrible<? super String> subscrible) {
                Log.i(TAG, "===当前线程：" + Thread.currentThread().getName());

                subscrible.onNext("===开始转换");
            }
        }).map(new Func1<String, Bitmap>() {
            @Override
            public Bitmap call(String s) {
                Log.i(TAG, "===收到：" + s);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                Log.i(TAG, "===转换图片：" + bitmap);
                return bitmap;
            }
        }).subscribleOnIO().subscrible(new Subscrible<Bitmap>() {
            @Override
            public void onNext(Bitmap bitmap) {
                Log.i(TAG, "===转换结束：" + bitmap);
            }
        });
    }


}
