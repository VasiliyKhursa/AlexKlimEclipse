package com.example.vasiliy.aninterface;

import android.util.Log;

/**
 * Created by Vasiliy on 05.01.2017.
 */

public class SubClass {
    interface MyCallback{
        void callBackReturn();
    }

    MyCallback myCallback;

    void registerCallBack(MyCallback callback){
        this.myCallback = callback;
    }

    void doSomething(){
        // Здесь какой-то длительный код
        // Например, тянем кота за хвост
        Log.i("myTag", "SubClass - doSomething");
        // вызываем метод обратного вызова
        myCallback.callBackReturn();
    }
}
