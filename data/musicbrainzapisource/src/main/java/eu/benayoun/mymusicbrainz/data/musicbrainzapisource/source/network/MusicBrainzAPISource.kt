package eu.benayoun.mymusicbrainz.data.repository.source.network

import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzArtistSearchAPIResponse
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzGetArtistReleasesAPIResponse


interface MusicBrainzAPISource {

    // search artist with query : get a list of artists that could correspond or detailed error if not possible
    suspend fun searchArtist(query: String): MusicBrainzArtistSearchAPIResponse

    // search releases for artistId : get a list of release that could correspond or detailed error if not possible
    suspend fun getReleases(artistId: String): MusicBrainzGetArtistReleasesAPIResponse
}