package com.mmomeni.distance_converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity_MM";
    private EditText userInput;
    private TextView result;
    private TextView historyBox;
    private TextView firstBox1;
    private TextView firstBox2;
    private String valIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput = findViewById(R.id.input1);
        result = findViewById(R.id.text4);
        firstBox1 = findViewById(R.id.text2);
        firstBox2 = findViewById(R.id.text3);
        historyBox = findViewById(R.id.text6);
        historyBox.setMovementMethod(new ScrollingMovementMethod());
    }
    public void doPress1 (View v){
        double number = 0;
        valIn = userInput.getText().toString();
        Log.d(TAG, "doPress: " + valIn);
        String allHistroy = historyBox.getText().toString();

        if (!valIn.trim().isEmpty()){
            number = Double.parseDouble(valIn);
            if (firstBox1.getText().toString().equals("Miles value")){
                number = number * 1.60934;
                userInput.setText("");
                historyBox.setText(String.format("Mi to Km: %s ➔ %.1f\n%s", valIn, number, allHistroy));

            }
            else {
                number = number * 0.621371;
                userInput.setText("");
                historyBox.setText(String.format("Km to Mi: %s ➔ %.1f\n%s", valIn, number, allHistroy));
            }
        }
        result.setText(String.format("%.1f", number));
    }
    public void doPress2 (View v){
        historyBox.setText("");

    }

    public void radioClicked (View v){
        String message = "";
        switch (v.getId()){
            case R.id.radioButton1:
                firstBox1.setText("Miles value");
                firstBox2.setText("Kilometers value");
                message = "Miles to Kilometers";
                break;
            case R.id.radioButton2:
                firstBox1.setText("Kilometers value");
                firstBox2.setText("Miles value");
                message = "Kilometers to Miles";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("HISTORY", historyBox.getText().toString());

        // Call super last
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        // Call super first
        super.onRestoreInstanceState(savedInstanceState);

        historyBox.setText(savedInstanceState.getString("HISTORY"));
    }
}