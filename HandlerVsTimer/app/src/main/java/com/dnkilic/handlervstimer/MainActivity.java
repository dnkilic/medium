package com.dnkilic.handlervstimer;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Button mStartButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mStartButton.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    final Handler updateHandler = new Handler();

                                    Runnable runnable = new Runnable() {

                                        public void run() {
                                            mTextMessage.setText(R.string.title_android);
                                            updateHandler.postDelayed(this, 1); // determines the execution interval
                                        }

                                    };

                                    updateHandler.post(runnable);

                                }
                            }
                    );
                    return true;
                case R.id.navigation_notifications:
                    mStartButton.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    new Timer().schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                           runOnUiThread(new Runnable() {
                                               @Override
                                               public void run() {
                                                   mTextMessage.setText(R.string.title_android);
                                               }
                                           });
                                        }
                                    }, 0, 1);
                                }
                            }
                    );
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        mStartButton = (Button) findViewById(R.id.start);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

}

/*
Handler runs on UI Thread
Timer runs on its own Thread
*/
