package lobna.swvl.adecadeofmovies.data

sealed class RepoResponse {
    data class ErrorResponse(val code: Int, val message: String) : RepoResponse()
    data class ExceptionResponse(val message: String?) : RepoResponse()
    data class DataResponse<T>(val data: T) : RepoResponse()
}