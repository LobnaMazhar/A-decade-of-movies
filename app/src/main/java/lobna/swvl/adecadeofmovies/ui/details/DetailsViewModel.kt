package lobna.swvl.adecadeofmovies.ui.details

import android.app.Application
import android.os.Bundle
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import lobna.swvl.adecadeofmovies.data.MovieModel
import lobna.swvl.adecadeofmovies.datasource.DataSourceInterface
import lobna.swvl.adecadeofmovies.datasource.FlickrDataSource
import lobna.swvl.adecadeofmovies.utils.SingleLiveEvent

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    val movieObservable = ObservableField<MovieModel>()
    val hasGenresObservable = ObservableBoolean(false)
    val hasCastObservable = ObservableBoolean(false)
    val hasGalleryObservable = ObservableBoolean(false)

    private val allGenres = arrayListOf<String>()
    val genreAdapter = GenreAdapter(allGenres)

    private val allCast = arrayListOf<String>()
    val castAdapter = CastAdapter(allCast)

    val imageAdapter = ImageAdapter()

    val onBackEvent = SingleLiveEvent<Boolean>()

    val onBackClick: () -> Unit = { onBackEvent.value = true }

    fun init(bundle: Bundle?) {
        bundle?.let {
            val movie = it.getParcelable("movie") as? MovieModel
            movie?.run {
                movieObservable.set(this)

                allGenres.addAll(genres)
                genreAdapter.notifyDataSetChanged()
                hasGenresObservable.set(allGenres.isNotEmpty())

                allCast.addAll(cast)
                castAdapter.notifyDataSetChanged()
                hasCastObservable.set(allCast.isNotEmpty())

                getImages(title)
            }
        }
    }

    private fun getImages(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Pager(PagingConfig(pageSize = 10)) {
                FlickrDataSource(getApplication(), title, object : DataSourceInterface {
                    override fun totalCount(total: Int) {
                        hasGalleryObservable.set(hasGalleryObservable.get() || total != 0)
                    }
                })
            }.flow.cachedIn(viewModelScope).collectLatest { imageAdapter.submitData(it) }
        }
    }
}