package com.example.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.databinding.ItemMovieBinding;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    public static final String VIDEOS_URL = "";
    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Inflate a layout from XML and return the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    //Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get movie at position
        Movie movie = movies.get(position);
        // Bind movie data into the vh
        holder.bind(movie);
    }

    // Returns total count of items in list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // View Holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        TextView voteAverage;
        ImageView ivPoster;
        ImageView playImage;
        private ItemMovieBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemMovieBinding.bind(itemView);
            tvTitle = binding.tvTitle;
            tvOverview = binding.tvOverview;
            ivPoster = binding.ivPoster;
            voteAverage = binding.voteAverage;
            container = binding.popularContainer;
            playImage = binding.playImage;
        }

        public void bind(final Movie movie) {
            // binding
            binding.setMovie(movie);
            binding.executePendingBindings();
            // set data into variables
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            voteAverage.setText(movie.getVoteAverage());
            String imageUrl;
            // check if we need to display youtube icon
            final float rating = (float)movie.getRating();
            if (rating > 5.0) {
                playImage.setVisibility(View.VISIBLE);
            }

            // check if phone is in landscape
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            }
            else {
                imageUrl = movie.getPosterPath();
            }

            Glide.with(context)
                    .load(imageUrl)
                    .apply(new RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.error))
                    .fitCenter()
                    .transform(new RoundedCornersTransformation(25, 10))
                    .into(ivPoster);

            // I. Register click listener on whole row
            // 2. Navigate to new activity
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) context, (View) tvTitle, "movieTitleTransition");
                    context.startActivity(i, options.toBundle());
                }
            });
        }
    }
}
