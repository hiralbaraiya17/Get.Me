package com.theneem.getme.ui.slideshow;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.theneem.getme.EventActivity;
import com.theneem.getme.R;
import com.theneem.getme.ui.EventDetailsFragment;
import com.theneem.getme.ui.EventDetailsViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.squareup.picasso.Picasso;
import com.theneem.getme.ui.RegistrationFragment;


public class SlideshowFragment extends Fragment {
    private SlideshowViewModel slideshowViewModel;
    public String[] arrayeventList; //  = {"EVENT 1", "EVENT 2", "EVENT 3", "EVENT 4"};
    public String[] arrayeventDescList;
    public String[] arrayeventStartDateList;
    public String[] arrayeventEndDateList;

    private String[] IMAGES; //  = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};
    private FragmentActivity myContext;

    EditText txtSearch;
    ListView listView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        //Next Button
        //final Button btnNext = root.findViewById(R.id.btn_next);

         txtSearch = (EditText) root.findViewById(R.id.txtSearch);
        EventActivity eventActivity = (EventActivity) getActivity();
        txtSearch.setText(eventActivity.getMyData());


        getJSON("http://scienceclub.in/getmeapi/getevents.php");


         listView = root.findViewById(R.id.lv_event_list);
       // CustomAdapter customAdapter = new CustomAdapter();
        //listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create new fragment and transaction
                Fragment newFragment = new EventDetailsFragment();
                // consider using Java coding conventions (upper first char class names!!!)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.nav_host_fragment, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });

        return root;
    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayeventList.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.raw_layout,null);
            ImageView imageView = convertView.findViewById(R.id.raw_img);

            TextView tvEventName = convertView.findViewById(R.id.txtEventName);
            tvEventName.setText(arrayeventList[position]);


            TextView tvEventDesc = convertView.findViewById(R.id.txtEventDesc);
            tvEventDesc.setText(arrayeventDescList[position]);

            TextView tvStartDate = convertView.findViewById(R.id.txtStartDate);
            tvStartDate.setText(arrayeventStartDateList[position]);

            TextView tvEndDate = convertView.findViewById(R.id.txtEndDate);
            tvEndDate.setText(arrayeventEndDateList[position]);


            Picasso.with(getContext() ).load(IMAGES[position]).into(imageView);

            return convertView;
        }
    }
    // get data from php service

    //this method is actually fetching the json string
    private void getJSON(final String urlWebService) {
        /*
         * As fetching the json string is a network operation
         * And we cannot perform a network operation in main thread
         * so we need an AsyncTask
         * The constrains defined here are
         * Void -> We are not passing anything
         * Void -> Nothing at progress update as well
         * String -> After completion it should return a string and it will be the json string
         * */
        class GetJSON extends AsyncTask<Void, Void, String> {

            //this method will be called before execution
            //you can display a progress bar or something
            //so that user can understand that he should wait
            //as network operation may take some time
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            //this method will be called after execution
            //so here we are displaying a toast with the json string
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                //txtSearch.setText(s);
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids) {

                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        //creating a json array from the json string
        JSONArray jsonArray = new JSONArray(json);

        //creating a string array for listview
        String[] heroes = new String[jsonArray.length()];

        arrayeventList = new String[jsonArray.length()];
        arrayeventDescList = new String[jsonArray.length()];
        arrayeventStartDateList = new String[jsonArray.length()];
        arrayeventEndDateList = new String[jsonArray.length()];
        IMAGES = new String[jsonArray.length()];

        //looping through all the elements in json array
        for (int i = 0; i < jsonArray.length(); i++) {

            //getting json object from the json array
            JSONObject obj = jsonArray.getJSONObject(i);

            //getting the name from the json object and putting it inside string array
            heroes[i] = obj.getString("event_name");
            arrayeventList[i] = obj.getString("event_name");
            arrayeventDescList[i] = obj.getString("event_desc");
            arrayeventStartDateList[i] = obj.getString("event_startdate");
            arrayeventEndDateList[i] = obj.getString("event_enddate");
            IMAGES[i] =  obj.getString("events_img");
        }

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        //the array adapter to load data into list
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, heroes);

        //attaching adapter to listview
        //listView.setAdapter(arrayAdapter);
    }
}