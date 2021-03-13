package com.example.se2_einzelphase;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

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
        Socket socket = null;
        String result;

        try{
            socket = new Socket("se2-isys.aau.at", 53212);

            try(DataOutputStream toServer = new DataOutputStream(socket.getOutputStream())) {
                try (BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    byte[] data = (input + '\n').getBytes();

                    toServer.write(data);

                    result = fromServer.readLine();

                    outputDisplay.setText(result);
                }
            }
        }catch(Exception e){
            Toast.makeText(parent, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }finally{
            if (socket != null && !socket.isClosed()) {
                try { socket.close(); } catch (Exception e) {}
            }
        }
    }
}
