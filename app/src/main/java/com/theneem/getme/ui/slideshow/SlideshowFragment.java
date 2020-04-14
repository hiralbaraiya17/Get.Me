package com.theneem.getme.ui.slideshow;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.theneem.getme.R;
import com.theneem.getme.ui.EventDetailsViewModel;

public class SlideshowFragment extends Fragment {
    private SlideshowViewModel slideshowViewModel;
    public String[] arrayeventList = {"EVENT 1","EVENT 2","EVENT 3","EVENT 4"};
    public int[] IMAGES = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Toast.makeText(getContext(),"hello create view",Toast.LENGTH_SHORT).show();

        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        ListView listView = root.findViewById(R.id.lv_event_list);

        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                TextView.setText(s);
//            //    ListView listView = root.findViewById(R.id.lv_event_list);
            }

        });
        return root;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            Toast.makeText(getContext(), arrayeventList.length,Toast.LENGTH_SHORT).show();

            return arrayeventList.length;
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Toast.makeText(getContext(),"hello view",Toast.LENGTH_SHORT).show();

            convertView = getLayoutInflater().inflate(R.layout.raw_layout,null);
            ImageView rawImage = convertView.findViewById(R.id.raw_img);
            TextView rawText = convertView.findViewById(R.id.raw_tv);
            //for (int i=0; i <= arrayeventList.length ; i++)
                rawText.setText(arrayeventList[position]);
                rawImage.setImageResource(IMAGES[position]);
            Toast.makeText(getContext(),arrayeventList.length,Toast.LENGTH_SHORT).show();
            return convertView;

        }
    }
}
