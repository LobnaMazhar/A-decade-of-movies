package lobna.swvl.adecadeofmovies.data

data class FlickrResponse(
    val photos: PhotosFlickrResponse
)

data class PhotosFlickrResponse(
    val photo: List<FlickrModel>
)

data class FlickrModel(
    val id: String,
    val secret: String,
    val server: String,
    val farm: Int
)