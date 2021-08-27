package lobna.swvl.adecadeofmovies.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lobna.swvl.adecadeofmovies.R
import lobna.swvl.adecadeofmovies.databinding.ItemGenreBinding

class GenreAdapter(val items: List<String>) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val itemGenreBinding: ItemGenreBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_genre, parent, false
        )
        return GenreViewHolder(itemGenreBinding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class GenreViewHolder(var itemGenreBinding: ItemGenreBinding) :
        RecyclerView.ViewHolder(itemGenreBinding.root) {

        fun bind(item: String) {
            itemGenreBinding.givm = GenreItemViewModel(item)
        }
    }
}