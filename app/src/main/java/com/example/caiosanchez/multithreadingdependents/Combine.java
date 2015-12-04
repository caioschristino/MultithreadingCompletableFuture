package com.example.caiosanchez.multithreadingdependents;

/**
 * Created by Caio Sanchez on 18/11/2015.
 */

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import android.util.Log;

public class Combine implements Callable<String> {

    public Combine(Future<String> frothedMilk, Future<String> brewedCoffee) {
        super();
        this.frothedMilk = frothedMilk;
        this.brewedCoffee = brewedCoffee;
    }

    final Future<String> frothedMilk;
    final Future<String> brewedCoffee;

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        Log.e("===>", "Combining " + frothedMilk.get() + " " + brewedCoffee.get());
        return "Final Coffee";
    }
}