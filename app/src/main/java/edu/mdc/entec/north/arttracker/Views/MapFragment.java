package edu.mdc.entec.north.arttracker.Views;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.mdc.entec.north.arttracker.R;
import edu.mdc.entec.north.arttracker.Views.ListDialogFragment;


public class MapFragment extends Fragment {

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();
        DialogFragment newFragment = new ListDialogFragment();
        newFragment.show(getChildFragmentManager(), "list");

    }

}
