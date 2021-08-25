package lobna.swvl.adecadeofmovies.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lobna.swvl.adecadeofmovies.R
import lobna.swvl.adecadeofmovies.data.MovieModel
import lobna.swvl.adecadeofmovies.databinding.ItemYearBinding

class YearsAdapter(val items: Map<Int, List<MovieModel>>) :
    RecyclerView.Adapter<YearsAdapter.YearsViewHolder>() {

    val keys by lazy { items.keys.toMutableList() }
    val values by lazy { items.values.toMutableList() }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): YearsAdapter.YearsViewHolder {
        val itemYearBinding: ItemYearBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_year,
            parent,
            false
        )
        return YearsViewHolder(itemYearBinding)
    }

    override fun onBindViewHolder(holder: YearsViewHolder, position: Int) {
        holder.bind(keys[position], values[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    var query: String = ""

    fun updateData(query: String) {
        this.query = query

        keys.clear()
        keys.addAll(items.keys.toMutableList())

        values.clear()
        values.addAll(items.values.toMutableList())

        notifyDataSetChanged()
    }

    inner class YearsViewHolder(private var itemYearsBinding: ItemYearBinding) :
        RecyclerView.ViewHolder(itemYearsBinding.root) {

        fun bind(year: Int, movies: List<MovieModel>) {
            itemYearsBinding.yivm = YearItemViewModel(year, movies, query)
        }
    }
}