package com.example.android.moviesapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        ArrayAdapter<ImageView> adapter = new ArrayAdapter<ImageView>(getActivity(), R.layout.grid_item
                    ,R.id.grid_image_view, new ArrayList<ImageView>());


        GridView gridView = (GridView) rootView.findViewById(R.id.gridView);

        gridView.setAdapter(gridView);

//        Picasso.with(getActivity()).
//                load(R.drawable.ic_launcher).
//                into(image);

        return rootView;
    }
}
