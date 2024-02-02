package org.hse.demoproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private EditText number;
    private List<Integer> numberList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        number = findViewById(R.id.number);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        result = findViewById(R.id.result);

        button1.setOnClickListener(v -> clickButton(1));

        button2.setOnClickListener(v -> clickButton(2));
    }
    private void clickButton(int buttonNumber) {
        String numberVal = number.getText().toString();
        if (numberVal.isEmpty()) {
            numberVal = "0";
        }

        try {
            long count = Long.parseLong(numberVal);

            if (count < 1 || count > 50) {
                Toast.makeText(this, getString(R.string.range_error), Toast.LENGTH_SHORT).show();
                return;
            }

            numberList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                numberList.add(i + 1);
            }

            if (buttonNumber == 1) {
                int sum = 0;
                for (int num : numberList) {
                    sum += num;
                }
                result.setText(String.format(Locale.US, "Result: %d", sum));
            } else if (buttonNumber == 2) {
                long product = 1;
                for (int num : numberList) {
                    if (num % 2 == 0) {
                        product *= num;
                    }
                }
                result.setText(String.format(Locale.US, "Result: %d", product));
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.range_error), Toast.LENGTH_SHORT).show();
        }
    }
}