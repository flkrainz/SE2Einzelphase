package com.example.se2_einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView outputDisplay;
    private EditText inputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        outputDisplay = findViewById(R.id.outputDisplay);
        inputField = findViewById(R.id.inputField);

        btn.setOnClickListener(view -> {
            String input = inputField.getText().toString();

            new WorkerThread(this, input, outputDisplay).start();
        });
    }


}