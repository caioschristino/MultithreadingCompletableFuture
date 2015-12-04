package com.example.caiosanchez.multithreadingdependents;

import java.util.concurrent.Callable;
import android.util.Log;

/**
 * Created by Caio Sanchez on 18/11/2015.
 */
public class GrindBeans implements Callable<String> {
    @Override
    public String call() throws Exception {
        Log.e("===>", "Grinding Beans");
        Thread.sleep(2000);

        return "grinded beans";
    }
}