package lobna.swvl.adecadeofmovies.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lobna.swvl.adecadeofmovies.R
import lobna.swvl.adecadeofmovies.data.MovieModel
import lobna.swvl.adecadeofmovies.databinding.ItemMovieBinding
import kotlin.math.min

class MoviesAdapter(val items: List<MovieModel>, val searching: Boolean) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemMovieBinding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        return MoviesViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return if (searching) min(items.size, 5) else items.size
    }

    inner class MoviesViewHolder(var itemMoviesBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMoviesBinding.root) {

        fun bind(item: MovieModel) {
            itemMoviesBinding.mivm = MovieItemViewModel(item)
        }
    }
}