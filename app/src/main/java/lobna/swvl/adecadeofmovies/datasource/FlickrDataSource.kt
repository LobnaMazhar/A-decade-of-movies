package lobna.swvl.adecadeofmovies.datasource

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.paging.PagingSource
import androidx.paging.PagingState
import lobna.swvl.adecadeofmovies.data.FlickrModel
import lobna.swvl.adecadeofmovies.data.FlickrResponse
import lobna.swvl.adecadeofmovies.data.RepoResponse
import lobna.swvl.adecadeofmovies.repository.MoviesRepository

class FlickrDataSource(
    val context: Context,
    val title: String,
    val dataSourceInterface: DataSourceInterface
) : PagingSource<Int, FlickrModel>() {

    private val TAG = FlickrDataSource::class.java.simpleName

    override fun getRefreshKey(state: PagingState<Int, FlickrModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FlickrModel> {
        try {
            val page = params.key ?: 1

            when (val response = makeApiCall(page)) {
                is RepoResponse.ErrorResponse ->
                    Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                is RepoResponse.ExceptionResponse ->
                    Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                is RepoResponse.DataResponse<*> -> {
                    (response.data as? FlickrResponse)?.run {
                        photos.photo.let {
                            dataSourceInterface.totalCount(it.size)
                            return LoadResult.Page(it, null, page + 1)
                        }
                    }
                }
            }
            return LoadResult.Page(emptyList(), null, null)
        } catch (exception: Exception) {
            Log.e(TAG, "Failed to fetch data!")
            return LoadResult.Error(exception)
        }
    }

    private suspend fun makeApiCall(page: Int): RepoResponse {
        return MoviesRepository.getMovieImages(title, page)
    }
}