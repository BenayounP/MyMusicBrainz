package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.artistsearch


import eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.HeaderManager
import eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.artistsearch.responsemodel.MusicBrainzArtistSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

internal interface RetrofitMusicBrainzArtistSearchService {
    @Headers(HeaderManager.header)
    @GET("artist")
    suspend fun searchArtist(
        @Query("query") query: String,
        @Query("fmt") format: String = "json"
    ): Response<MusicBrainzArtistSearchResponse>
}