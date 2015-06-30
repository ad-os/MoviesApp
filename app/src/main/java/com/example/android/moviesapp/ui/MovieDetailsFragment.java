package com.example.android.moviesapp.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moviesapp.R;
import com.example.android.moviesapp.adapter.MovieAdapter;
import com.example.android.moviesapp.model.Movie;

import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsFragment extends Fragment {

    private Movie[] mMovies;

    public MovieDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);

        Intent intent = getActivity().getIntent();

        Parcelable[] parcelables = intent.getParcelableArrayExtra(MovieAdapter.MOVIE_DETAILS);
        mMovies = Arrays.copyOf(parcelables, parcelables.length, Movie[].class);





        return rootView;
    }
}
