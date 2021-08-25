package lobna.swvl.adecadeofmovies.ui

import android.app.Application
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import lobna.swvl.adecadeofmovies.data.MovieResponse
import lobna.swvl.adecadeofmovies.repository.MoviesRepository
import lobna.swvl.adecadeofmovies.utils.Utilities

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val searchEditTextObservable = ObservableField<String>()


    private val moviesResponse: MovieResponse = MoviesRepository.getMovies(application)
    private val allMoviesByYears = moviesResponse.movies.groupBy { it.year }
    val yearsAdapter: YearsAdapter = YearsAdapter(allMoviesByYears)


}