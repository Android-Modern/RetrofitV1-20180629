package com.example.doctorsfab.retrofitv1;

import android.graphics.ColorSpace;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    data1 data1;

    String tag = "Tag:";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textview1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        GithubService service = retrofit.create(GithubService.class);

        Call<data1> request = service.getUserRepositories("dev-juyoung");

        request.enqueue(new Callback<data1>() {

            @Override
            public void onResponse(Call<data1> call, Response<data1> response) {

                Log.d(tag, String.valueOf(response.body()));
                Log.d(tag,"리스폰스1!!!!!!!!!!@!@!@!@!@!");
//                Log.d(tag+"!@#!@#!@#!@#!@#::::",response.body().name);
//                textView.setText(response.body().name);
            }

            @Override
            public void onFailure(Call<data1> call, Throwable t) {
                Log.d(tag,"에러!@#!@#!@#");
            Log.d(tag,t.getLocalizedMessage());

            }
        });
    }


    public interface GithubService {
        @GET("users/{user}/repos")
        Call<data1> getUserRepositories(@Path("user") String userName);
    }



}
