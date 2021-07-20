package com.example.internship_task_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    List<photo> imagesList;
    RecyclerView recyclerView;
    private List imageList = new ArrayList<>();
    public static final String TAG= "MainActivity";
    private static String BASE_URL ="https://api.flickr.com/services/rest/";
    private static Retrofit retrofit;
    public RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        imagesList =new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Images> call =apiInterface.getImages();
        call.enqueue(new Callback<Images>() {
            @Override
            public void onResponse(Call<Images> call, Response<Images> response) {
                Log.d(TAG, "onResponse:"+ response.toString());
                ArrayList<photo> photoArrayList=response.body().getPhotos().getPhoto();
                for (int i=0; i< photoArrayList.size(); i++){
//                    Log.d(TAG ,"On Respone :\n"+
//                            "Title"+ photoArrayList.get(i).getTitle()+ "\n"+
//                            "Image"+ photoArrayList.get(i).getUrl_s()+"\n");
                        String title=photoArrayList.get(i).getTitle();
                        String Image=photoArrayList.get(i).getUrl_s();
                    Log.d(TAG, "onResponse: "+response.toString());
                        imageList.add(photoArrayList.get(i));


                }
        myAdapter= new MyAdapter(MainActivity.this, imageList);
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<Images> call, Throwable t) {
                Log.e(TAG,"Something Went Wrong!");
            }
        });
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState==null){
            navigationView.setCheckedItem(R.id.nav_view);
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
             case  R.id.nav_view:
                    Toast.makeText(this, "You are on Home ", Toast.LENGTH_SHORT).show();
                    break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}