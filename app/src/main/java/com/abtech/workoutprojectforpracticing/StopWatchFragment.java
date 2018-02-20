package com.abtech.workoutprojectforpracticing;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopWatchFragment extends Fragment implements View.OnClickListener{


    int millisec = 0;
    boolean running;
    private boolean wasRunning;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;

        if(savedInstanceState != null){
            millisec = savedInstanceState.getInt("milliseconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            if(wasRunning){
                running = true;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout =  inflater.inflate(R.layout.fragment_stop_watch, container, false);
        runTimer(layout);

        Button startButton = (Button) layout.findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
        Button stoputton = (Button) layout.findViewById(R.id.stopButton);
        stoputton.setOnClickListener(this);
        Button resetButton = (Button) layout.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);

        return layout;
    }
    @Override
    public  void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("milliseconds",millisec);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }


    public void runTimer(View view){
        final TextView timerText = (TextView) view.findViewById(R.id.timerText);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = millisec/360000;
                int minutes = (millisec%360000)/6000;
                int secs= (millisec%6000)/100;
                int milsec = millisec%100;
                String timer = String.format("%d:%02d:%02d:%02d",hours,minutes,secs,milsec);

                timerText.setText(timer);
                if(running){
                    millisec++;

                }

                handler.postDelayed(this, 1);
            }
        });

    }

    @Override
    public void onStop(){
        super.onStop();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onStart(){
        super.onStart();
        if(wasRunning){
            running = true;
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.startButton:
                start(view);
                break;
            case R.id.stopButton:
                stop(view);
                break;
            case R.id.resetButton:
                reset(view);
                break;

        }
    }

    public void start(View view){
        running = true;
    }
    public  void stop(View view){
        running = false;
    }
    public void reset(View view){
        running = false;
        millisec = 0;
    }

}
