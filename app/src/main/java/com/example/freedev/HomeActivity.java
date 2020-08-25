package com.example.freedev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private RecyclerView mRecycleView;
    private ImageAdapter mAdapter;
    private ProgressBar mProgressCircle;
    private DatabaseReference mDatabaseRef;
    private List<Porto> mPorto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Navigation
        loadFragment(new home());
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        mRecycleView =findViewById(R.id.rv_requests);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mPorto = new ArrayList<>();
        mProgressCircle =findViewById(R.id.progres_circle);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("porto");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Porto porto = postSnapshot.getValue(Porto.class);
                    mPorto.add(porto);
                }
                mAdapter =new ImageAdapter(HomeActivity.this,mPorto);
                mRecycleView.setAdapter(mAdapter);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HomeActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);

            }
        });
    }
    //fragment function
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.home:
                fragment = new home();
                Intent acthome = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(acthome);

                finish();
                break;
            case R.id.list:
                fragment = new list();

                Intent actlist = new Intent(HomeActivity.this, ListActivity.class);
                startActivity(actlist);
                finish();
                break;
            case R.id.add:
                fragment = new add();
                Intent actadd = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(actadd);
                finish();
                break;

           case R.id.person:
                fragment = new person();
                Intent actperson = new Intent(HomeActivity.this, Profile.class);
                startActivity(actperson);
                finish();
                break;
        }
        return loadFragment(fragment);
    }

}
