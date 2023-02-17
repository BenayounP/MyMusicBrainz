package eu.benayoun.mymusicbrainz.data.repository.source.network

import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainArtistSearchAPIResponse


interface MusicBrainzAPISource {
    suspend fun searchArtist(query: String): MusicBrainArtistSearchAPIResponse
}