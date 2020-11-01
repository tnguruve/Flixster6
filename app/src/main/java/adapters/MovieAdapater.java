package adapters;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;

import org.parceler.Parcels;

import java.util.List;
import Model.Movie;

import static android.media.CamcorderProfile.get;

public class MovieAdapater extends RecyclerView.Adapter<MovieAdapater.ViewHolder>
{
    Context context;
    List<Movie> movies;

    public MovieAdapater(Context context, List<Movie> movies)
    {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    // this inflates the layout and return it inside the viewHolder
    public MovieAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View movieView =  LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    // involves populating data into item through holder
    public void onBindViewHolder(@NonNull MovieAdapater.ViewHolder holder, int position)
    {
      // get the movie at the passed in position
       Movie movie = movies.get(position);
        // bind movie data
        holder.bind(movie);
    }
    // return total counts of items in the lists
    @Override
    public int getItemCount()
    {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

      public void bind(final Movie movie) {
          tvTitle.setText(movie.getTitle());
          tvOverview.setText(movie.getOverview());

          String imageUrl;
          if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
              imageUrl = movie.getBackdropPath();
          }
          else{
              imageUrl = movie.getPosterPath();
          }
          Glide.with(context).load(imageUrl).into(ivPoster);

          //sett click listener on whole row
          //Navigate to new row
          container.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  //Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();
                  Intent i = new Intent(context, DetailActivity.class);

                  i.putExtra("movie", Parcels.wrap(movie));
                  context.startActivity(i);
              }
          });

      }
  }
}
