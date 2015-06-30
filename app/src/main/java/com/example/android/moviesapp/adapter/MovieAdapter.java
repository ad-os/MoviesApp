package com.example.android.moviesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.moviesapp.R;
import com.example.android.moviesapp.model.Movie;
import com.example.android.moviesapp.ui.MainActivityFragment;
import com.example.android.moviesapp.ui.MovieDetails;
import com.example.android.moviesapp.ui.MovieDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.InputMismatchException;

/**
 * Created by adhyan on 29/6/15.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Movie[] mMovies;
    private Context mContext;
    public  static final String MOVIE_DETAILS = "MOVIE_DETAILS";

    public MovieAdapter(Context context, Movie[] movies){
        mMovies = movies;
        mContext = context;
    }


    //At first this method is called.
    //Create new views invoked by layout manager.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Create new view
        View v = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    //Replace the contents of the view (invoked by layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Get the elements from data set at this position.
        //replace the contents of view with that element.
        holder.bindMovie(mMovies[position]);
    }


    //Return the size of the data set invoked by the layout manager.
    @Override
    public int getItemCount() {
        return mMovies.length;
    }

    //The view holder class maps the data as well as holding of views.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        public void bindMovie(Movie movie){

            String url = "http://image.tmdb.org/t/p/w185/" + movie.getPosterString();
            Picasso.with(mContext)
                    .load(url)
                    .into(mImageView);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(mContext, MovieDetails.class);
            intent.putExtra(MOVIE_DETAILS, mMovies);
            intent.putExtra("POSITION", position);
            mContext.startActivity(intent);
        }
    }
}
