package com.example.caiosanchez.multithreadingdependents;

import java.util.concurrent.Callable;
import android.util.Log;

/**
 * Created by Caio Sanchez on 18/11/2015.
 */
public class HeatWater implements Callable<String> {
    @Override
    public String call() throws Exception {
        Log.e("===>", "Heating Water");
        Thread.sleep(1000);
        return "hot water";
    }
}
