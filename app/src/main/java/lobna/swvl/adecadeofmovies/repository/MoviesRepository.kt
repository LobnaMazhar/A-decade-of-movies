package lobna.swvl.adecadeofmovies.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import lobna.swvl.adecadeofmovies.data.MovieResponse
import lobna.swvl.adecadeofmovies.utils.Utilities

object MoviesRepository {

    fun getMovies(context: Context): MovieResponse {
        val response = Utilities.getJsonDataFromAsset(context, "movies.json")

        return Gson().fromJson(response, object : TypeToken<MovieResponse>() {}.type)
    }
}