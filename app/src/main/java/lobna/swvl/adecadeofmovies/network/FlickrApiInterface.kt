package lobna.swvl.adecadeofmovies.network

import lobna.swvl.adecadeofmovies.BuildConfig
import lobna.swvl.adecadeofmovies.data.FlickrResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiInterface {

    @GET("?")
    suspend fun images(
        @Query("text") text: String,
        @Query("method") method: String = "flickr.photos.search",
        @Query("api_key") apiKey: String = BuildConfig.FLICKR_API_KEY,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Response<FlickrResponse>

}