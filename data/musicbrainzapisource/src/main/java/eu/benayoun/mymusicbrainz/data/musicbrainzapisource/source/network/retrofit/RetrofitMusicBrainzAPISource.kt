package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzAPIResponse
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzArtistSearchAPIResponse
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzGetArtistReleasesAPIResponse
import eu.benayoun.mymusicbrainz.data.model.apiresponse.global.MusicBrainzAPIError
import eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.artistsearch.RetrofitMusicBrainzArtistSearchService
import eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.releases.RetrofitMusicBrainzReleasesService
import eu.benayoun.mymusicbrainz.data.repository.source.network.MusicBrainzAPISource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitMusicBrainzAPISource(val context: Context) : MusicBrainzAPISource {
    private val retrofitMusicBrainzArtistSearchService: RetrofitMusicBrainzArtistSearchService
    private val retrofitMusicBrainzReleasesService: RetrofitMusicBrainzReleasesService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://musicbrainz.org/ws/2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofitMusicBrainzArtistSearchService =
            retrofit.create(RetrofitMusicBrainzArtistSearchService::class.java)
        retrofitMusicBrainzReleasesService =
            retrofit.create(RetrofitMusicBrainzReleasesService::class.java)

    }

    override suspend fun searchArtist(query: String): MusicBrainzArtistSearchAPIResponse {
        val musicBrainzAPIResponse =
            getData { retrofitMusicBrainzArtistSearchService.searchArtist(query) }
        return when (musicBrainzAPIResponse) {
            is MusicBrainzAPIResponse.Success ->
                MusicBrainzArtistSearchAPIResponse.Success(musicBrainzAPIResponse.successContent.artists.map { it.asArtist() })
            is MusicBrainzAPIResponse.Error ->
                MusicBrainzArtistSearchAPIResponse.Error(musicBrainzAPIResponse.musicBrainzAPIError)
            else -> MusicBrainzArtistSearchAPIResponse.Error(getWeirdException())
        }

    }

    override suspend fun getReleases(artistId: String): MusicBrainzGetArtistReleasesAPIResponse {
        val musicBrainzAPIResponse =
            getData { retrofitMusicBrainzReleasesService.getReleases("arid:$artistId") }
        return when (musicBrainzAPIResponse) {
            is MusicBrainzAPIResponse.Success ->
                MusicBrainzGetArtistReleasesAPIResponse.Success(musicBrainzAPIResponse.successContent.musicBrainzReleases.map { it.asRelease() })
            is MusicBrainzAPIResponse.Error ->
                MusicBrainzGetArtistReleasesAPIResponse.Error(musicBrainzAPIResponse.musicBrainzAPIError)
            else -> MusicBrainzGetArtistReleasesAPIResponse.Error(getWeirdException())
        }
    }

    // INTERNAL COOKING

    private suspend fun <SUCCESS_CONTENT> getData(request: suspend (Any) -> Response<SUCCESS_CONTENT>): MusicBrainzAPIResponse<SUCCESS_CONTENT> {
        logv("    Retrofit step 1: check internet connectivity")
        if (!isNetworkConnected(context)) {
            return MusicBrainzAPIResponse.Error(MusicBrainzAPIError.NoInternet)
        }
        logv("    Retrofit step 2: get movies via API")
        try {
            val response = request.invoke(Any())
            logv("    Retrofit step 3: process response")
            if (response.isSuccessful) {
                logv("          SUCCESS")
                val responseBody = response.body()
                if (responseBody != null) {
                    return MusicBrainzAPIResponse.Success(responseBody)
                } else {
                    logv("          NO DATA")
                    return MusicBrainzAPIResponse.Error(MusicBrainzAPIError.NoData)
                }
            } else {
                logv("          Tool Error")
                return MusicBrainzAPIResponse.Error(MusicBrainzAPIError.ToolError)
            }
        } catch (e: Exception) {
            val exceptionMessage = e.localizedMessage
            logv("retrofit step 3: process EXCEPTION: $exceptionMessage")
            return MusicBrainzAPIResponse.Error(
                MusicBrainzAPIError.Exception(
                    exceptionMessage ?: ""
                )
            )
        }
    }

    private fun getWeirdException() = MusicBrainzAPIError.Exception("UNKNOWN PROBLEM 42")

    fun logv(message: String) {
        Log.v("MMMB_V", message)
    }

    fun loge(message: String) {
        Log.e("MMMB_E", message)
    }

    private fun isNetworkConnected(context: Context): Boolean {
        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}