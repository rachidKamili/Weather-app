package me.kamili.rachid.weatherapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import me.kamili.rachid.weatherapp.model.ListItem;
import me.kamili.rachid.weatherapp.model.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "ccb67a7bbec4c82b2b835fd65d0c0ca1";
    private List<ListItem> myDataset = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String zipCode;

    private EditText etZipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        zipCode = sharedPref.getString("zipCode", "30067");

        etZipCode = findViewById(R.id.etZipCode);
        etZipCode.setText(zipCode);
        mRecyclerView = findViewById(R.id.rvList);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        getData();
    }

    private void getData() {


        APIManager.getApiService().getWeatherInfo(
                zipCode,
                API_KEY)
                .enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        Weather weather = response.body();
                        myDataset.clear();
                        myDataset.addAll(weather.getList());
                        mAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {

                    }
                });
    }

    public void onChangeZipCode(View view) {
        zipCode = etZipCode.getText().toString();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("zipCode", zipCode);
        editor.commit();

        //Handle
        getData();
    }


}
