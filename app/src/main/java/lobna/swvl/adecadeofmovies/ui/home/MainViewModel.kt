package lobna.swvl.adecadeofmovies.ui.home

import android.app.Application
import android.content.Context
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lobna.swvl.adecadeofmovies.data.MovieModel
import lobna.swvl.adecadeofmovies.data.MovieResponse
import lobna.swvl.adecadeofmovies.repository.MoviesRepository
import lobna.swvl.adecadeofmovies.sharedprefs.MoviesSharedPreferences
import lobna.swvl.adecadeofmovies.utils.Utilities

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val searchEditTextObservable = ObservableField<String>()
    val onEditorActionListener = TextView.OnEditorActionListener { v, actionId, event ->
        when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> {
                Utilities.hideKeyboard(v)
                val query = searchEditTextObservable.get() ?: ""
                search(v.context, query)
                true
            }
            else -> false
        }
    }

    private val allMoviesByYears = hashMapOf<Int, List<MovieModel>>()
    val yearsAdapter: YearsAdapter = YearsAdapter(allMoviesByYears)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val insertionJob = async { updateData(application) }
            insertionJob.await()
            search(application)
        }
    }

    private suspend fun updateData(context: Context) {
        if (MoviesSharedPreferences.isRecentlyInstalled(context)) {
            val response = Utilities.getJsonDataFromAsset(context, "movies.json")
            val movieResponse: MovieResponse =
                Gson().fromJson(response, object : TypeToken<MovieResponse>() {}.type)
            val movies = movieResponse.movies.sortedByDescending { it.rating }
            MoviesRepository.insertMovies(context, movies)
            MoviesSharedPreferences.setInstalled(context)
        }
    }

    fun search(context: Context, query: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            val searchJob = async { MoviesRepository.getMovies(context, query) }
            val response = searchJob.await()
            withContext(Dispatchers.Main) {
                allMoviesByYears.clear()
                allMoviesByYears.putAll(response.groupBy { it.year })
                yearsAdapter.updateData(query)
            }
        }
    }
}