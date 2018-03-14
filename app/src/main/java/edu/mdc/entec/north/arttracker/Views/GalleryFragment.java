package edu.mdc.entec.north.arttracker.Views;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import edu.mdc.entec.north.arttracker.db.ArtPiece;
import edu.mdc.entec.north.arttracker.R;
import edu.mdc.entec.north.arttracker.db.AppDatabase;


public class GalleryFragment extends Fragment
        implements ArtPieceListFragment.OnArtPieceSelectedListener
        , GetNameDialogFragment.OnGetNameListener{
    private static final String TAG = "GalleryFragment";
    private boolean isLandscape;
    private AppDatabase mDb;
    private List<ArtPiece> artPieces;
    private boolean showList;
    private int position;
    private SharedPreferences sharedPref;
    private static final String FILENAME = "secret";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        showList = true;
        super.onCreate(savedInstanceState);//other nested frags are attached and created
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);


        //Create and populate data list
        mDb = AppDatabase.getInMemoryDatabase(getActivity().getApplicationContext());
        populateDb();

        if(view.findViewById(R.id.container2) != null){
            isLandscape = true;
            position = 0;
        } else {
            isLandscape = false;
        }

        if(savedInstanceState != null) {
            position = savedInstanceState.getInt("artPiece");
            showList = savedInstanceState.getBoolean("showList");
        }

        if(isLandscape) {
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ArtPieceListFragment listFragment = ArtPieceListFragment.newInstance(artPieces);
            ft.replace(R.id.container, listFragment, "listFragment");
            ft.addToBackStack("listFragment");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            ArtPieceFragment artPieceFragment = ArtPieceFragment.newInstance(artPieces.get(position));
            ft.replace(R.id.container2, artPieceFragment, "artPieceFragment");
            ft.addToBackStack("artPieceFragment");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        } else {
            if (showList) {

                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                ArtPieceListFragment listFragment = ArtPieceListFragment.newInstance(artPieces);
                transaction.replace(R.id.container, listFragment, "listFragment");
                transaction.addToBackStack("listFragment");
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();
            } else {

                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                ArtPieceFragment artPieceFragment = ArtPieceFragment.newInstance(artPieces.get(position));
                transaction.replace(R.id.container, artPieceFragment, "artPieceFragment");
                transaction.addToBackStack("artPieceFragment");
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();
            }
        }

        sharedPref = getContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        //Reading the username from the shared preferences file
        String  savedUserName = sharedPref.getString("userName", null);
        if(savedUserName == null){
            DialogFragment customFragment = GetNameDialogFragment.newInstance();
            customFragment.show(getChildFragmentManager(), "custom");
        } else {
            sayHello(savedUserName);
        }







        return view;

    }

    private void populateDb() {

        mDb.artPieceModel().deleteAll();
        //Dynamite Baby
        ArtPiece ap1 = new ArtPiece("Dynamite Baby", "Alfredo Halegua", 1983, R.drawable.dynamite_baby);
        mDb.artPieceModel().insertArtPiece(ap1);
        //Pheles
        ArtPiece ap2 = new ArtPiece("Pheles", "Rafael Consuegra", 1991, R.drawable.pheles);
        mDb.artPieceModel().insertArtPiece(ap2);
        //Piet
        ArtPiece ap3 = new ArtPiece("Piet", "Alfredo Halegua", 1984, R.drawable.piet);
        mDb.artPieceModel().insertArtPiece(ap3);
        //Toboggan
        ArtPiece ap4 = new ArtPiece("Toboggan", "Alfredo Halegua", 1978, R.drawable.toboggan);
        mDb.artPieceModel().insertArtPiece(ap4);
        //For the Birds
        ArtPiece ap5 = new ArtPiece("For The Birds", "Alfredo Halegua", 1990, R.drawable.for_the_birds);
        mDb.artPieceModel().insertArtPiece(ap5);
        //Quebrada
        ArtPiece ap6 = new ArtPiece("Quebrada", "Alfredo Halegua", 1990, R.drawable.quebrada);
        mDb.artPieceModel().insertArtPiece(ap6);
        //Destellos
        ArtPiece ap7 = new ArtPiece("Destellos", "Mario Almaguer", 2004, R.drawable.destellos);
        mDb.artPieceModel().insertArtPiece(ap7);
        //Arcos
        ArtPiece ap8 = new ArtPiece("Arcos", "Mario Almaguer", 2004, R.drawable.arcos);
        mDb.artPieceModel().insertArtPiece(ap8);
        //untitled#1
        ArtPiece ap9 = new ArtPiece("Untitled #1", "William King", 0000, R.drawable.untitled1);
        mDb.artPieceModel().insertArtPiece(ap9);
        //untitled #2
        ArtPiece ap10 = new ArtPiece("Untitled #2", "William King", 0, R.drawable.untitled2);
        mDb.artPieceModel().insertArtPiece(ap10);
        //Touching point
        ArtPiece ap11 = new ArtPiece("Touching Point", "Alfredo Halegua", 1980, R.drawable.touching_point);
        mDb.artPieceModel().insertArtPiece(ap11);
        //Faron
        ArtPiece ap12 = new ArtPiece("Faron", "Rafael Consuegra", 1992, R.drawable.faron);
        mDb.artPieceModel().insertArtPiece(ap12);
        //Habitat
        ArtPiece ap13 = new ArtPiece("Habitat", "Alfredo Halegua", 1974, R.drawable.habitat);
        mDb.artPieceModel().insertArtPiece(ap13);
        //Architechtonic
        ArtPiece ap14 = new ArtPiece("Architechtonic", "Alfredo Halegua", 1969, R.drawable.architectonic);
        mDb.artPieceModel().insertArtPiece(ap14);
        //Cantilever
        ArtPiece ap15 = new ArtPiece("Cantilever", "Alfredo Halegua", 0, R.drawable.cantilever);
        mDb.artPieceModel().insertArtPiece(ap15);
        //statue of limitation
        ArtPiece ap16 = new ArtPiece("Statue of Limitations", "Alfredo Halegua", 1980, R.drawable.statue_of_limitations);
        mDb.artPieceModel().insertArtPiece(ap16);
        //pie in the sky
        ArtPiece ap17 = new ArtPiece("Pie in the Sky", "Alfredo Halegua", 1984, R.drawable.pie_in_the_sky);
        mDb.artPieceModel().insertArtPiece(ap17);
        //against the wall
        ArtPiece ap18 = new ArtPiece("Against the Wall", "Alfredo Halegua", 1974 , R.drawable.against_the_wall);
        mDb.artPieceModel().insertArtPiece(ap18);
        //piece of cake
        ArtPiece ap19 = new ArtPiece("Piece of cake", "Alfredo Halegua", 1981, R.drawable.piece_of_cake);
        mDb.artPieceModel().insertArtPiece(ap19);
        //giron iv
        ArtPiece ap20 = new ArtPiece("Giron IV (OLGA)", "Rafael Consuegra", 1992, R.drawable.giron_iv);
        mDb.artPieceModel().insertArtPiece(ap20);
        //Cubolic
        ArtPiece ap21 = new ArtPiece("Cubolic", "Alfredo Halegua", 1968, R.drawable.cubolic);
        mDb.artPieceModel().insertArtPiece(ap21);






        StringBuilder sb = new StringBuilder();
        artPieces = mDb.artPieceModel().findAllArtPiecesSync();



    }

    private void sayHello(String userName) {
        FileInputStream fin = null;
        StringBuilder temp = new StringBuilder();
        try {
            fin = getContext().openFileInput(FILENAME);

            int c;
            while( (c = fin.read()) != -1){
                temp.append(Character.toString((char)c));
            }
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast toast = Toast.makeText(getContext(), "Hello " + userName + "! Your password is " + temp.toString(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 10, 10);
        toast.show();
    }


    @Override
    public void onArtPieceSelected(int pos) {
        showList = false;
        position = pos;

        if(isLandscape){
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            ArtPieceFragment artPieceFragment = ArtPieceFragment.newInstance(artPieces.get(position));
            transaction.replace(R.id.container2, artPieceFragment, "artPieceFragment");
            transaction.addToBackStack("artPieceFragment");
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        } else{
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            ArtPieceFragment artPieceFragment = ArtPieceFragment.newInstance(artPieces.get(position));
            transaction.replace(R.id.container, artPieceFragment, "artPieceFragment");
            transaction.addToBackStack("artPieceFragment");
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }

    }

    @Override
    public void onArtPieceLongSelected(int position) {
        DialogFragment newFragment = ConfirmDeleteDialogFragment.newInstance(position);
        newFragment.show(getChildFragmentManager(), "confirmDeleteArtPiece");


    }

    /**
     * @return true = if this fragment can handle the backPress
     */
    public boolean onBackPressed() {
        if(!showList && !isLandscape) {
            getChildFragmentManager().popBackStackImmediate("artPieceFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            showList = true;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("artPiece", position);
        outState.putBoolean("showList", showList);
    }

    @Override
    public void onGetName(String name) {

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("userName", name);
        editor.commit();


        String fileContents = "password";
        FileOutputStream outputStream;
        try {
            outputStream = getContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            Log.w("WARNING", "Cannot save password");
        }

        sayHello(name);
    }
}