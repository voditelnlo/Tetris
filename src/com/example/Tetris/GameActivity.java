package com.example.Tetris;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    DrawGame tet;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        tet = new DrawGame(this);
        setContentView(tet);

        Timer timer = new Timer();

        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                GameActivity.this.runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        tet.GameTick();
                    }
                });
            }
        }, 0, 10);

    }
}