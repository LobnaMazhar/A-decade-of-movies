package lobna.swvl.adecadeofmovies.ui.details

import androidx.databinding.ObservableField
import lobna.swvl.adecadeofmovies.data.FlickrModel
import lobna.swvl.adecadeofmovies.network.MyRetrofitClient

class ImageItemViewModel(val item: FlickrModel) {

    val imageObservable = ObservableField(
        String.format(MyRetrofitClient.IMAGE_BASE_URL, item.farm, item.server, item.id, item.secret)
    )
}