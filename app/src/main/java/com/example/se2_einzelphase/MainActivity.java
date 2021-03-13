package com.example.se2_einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnSend;
    private Button btnSort;
    private TextView outputDisplay;
    private EditText inputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.buttonSend);
        btnSort = findViewById(R.id.buttonSort);
        outputDisplay = findViewById(R.id.outputDisplay);
        inputField = findViewById(R.id.inputField);

        btnSend.setOnClickListener(view -> {
            String input = inputField.getText().toString();

            new WorkerThread(this, input, outputDisplay).start();
        });

        btnSort.setOnClickListener(view -> {
            try {
                String input = inputField.getText().toString();
                ArrayList<Integer> evens = new ArrayList<>();
                ArrayList<Integer> odds = new ArrayList<>();
                int number = Integer.parseInt(input);
                String result = "";

                for (int i = 0; i < input.length(); i++) {
                    int digit = number % 10;
                    boolean inserted = false;

                    if (digit % 2 == 0) {
                        if (evens.size() < 1) {
                            evens.add(digit);
                        } else {
                            for (int j = 0; j < evens.size(); j++) {
                                if (digit <= evens.get(j)) {
                                    evens.add(j, digit);
                                    inserted = true;

                                    break;
                                }
                            }

                            if (!inserted) {
                                evens.add(digit);
                            }
                        }
                    } else {
                        if (odds.size() < 1) {
                            odds.add(digit);
                        } else {
                            for (int j = 0; j < odds.size(); j++) {
                                if (digit <= odds.get(j)) {
                                    odds.add(j, digit);
                                    inserted = true;

                                    break;
                                }
                            }

                            if (!inserted) {
                                odds.add(digit);
                            }
                        }
                    }

                    number = (int) (number / 10);
                }

                for (int x : evens) {
                    result += x;
                }

                for (int x : odds) {
                    result += x;
                }

                outputDisplay.setText(result);
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}