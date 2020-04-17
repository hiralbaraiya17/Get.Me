package com.theneem.getme;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.theneem.getme.ui.EventDetailsFragment;
import com.theneem.getme.ui.home.HomeFragment;
import com.theneem.getme.ui.slideshow.SlideshowFragment;

public class EventActivity extends AppCompatActivity {


    private  String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.eventContainer,new SlideshowFragment());
        fragmentTransaction.commit();




        // Get the transferred data from source activity.
        Intent intent = getIntent();
        searchString= intent.getStringExtra("SEARCH_MESSAGE");


        //EditText textView = (EditText )  findViewById(R.id.txtSearch);
       // textView.setText (message.toString());




        // Get a support ActionBar corresponding to this toolbar
        //ActionBar ab = getSupportActionBar();

        // Enable the Up button
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void btnSearch_click(View view) {
    }

    public String getMyData() {
        return searchString;
    }


}
