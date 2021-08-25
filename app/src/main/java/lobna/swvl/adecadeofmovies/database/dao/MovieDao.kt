package lobna.swvl.adecadeofmovies.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import lobna.swvl.adecadeofmovies.data.MovieModel

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovie(movie: MovieModel)

    @Query("SELECT * from MovieModel WHERE title LIKE '%' || :query || '%'")
    suspend fun getMovies(query: String): List<MovieModel>
}