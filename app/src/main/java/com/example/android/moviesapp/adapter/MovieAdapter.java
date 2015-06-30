package com.example.android.moviesapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.moviesapp.R;
import com.example.android.moviesapp.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by adhyan on 29/6/15.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Movie[] mMovies;
    private Context mContext;

    public MovieAdapter(Context context, Movie[] movies){
        mMovies = movies;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindMovie(mMovies[position]);
    }

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
        }

        public void bindMovie(Movie movie){

            String url = "http://image.tmdb.org/t/p/w185/" + movie.getPosterString();
            Picasso.with(mContext)
                    .load(url)
                    .into(mImageView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
