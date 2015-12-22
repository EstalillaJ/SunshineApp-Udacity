package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onStart() {
        super.onStart();
        Log.v("_________", "In the Start Method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("_________", "In the Pause Method");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("_________", "In the Destroy Method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("_________", "In the Stop Method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("_________", "In the Resume Method");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
        Log.v("_________", "In the Create Method");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        Intent intent;
        switch (id) {
            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_view_location:
                showMap();
                return true;
        }
            return super.onOptionsItemSelected(item);

    }

    public void showMap() {

        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(getApplicationContext());
        String zipCode = preferences.getString(getString(R.string.pref_location_key),"");

        //building the uri for the intent. see Common Intents
        Uri geoLocation = Uri.parse("geo:0,0?")
                .buildUpon()
                .appendQueryParameter("q",zipCode)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        //If the data we gave to the intent makes sense, start the activity
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else {
            Toast toast = new Toast(getApplicationContext());
            toast.setText("Oops, either you don't have a map application, or it cannot" +
                    " resolve your location.");
            toast.show();
        }
    }

}
