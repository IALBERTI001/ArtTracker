package edu.mdc.entec.north.arttracker.Views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import edu.mdc.entec.north.arttracker.R;


public class GetNameDialogFragment extends DialogFragment {
    private OnGetNameListener mListener;

    public GetNameDialogFragment() {
        // Required empty public constructor
    }

    public static GetNameDialogFragment newInstance() {
        GetNameDialogFragment fragment = new GetNameDialogFragment();
        return fragment;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.get_name_dialog, null);

        final EditText editText = (EditText) view.findViewById(R.id.name);
                // set dialog icon
                builder.setIcon(android.R.drawable.btn_dialog)
                // set Dialog Title
                .setTitle("Hello, what's your name?")
                // Set Dialog Message
                .setView(view)
                // positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (mListener != null) {
                            mListener.onGetName(editText.getText().toString());
                        }
                        dialog.dismiss();
                    }
                })
                // negative button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment artPieceListFragment = this.getParentFragment()
                .getChildFragmentManager()
                .findFragmentByTag("listFragment");
        if (artPieceListFragment instanceof OnGetNameListener) {
            mListener = (OnGetNameListener) artPieceListFragment;
        } else {
            throw new RuntimeException(artPieceListFragment.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnGetNameListener {
        void onGetName(String name);
    }
}