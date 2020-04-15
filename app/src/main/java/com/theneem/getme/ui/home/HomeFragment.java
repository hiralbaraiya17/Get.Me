package com.theneem.getme.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.theneem.getme.R;
import com.theneem.getme.ui.EventDetailsFragment;

public class HomeFragment extends Fragment  implements  View.OnClickListener{

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.txtSearch);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button BtnGetMe = (Button)root.findViewById(R.id.btnGetMe);
        BtnGetMe.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.btnGetMe:

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment fg = new EventDetailsFragment();

                //transaction.replace(Root, fg);
                //transaction.addToBackStack(null);
                //transaction.commit();


                break;

        }
    }



}
