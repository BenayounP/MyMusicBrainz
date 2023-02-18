package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.releases


import eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.HeaderManager
import eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.releases.responsemodel.MusicBrainzGetReleasesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

internal interface RetrofitMusicBrainzReleasesService {
    @Headers(HeaderManager.header)
    @GET("release")
    suspend fun getReleases(
        @Query("query") query: String,
        @Query("fmt") format: String = "json",
    ): Response<MusicBrainzGetReleasesResponse>
}

