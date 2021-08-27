package lobna.swvl.adecadeofmovies.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lobna.swvl.adecadeofmovies.R
import lobna.swvl.adecadeofmovies.databinding.ItemCastBinding

class CastAdapter(val items: List<String>) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val itemCastBinding: ItemCastBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_cast, parent, false
        )
        return CastViewHolder(itemCastBinding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class CastViewHolder(var itemCastBinding: ItemCastBinding) :
        RecyclerView.ViewHolder(itemCastBinding.root) {

        fun bind(item: String) {
            itemCastBinding.civm = CastItemViewModel(item)
        }
    }
}