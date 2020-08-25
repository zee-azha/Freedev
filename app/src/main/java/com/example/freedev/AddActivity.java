package com.example.freedev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
   EditText Name,Email,Subject,Desc;
    Button Submit;
    public String status;
    GoogleSignInClient mGoogleSignInClient;
    private DatabaseReference mDatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activiry);
        Name=findViewById(R.id.names);
        Email=findViewById(R.id.emails);
        Subject=findViewById(R.id.subjects);
        Desc=findViewById(R.id.descs);

        status ="waiting";
        loadFragment(new person());
        //Navigation
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(AddActivity.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            Name.setText(personName);
            Email.setText(personEmail);
        }
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("project");
        Submit=findViewById(R.id.submits);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();

        };


        });
    };
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
                Intent acthome = new Intent(AddActivity.this, HomeActivity.class);
                startActivity(acthome);
                finish();
                break;

            case R.id.list:
                fragment = new list();

                Intent actlist = new Intent(AddActivity.this, ListActivity.class);
                startActivity(actlist);
                finish();
                break;
            case R.id.add:
                fragment = new add();
                Intent actadd = new Intent(AddActivity.this, AddActivity.class);
                startActivity(actadd);
                finish();
                break;
            case R.id.person:
                fragment = new person();
                Intent actperson = new Intent(AddActivity.this, Profile.class);
                startActivity(actperson);
                finish();
                break;
        }
        return loadFragment(fragment);
    }
    public void request(){
        String name = Name.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String subject = Subject.getText().toString().trim();
        String description = Desc.getText().toString().trim();
        if (name.equals("") || description.equals("")||email.equals("") || subject.equals(""))
        {
            Toast.makeText(AddActivity.this, "Please insert All field",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            String key  = mDatabaseRef.push().getKey();
            Request request = new Request(name, email,subject,description,status);
            mDatabaseRef.child(key).setValue(request);
            Toast.makeText(this, "project has been send! we will contact your email if we approve the project", Toast.LENGTH_LONG).show();

        };
    }
}
