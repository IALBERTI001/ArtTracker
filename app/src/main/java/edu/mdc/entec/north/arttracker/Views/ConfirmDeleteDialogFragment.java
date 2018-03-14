package edu.mdc.entec.north.arttracker.Views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;


public class ConfirmDeleteDialogFragment extends DialogFragment {
    private static final String ART_PIECE_POSITION = "artPiecePosition";
    private int position;
    private OnDeleteConfirmedListener mListener;

    public ConfirmDeleteDialogFragment() {
        // Required empty public constructor
    }

    public static ConfirmDeleteDialogFragment newInstance(int position) {
        ConfirmDeleteDialogFragment fragment = new ConfirmDeleteDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ART_PIECE_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ART_PIECE_POSITION);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                // set dialog icon
                .setIcon(android.R.drawable.btn_dialog)
                // set Dialog Title
                .setTitle("Important!")
                // Set Dialog Message
                .setMessage("Do you really want to remove this art piece?")

                // positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (mListener != null) {
                            mListener.onDeleteConfirmed(position);
                        }
                        dialog.dismiss();
                    }
                })
                // negative button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment artPieceListFragment = this.getParentFragment()
                .getChildFragmentManager()
                .findFragmentByTag("listFragment");
        if (artPieceListFragment instanceof OnDeleteConfirmedListener) {
            mListener = (OnDeleteConfirmedListener) artPieceListFragment;
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


    public interface OnDeleteConfirmedListener {
        void onDeleteConfirmed(int position);
    }
}