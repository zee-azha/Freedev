    package com.example.freedev;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;

    import com.google.android.material.bottomnavigation.BottomNavigationView;

    public class ListActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
        Button waiting,process;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list);
            loadFragment(new list());
            //Navigation
            BottomNavigationView navigation = findViewById(R.id.nav_views);
            navigation.setOnNavigationItemSelectedListener(this);
            Menu menu = navigation.getMenu();
            MenuItem menuItem = menu.getItem(1);
            menuItem.setChecked(true);
            waiting = findViewById(R.id.waiting);
            process = findViewById(R.id.process);
            waiting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ListActivity.this,WaitingActivity.class);
                    startActivity(intent);
                }
            });
            process.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ListActivity.this,ProcessActivity.class);
                    startActivity(intent);
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
                    Intent acthome = new Intent(ListActivity.this, HomeActivity.class);
                    startActivity(acthome);
                    finish();
                    break;

                case R.id.list:
                    fragment = new list();

                    Intent actlist = new Intent(ListActivity.this, ListActivity.class);
                    startActivity(actlist);
                    finish();
                    break;
                case R.id.add:
                    fragment = new add();
                    Intent actadd = new Intent(ListActivity.this, AddActivity.class);
                    startActivity(actadd);
                    finish();
                    break;
                case R.id.person:
                    fragment = new person();
                    Intent actperson = new Intent(ListActivity.this, Profile.class);
                    startActivity(actperson);
                    finish();
                    break;
            }
            return loadFragment(fragment);
        }
    }
