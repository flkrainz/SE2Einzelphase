package com.example.se2_einzelphase;

import android.content.Context;
import android.widget.TextView;

public class WorkerThread extends Thread{

    private Context parent;
    private String input;
    private TextView outputDisplay;

    public WorkerThread(Context parent, String input, TextView outputDisplay){
        this.parent = parent;
        this.input = input;
        this.outputDisplay = outputDisplay;
    }

    @Override
    public void run() {

    }
}
