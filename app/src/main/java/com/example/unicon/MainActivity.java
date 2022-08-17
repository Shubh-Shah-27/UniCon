package com.example.unicon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Key Value
    final String keyDollarToINR = "MainActivity.dollarToINR";
    float dollarToINR = -5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization
        EditSharedPreference edit = new EditSharedPreference(this, keyDollarToINR);
        Button button = findViewById(R.id.submit);
        TextView output = findViewById(R.id.output);
        EditText input = findViewById(R.id.input);
        Log.e("MainActivity","Initialization Done");

        // API Call
        String url = "https://openexchangerates.org/api/latest.json?app_id=ecb799ad0a1e4d158ab8b9d6094062c2&symbols=INR&prettyprint=true&show_alternative=true";
        APICall call = new APICall(this,edit);
        call.callWithURL(url);
        Log.e("MainActivity","API Call Done");

        // Submit -> Button OnClick Listener
        button.setOnClickListener(view -> {
            double dollars, rupees;
            // Access Value from Shared Preference
            dollarToINR = edit.getStoredDollarValue();

            // Parsing Input
            String in = input.getText().toString();
            // If Input is empty -> Default Value = 1
            if(in.equals("")) {
                dollars = 1;
                input.setText("1");
            }
            else
                dollars = Double.parseDouble(in);

            // Calculating
            rupees = dollarToINR*dollars;
            rupees = Math.round(rupees * 1000) / 1000.0;

            // Output
            output.setText("The given amount in INR\n" + "= " + rupees + " rupees");
        });

        // Pressing ENTER Key in EditText input -> Button Click
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    Log.i("MainActivity","Enter pressed");
                    button.performClick();
                }
                return false;
            }
        });
    }
}