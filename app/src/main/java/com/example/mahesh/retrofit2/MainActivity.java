package com.example.mahesh.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView responseText;
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    private RecycleAdapter adapter;
    private static final String TAG = "MainActivity";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getImages();

        responseText = (TextView) findViewById(R.id.responsetest);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ListUser> call=apiInterface.getServiceResponse();
        call.enqueue(new Callback<ListUser>() {
            @Override
            public void onResponse(Call<ListUser> call, final Response<ListUser> response) {

                if (response.isSuccessful()){
                   // responseText.setText(response.body().data.get(0).page);
                    adapter = new RecycleAdapter(response.body().data);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(new RecycleAdapter.ClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            Toast.makeText(MainActivity.this, ""+response.body().data.get(position).firstName, Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onItemLongClick(int position, View v) {
                            Toast.makeText(MainActivity.this, ""+response.body().data.get(position).firstName, Toast.LENGTH_SHORT).show();

                        }
                    });
            }else{
                    Toast.makeText(MainActivity.this, "response error", Toast.LENGTH_SHORT).show();
                }


        }
        @Override
            public void onFailure(Call<ListUser> call, Throwable t) {
                   Toast.makeText(MainActivity.this,"Service not found",Toast.LENGTH_LONG).show();
            }
        });


    }

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Washington");

        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
    }
}
