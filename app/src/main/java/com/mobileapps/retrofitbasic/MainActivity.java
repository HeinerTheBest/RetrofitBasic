package com.mobileapps.retrofitbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Creating Retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Api.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        //Create API object
        Api api = retrofit.create(Api.class);

        //Create Call object
        Call<List<Hero>> call = api.getHeroes();


        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroes = response.body();
                if (heroes != null) {
                    for (Hero hero : heroes)
                    {
                        Log.d("Heiner","The name of the hero is: "+hero.getName());
                        Log.d("Heiner","The Real name of the hero is: "+hero.getRealname());
                        Log.d("Heiner","Created by: "+hero.getCreatedby());
                        Log.d("Heiner"," ");

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

            }
        });




    }
}
