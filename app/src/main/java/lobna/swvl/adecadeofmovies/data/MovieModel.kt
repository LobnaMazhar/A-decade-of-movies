package lobna.swvl.adecadeofmovies.data

import androidx.room.Entity
import androidx.room.PrimaryKey

data class MovieResponse(val movies: List<MovieModel>)

@Entity
data class MovieModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val year: Int,
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int
)