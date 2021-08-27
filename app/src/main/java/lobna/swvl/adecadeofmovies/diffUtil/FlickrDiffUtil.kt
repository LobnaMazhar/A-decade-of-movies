package lobna.swvl.adecadeofmovies.diffUtil

import androidx.recyclerview.widget.DiffUtil
import lobna.swvl.adecadeofmovies.data.FlickrModel

class FlickrDiffUtil : DiffUtil.ItemCallback<FlickrModel>() {

    override fun areItemsTheSame(old: FlickrModel, new: FlickrModel): Boolean {
        return old.id == new.id
    }

    override fun areContentsTheSame(old: FlickrModel, new: FlickrModel): Boolean {
        return old == new
    }
}