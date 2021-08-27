package lobna.swvl.adecadeofmovies.ui.home

import android.os.Bundle
import android.view.View
import lobna.swvl.adecadeofmovies.data.MovieModel
import lobna.swvl.adecadeofmovies.ui.details.DetailsActivity
import lobna.swvl.adecadeofmovies.utils.IntentClass

class MovieItemViewModel(val item: MovieModel) {

    fun onClick(view: View) {
        Bundle().run {
            putParcelable("movie", item)
            IntentClass.goToActivity(view.context, DetailsActivity::class.java, this)
        }
    }
}