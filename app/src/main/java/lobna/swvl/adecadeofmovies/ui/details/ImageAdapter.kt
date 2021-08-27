package lobna.swvl.adecadeofmovies.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import lobna.swvl.adecadeofmovies.R
import lobna.swvl.adecadeofmovies.data.FlickrModel
import lobna.swvl.adecadeofmovies.databinding.ItemImageBinding
import lobna.swvl.adecadeofmovies.diffUtil.FlickrDiffUtil

class ImageAdapter :
    PagingDataAdapter<FlickrModel, ImageAdapter.ImageViewHolder>(FlickrDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemImageBinding: ItemImageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_image, parent, false
        )
        return ImageViewHolder(itemImageBinding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageViewHolder(private val itemImageBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(itemImageBinding.root) {
        fun bind(item: FlickrModel?) {
            item?.run { itemImageBinding.iivm = ImageItemViewModel(this) }
        }
    }
}