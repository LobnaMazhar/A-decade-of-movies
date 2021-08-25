package lobna.swvl.adecadeofmovies.ui

import lobna.swvl.adecadeofmovies.data.MovieModel

class YearItemViewModel(val year: Int, movies: List<MovieModel>) {

    val movieAdapter = MoviesAdapter(movies)
}