package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.artistsearch


import eu.benayoun.mymusicbrainz.data.repository.source.network.retrofit.response.MusicBrainzArtistSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

internal interface RetrofitMusicBrainzSearchService {
    @Headers("User-Agent: MyMusicBrainz/0.9 ( pierre@cabnum.fr )")
    @GET("artist")
    suspend fun searchArtist(
        @Query("query") query: String,
        @Query("fmt") format: String = "json"
    ): Response<MusicBrainzArtistSearchResponse>
}