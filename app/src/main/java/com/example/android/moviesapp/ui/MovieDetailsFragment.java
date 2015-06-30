package com.example.android.moviesapp.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.moviesapp.R;
import com.example.android.moviesapp.adapter.MovieAdapter;
import com.example.android.moviesapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsFragment extends Fragment {

    private Movie[] mMovies;

    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.movieImageView) ImageView mImageView;
    @Bind(R.id.releaseDate) TextView mReleaseDateTextView;
    @Bind(R.id.averageRating) TextView mAverageRatingTextVIew;
    @Bind(R.id.summary) TextView mSummary;
    @Bind(R.id.ratingBar) RatingBar mMovieRatingBar;

    public MovieDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        ButterKnife.bind(this, rootView);

//        TextView mTitleTextView = (TextView) rootView.findViewById(R.id.titleTextView);
//        ImageView mImageView = (ImageView) rootView.findViewById(R.id.movieImageView);
//        TextView  mReleaseDateTextView = (TextView) rootView.findViewById(R.id.releaseDate);
//        TextView mAverageRatingTextVIew = (TextView) rootView.findViewById(R.id.averageRating);
//        TextView mSummary = (TextView) rootView.findViewById(R.id.summary);



        Intent intent = getActivity().getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MovieAdapter.MOVIE_DETAILS);
        int position = intent.getIntExtra("POSITION", 0);
        mMovies = Arrays.copyOf(parcelables, parcelables.length, Movie[].class);
        String url = "http://image.tmdb.org/t/p/w185/" + mMovies[position].getPosterString();
        String rating = mMovies[position].getUserRating();

        mTitleTextView.setText(mMovies[position].getMovieTitle());

        Picasso.with(getActivity())
                .load(url)
                .into(mImageView);

        mAverageRatingTextVIew.setText(rating + "/10");
        mMovieRatingBar.setRating(Float.parseFloat(rating)/2);
        mReleaseDateTextView.setText(mMovies[position].getReleaseDate());
        mSummary.setText(mMovies[position].getPlotSynopsis());

        return rootView;
    }
}
