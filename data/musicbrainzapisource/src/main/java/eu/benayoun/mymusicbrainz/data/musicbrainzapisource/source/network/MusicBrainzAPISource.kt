package eu.benayoun.mymusicbrainz.data.artistsearch.source.network

import eu.benayoun.mymusicbrainz.data.artistsearch.model.MusicBrainzArtistSearchAPIResponse

interface MusicBrainzAPISource {
    suspend fun searchArtist(query: String): MusicBrainzArtistSearchAPIResponse
}