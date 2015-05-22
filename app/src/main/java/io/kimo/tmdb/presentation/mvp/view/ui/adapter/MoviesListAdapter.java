package io.kimo.tmdb.presentation.mvp.view.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.kimo.tmdb.R;
import io.kimo.tmdb.presentation.TMDb;
import io.kimo.tmdb.presentation.mvp.model.MovieModel;
import io.kimo.tmdb.presentation.mvp.view.ui.activity.MovieDetailActivity;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder> {

    private Context context;
    private List<MovieModel> data = new ArrayList<>();

    public MoviesListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<MovieModel> movies) {
        data = movies;
        notifyDataSetChanged();
    }

    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        MovieModel movieModel = data.get(i);

        viewHolder.title.setText(movieModel.getName());
        viewHolder.subtitle.setText(movieModel.getYearOfRelease());
        TMDb.PICASSO.load(movieModel.getCover()).into(viewHolder.cover);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{

        private ImageView cover;
        private TextView title, subtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            cover = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startActivity(MovieDetailActivity.getCallingIntent(context, data.get(getPosition())));
                }
            }, 200); // <--- Giving time to the ripple effect finish before opening a new activity
        }
    }
}