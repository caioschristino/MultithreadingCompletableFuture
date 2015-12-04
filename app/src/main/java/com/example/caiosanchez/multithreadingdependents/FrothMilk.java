package com.example.caiosanchez.multithreadingdependents;

import java.util.concurrent.Callable;

/**
 * Created by Caio Sanchez on 18/11/2015.
 */
public class FrothMilk implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "some milk";
    }
}