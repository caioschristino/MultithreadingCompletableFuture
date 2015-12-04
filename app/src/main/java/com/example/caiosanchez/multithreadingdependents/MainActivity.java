package com.example.caiosanchez.multithreadingdependents;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import android.util.Log;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ExecutorService executor = Executors.newFixedThreadPool(2);

        FutureTask<String> heatWaterFuture = new FutureTask<String>(new HeatWater());
        FutureTask<String> grindBeans = new FutureTask<String>(new GrindBeans());
        FutureTask<String> brewCoffee = new FutureTask<String>(new Brew(grindBeans, heatWaterFuture));
        FutureTask<String> frothMilk = new FutureTask<String>(new FrothMilk());
        FutureTask<String> combineCoffee = new FutureTask<String>(new Combine(frothMilk, brewCoffee));

        executor.execute(heatWaterFuture);
        executor.execute(grindBeans);
        executor.execute(brewCoffee);
        executor.execute(frothMilk);
        executor.execute(combineCoffee);

        try {
            Log.e("===>", combineCoffee.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
