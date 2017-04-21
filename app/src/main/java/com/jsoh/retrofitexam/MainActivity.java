package com.jsoh.retrofitexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);

        Call<List<Weather>> call = service.getWeatherList();

        call.enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                Toast.makeText(MainActivity.this, "성공", Toast.LENGTH_SHORT).show();
                List<Weather> weatherList = response.body();
                Log.d(TAG, "onResponse: " + weatherList);

                WeatherAdapter adapter = new WeatherAdapter(weatherList);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        mListView = (ListView) findViewById(R.id.list_view);

    }

    private static class WeatherAdapter extends BaseAdapter {

        private List<Weather> mWeatherList;

        public WeatherAdapter(List<Weather> weatherList) {
            mWeatherList = weatherList;
        }

        @Override
        public int getCount() {
            return mWeatherList.size();
        }

        @Override
        public Object getItem(int position) {
            return mWeatherList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
            }

            TextView countryTextView = (TextView) convertView.findViewById(R.id.country);
            TextView weatherTextView = (TextView) convertView.findViewById(R.id.weather);
            TextView temperatureTextView = (TextView) convertView.findViewById(R.id.temperature);

            Weather weather = mWeatherList.get(position);

            countryTextView.setText(weather.getCountry());
            weatherTextView.setText(weather.getWeather());
            temperatureTextView.setText(weather.getTemperature());

            return convertView;
        }
    }
}
