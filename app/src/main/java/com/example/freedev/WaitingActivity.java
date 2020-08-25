package com.example.freedev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WaitingActivity extends AppCompatActivity implements WaitingAdapter.OnItemClickListener {
    private ImageView Back;
    private DatabaseReference mDatabaseRef;
    private Query firebaseSearch;
    private ValueEventListener mDBListener;
    private WaitingAdapter mAdapter;
    private List<Request> mstatus;


    public String personEmail;
    private RecyclerView mRecycleView;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        Back = (ImageView) findViewById(R.id.back);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(WaitingActivity.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


        }

        mRecycleView =findViewById(R.id.rv_requests);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mstatus = new ArrayList<>();
        mAdapter = new WaitingAdapter(WaitingActivity.this,mstatus);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnitemClickListener(WaitingActivity.this);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("project");
        firebaseSearch = mDatabaseRef.orderByChild("email").startAt(personEmail).endAt(personEmail+"\uf8ff");
       mDBListener= firebaseSearch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mstatus.clear();
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Request request = postSnapshot.getValue(Request.class);
                    request.setKey(postSnapshot.getKey());
                    mstatus.add(request);
                }
                mAdapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(WaitingActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }

    @Override
    public void onItemClick(int postion) {


    }

    @Override
    public void onDeleteClick(int postion) {
       Request selected = mstatus.get(postion);
       String selectedKey = selected.getKey();
       mDatabaseRef.child(selectedKey).removeValue();
       Toast.makeText(WaitingActivity.this,"Project Canceled",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        firebaseSearch.removeEventListener(mDBListener);
    }
}
