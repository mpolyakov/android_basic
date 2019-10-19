package com.myapplication.myweather_copy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    private TextView temperature;
    private String tempString = "+27";
    static final private int CHOOSE_CITY = 0;
    private ArrayList<String> mDays = new ArrayList<>();
    private ArrayList<String> mForecTemperature = new ArrayList<>();
    private ArrayList<Integer> mImagesId = new ArrayList<Integer>();


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

        initFillingRecycleArrays();

        ImageView imageViewBrow = findViewById(R.id.imageViewInternet);
        imageViewBrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://gismeteo.ru";
                Uri uri = Uri.parse(url);

                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);

            }
        });


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

    private void initFillingRecycleArrays(){
        mDays.add("ПН");
        mDays.add("ВТ");
        mDays.add("СР");
        mDays.add("ЧТ");
        mDays.add("ПТ");
        mDays.add("СБ");
        mDays.add("ВС");

        mForecTemperature.add("+18");
        mForecTemperature.add("+19");
        mForecTemperature.add("+20");
        mForecTemperature.add("+21");
        mForecTemperature.add("+22");
        mForecTemperature.add("+23");
        mForecTemperature.add("+24");

        mImagesId.add(R.drawable.sun240);
        mImagesId.add(R.drawable.snow192);
        mImagesId.add(R.drawable.sun240);
        mImagesId.add(R.drawable.storm100_f);
        mImagesId.add(R.drawable.rain100_f);
        mImagesId.add(R.drawable.cloudly200_f);
        mImagesId.add(R.drawable.sun240);

        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mDays, mForecTemperature, mImagesId, this);
        recyclerView.setAdapter(adapter);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        }
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }

    }


}





















