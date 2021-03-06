package com.example.android.moviesapp.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moviesapp.R;
import com.example.android.moviesapp.adapter.MovieAdapter;
import com.example.android.moviesapp.model.Movie;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    public static final String TAG = MainActivity.class.getSimpleName();

    private Movie mMovies[];

    private String base_url;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    public MainActivityFragment() {
        base_url = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.popular_movies){

            base_url = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=";
            getResult();
            return true;

        }else if (id == R.id.best_rated){

            base_url = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=";
            getResult();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        getResult();

        return rootView;
    }

    private void getResult() {

        String api_key="15ae93992b3faf50d91572189a738361";

        String movieUrl = base_url + api_key + "";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().
                url(movieUrl).
                build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, "Failure", e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    if(response.isSuccessful()){
                        String jsonData = response.body().string();
                        mMovies = parseMovieDetails(jsonData);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setMovieDetails();
                            }
                        });
                        Log.v(TAG, jsonData);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error : ", e);
                } catch (JSONException e){
                    Log.e(TAG, "Unable to parse : ", e);
                }
            }
        });
    }

    private void setMovieDetails() {

        MovieAdapter movieAdapter = new MovieAdapter(getActivity(), mMovies);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(movieAdapter);
    }

    private Movie[] parseMovieDetails(String jsonData) throws JSONException {

        JSONObject data = new JSONObject(jsonData);
        JSONArray  array = data.getJSONArray("results");
        Movie[] movies = new Movie[array.length()];

        for (int i = 0; i < movies.length; i++){

            JSONObject movie = array.getJSONObject(i);
            Movie movieDetail = new Movie();
            movieDetail.setMovieTitle(movie.getString("original_title"));
            movieDetail.setPlotSynopsis(movie.getString("overview"));
            movieDetail.setPosterString(movie.getString("poster_path"));
            movieDetail.setReleaseDate(movie.getString("release_date"));
            movieDetail.setUserRating(movie.getString("vote_average"));
            movies[i] = movieDetail;
        }
        return movies;
    }
}
