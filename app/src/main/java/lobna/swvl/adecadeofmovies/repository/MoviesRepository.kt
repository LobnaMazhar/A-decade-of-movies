package lobna.swvl.adecadeofmovies.repository

import android.content.Context
import lobna.swvl.adecadeofmovies.data.MovieModel
import lobna.swvl.adecadeofmovies.database.MyRoomDatabase

object MoviesRepository {

    suspend fun insertMovies(context: Context, movies: List<MovieModel>) {
        movies.forEach { MyRoomDatabase.invoke(context).movieDao().insertMovie(it) }
    }

    suspend fun getMovies(context: Context, query: String): List<MovieModel> {
        return MyRoomDatabase.invoke(context).movieDao().getMovies(query)
    }
}