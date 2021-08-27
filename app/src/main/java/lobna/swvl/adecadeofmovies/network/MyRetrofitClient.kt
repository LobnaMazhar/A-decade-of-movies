package lobna.swvl.adecadeofmovies.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitClient {

    const val BASE_URL = "https://api.flickr.com/services/rest/"
    const val IMAGE_BASE_URL = "https://farm%s.static.flickr.com/%s/%s_%s.jpg"


    internal var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}