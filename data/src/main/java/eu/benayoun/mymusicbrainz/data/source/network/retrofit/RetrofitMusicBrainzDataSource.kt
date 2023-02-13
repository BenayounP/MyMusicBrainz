package eu.benayoun.mymusicbrainz.data.source.network.retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import eu.benayoun.mymusicbrainz.data.model.MusicBrainzAPIError
import eu.benayoun.mymusicbrainz.data.model.MusicBrainzArtistSearchAPIResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitMusicBrainzDataSource(val context: Context) {
    private val retrofitMusicBrainzSearchService: RetrofitMusicBrainzSearchService

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

        retrofitMusicBrainzSearchService =
            retrofit.create(RetrofitMusicBrainzSearchService::class.java)
    }

    suspend fun searchArtist(query: String): MusicBrainzArtistSearchAPIResponse {
        logv("    Retrofit step 1: check internet connectivity")
        if (!isNetworkConnected(context)) {
            return MusicBrainzArtistSearchAPIResponse.Error(
                MusicBrainzAPIError.NoInternet
            )
        }
        logv("    Retrofit step 2: get movies via API")
        try {
            val response = retrofitMusicBrainzSearchService.searchArtist(query)
            logv("    Retrofit step 3: process response")
            if (response.isSuccessful) {
                logv("          SUCCESS")
                val artists = response.body()?.artists
                if (artists != null) {
                    return MusicBrainzArtistSearchAPIResponse.Success(artists.map { musicBrainzArtist ->
                        musicBrainzArtist.asArtist()
                    })
                } else {
                    logv("          NO DATA")
                    return MusicBrainzArtistSearchAPIResponse.Error(MusicBrainzAPIError.NoData)
                }
            } else {
                logv("          Tool Error")
                return MusicBrainzArtistSearchAPIResponse.Error(MusicBrainzAPIError.ToolError)
            }
        } catch (e: Exception) {
            val exceptionMessage = e.localizedMessage
            logv("retrofit step 3: process EXCEPTION: $exceptionMessage")
            return MusicBrainzArtistSearchAPIResponse.Error(
                MusicBrainzAPIError.Exception(
                    exceptionMessage
                )
            )
        }
    }

    // INTERNAL COOKING

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