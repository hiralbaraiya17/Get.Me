package com.theneem.getme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.theneem.getme.ui.EventDetailsFragment;
import com.theneem.getme.ui.RegistrationFragment;
import com.theneem.getme.ui.home.HomeFragment;
import com.theneem.getme.ui.slideshow.SlideshowFragment;

public class EventActivity extends AppCompatActivity  {
    public ListView listView;
    private  String searchString;
    private SlideshowFragment newSlideShowFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.eventContainer,new SlideshowFragment(),"TAG_EVENT");
        fragmentTransaction.commit();

    //    listView = findViewById(R.id.lv_event_list);
      /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.eventContainer,new EventDetailsFragment());
                fragmentTransaction.commit();
            }
        });
*/

        // Get the transferred data from source activity.
        Intent intent = getIntent();
        searchString= intent.getStringExtra("SEARCH_MESSAGE");

    }

    public String getMyData() {
        return searchString;
    }

    public void btnSearch_click(View view) {

    }

}
