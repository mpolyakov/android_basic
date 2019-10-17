package com.myapplication.myweather_copy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    private TextView temperature;
    private String tempString = "+27";
    static final private int CHOOSE_CITY = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String instanceState;
        if(savedInstanceState == null){
            Log.d(TAG, "Первый запуск!");
            instanceState = "Первый запуск!";
        } else {
            Log.d(TAG, "Повторный запуск!");
            instanceState = "Повторный запуск!";
        }
        Toast.makeText(getApplicationContext(), instanceState, Toast.LENGTH_SHORT).show();

        temperature = findViewById(R.id.textView);
        temperature.setText(tempString);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        Toast.makeText(getApplicationContext(), "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        Toast.makeText(getApplicationContext(), "onSaveInstanceState", Toast.LENGTH_SHORT).show();

        outState.putString("Temperature", tempString);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        Toast.makeText(getApplicationContext(), "onResume", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
        Toast.makeText(getApplicationContext(), "onRestart", Toast.LENGTH_SHORT).show();

//        if (chkbx){
//            ImageView iv=(ImageView) findViewById(R.id.imageView);
//            iv.setVisibility(View.VISIBLE);
//        }

    }

    @Override
    protected  void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        tempString = savedInstanceState.getString("Temperature");
        temperature.setText(tempString);
        Log.d(TAG, "onRestoreInstanceState");
        Toast.makeText(getApplicationContext(), "onRestoreInstanceState", Toast.LENGTH_SHORT).show();

    }

    public void onClickChangeCity(View view) {

        Intent intentForResult = new Intent(MainActivity.this, ChangeCityActivity.class);
        startActivityForResult(intentForResult, CHOOSE_CITY);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        TextView currentCityTextView = findViewById(R.id.currentCity);
        if (requestCode == CHOOSE_CITY){
            if (resultCode == RESULT_OK){
                String currentCityTop = data.getExtras().getString("put_city");
                currentCityTextView.setText(currentCityTop);
            }else {
                currentCityTextView.setText("КВА-КВА");
            }
        }
    }



}