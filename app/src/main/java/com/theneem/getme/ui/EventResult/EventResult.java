package com.theneem.getme.ui.EventResult;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theneem.getme.R;
import com.theneem.getme.ui.gallery.GalleryViewModel;


public class EventResult extends Fragment {

    private EventResultViewModel eventResultViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventResultViewModel =
                ViewModelProviders.of(this).get(EventResultViewModel.class);
        View root = inflater.inflate(R.layout.event_result_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        eventResultViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}
