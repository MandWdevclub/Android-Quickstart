package edu.vt.amm28053.androidquickstart;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
     * These are our control buttons.
     */
    private Button start, stop, resume, reset;

    private ChronometerMilli timer;

    private long elapsedTime = 0L;

    /**
     * This is the first callback method called in the Activity lifecycle.
     * Generally, this is where initialization of variables happens.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout of this activity to be the layout defined in activity_main.xml
        setContentView(R.layout.activity_main);

        // findViewById finds a View contained within the Activity's layout with the id passed in
        // View ids are defined in the layout file.
        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);
        resume = (Button)findViewById(R.id.resume);
        reset = (Button)findViewById(R.id.reset);

        timer = (ChronometerMilli)findViewById(R.id.chronometer);
        resetTimer();

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        resume.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                startTimer();
                hideButtons(start);
                showButtons(stop);
                break;
            case R.id.stop:
                pauseTimer();
                hideButtons(stop);
                showButtons(resume, reset);
                break;
            case R.id.resume:
                startTimer();
                hideButtons(resume, reset);
                showButtons(stop);
                break;
            case R.id.reset:
                resetTimer();
                hideButtons(resume, reset);
                showButtons(start);
                break;
        }
    }

    private void showButtons(Button... buttons) {
        for (Button b : buttons) {
            b.setVisibility(View.VISIBLE);
        }
    }

    private void hideButtons(Button... buttons) {
        for (Button b : buttons) {
            b.setVisibility(View.GONE);
        }
    }

    private void startTimer() {
        timer.setBase(SystemClock.elapsedRealtime() - elapsedTime);
        timer.start();
    }

    private void pauseTimer() {
        elapsedTime = (SystemClock.elapsedRealtime() - timer.getBase());
        timer.stop();
    }

    private void resetTimer() {
        elapsedTime = 0;
        timer.setText("00:00.00");
    }
}
