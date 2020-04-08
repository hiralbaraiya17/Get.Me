package com.theneem.getme.ui.EventResult;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theneem.getme.R;




public class EventResult extends Fragment {

    private EventResultViewModel mViewModel;

    public static EventResult newInstance() {
        return new EventResult();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_result_fragment, container, false);
    }

    //this is commit test
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EventResultViewModel.class);
        // TODO: Use the ViewModel
    }

}
