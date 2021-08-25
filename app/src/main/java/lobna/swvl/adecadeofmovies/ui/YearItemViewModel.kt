package lobna.swvl.adecadeofmovies.ui

import lobna.swvl.adecadeofmovies.data.MovieModel

class YearItemViewModel(val year: Int, movies: List<MovieModel>, val query: String) {

    val movieAdapter: MoviesAdapter = MoviesAdapter(movies, query.isNotBlank())
}