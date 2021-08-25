package lobna.swvl.adecadeofmovies.data

data class MovieResponse(val movies: List<MovieModel>)

data class MovieModel(
    val title: String,
    val year: Int,
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int
)