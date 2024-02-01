package org.hse.demoproject;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DemoActivity extends AppCompatActivity {

    private TextView result;
    private EditText number;

    private List<Integer> GetList(){
        String numberVal = number.getText().toString();
        if (numberVal.isEmpty()){
            numberVal = "0";
        }
        int count = Integer.parseInt(numberVal);

        if (count < 1 || count > 50) {

            Toast.makeText(this, "Введите число в диапазоне от 1 до 50", Toast.LENGTH_SHORT).show();
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++){
            list.add(i+1);
        }
        return list;
    }

    private void clickButton1(){

        if (!GetList().isEmpty()){
            int sum = GetList().stream().mapToInt(Integer::intValue).sum();
            result.setText(String.format(Locale.US,"Result: %d", sum));
        }
        else{
            result.setText(String.format(Locale.US,"Result: %d", 0));
        }
    }

    private void clickButton2(){
        if (!GetList().isEmpty()){
            long product = GetList().stream().filter(num -> num % 2 == 0).mapToLong(Integer::intValue).reduce(1, (a, b) -> a * b);
            result.setText(String.format(Locale.US,"Result: %d", product));
        }
        else{
            result.setText(String.format(Locale.US,"Result: %d", 0));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        number = findViewById(R.id.number);

        View button1 = findViewById(R.id.button1);
        View button2 = findViewById(R.id.button2);
        result = findViewById(R.id.result);

        button1.setOnClickListener(v -> clickButton1());

        button2.setOnClickListener(v -> clickButton2());
    }
}
