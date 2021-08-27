package lobna.swvl.adecadeofmovies.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lobna.swvl.adecadeofmovies.data.MovieModel
import lobna.swvl.adecadeofmovies.data.RepoResponse
import lobna.swvl.adecadeofmovies.database.MyRoomDatabase
import lobna.swvl.adecadeofmovies.network.FlickrApiInterface
import lobna.swvl.adecadeofmovies.network.MyRetrofitClient

object MoviesRepository {

    suspend fun insertMovies(context: Context, movies: List<MovieModel>) {
        movies.forEach { MyRoomDatabase.invoke(context).movieDao().insertMovie(it) }
    }

    suspend fun getMovies(context: Context, query: String): List<MovieModel> {
        return MyRoomDatabase.invoke(context).movieDao().getMovies(query)
    }

    private val flickrApi: FlickrApiInterface by lazy {
        MyRetrofitClient.createService(FlickrApiInterface::class.java)
    }

    suspend fun getMovieImages(title: String, page: Int = 1): RepoResponse {
        return try {
            val response = flickrApi.images(title, page = page)

            if (response.isSuccessful) {
                RepoResponse.DataResponse(response.body())
            } else {
                RepoResponse.ErrorResponse(response.code(), response.message())
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) { e.printStackTrace() }
            RepoResponse.ExceptionResponse(e.message)
        }
    }
}