package com.theneem.getme.ui;

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

public class EventDetailsFragment extends Fragment {

    private EventDetailsViewModel eventDetailsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventDetailsViewModel =
                ViewModelProviders.of(this).get(EventDetailsViewModel.class);
        View root = inflater.inflate(R.layout.event_result_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        eventDetailsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}


//public class EventDetailsFragment extends Fragment {
//
//    private EventDetailsViewModel mViewModel;
//
//    public static EventDetailsFragment newInstance() {
//        return new EventDetailsFragment();
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.event_details_frament, false, container);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(EventDetailsViewModel.class);
//        // TODO: Use the ViewModel
//    }
//
//}

