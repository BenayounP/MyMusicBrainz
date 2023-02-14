package eu.benayoun.mymusicbrainz.data.source.network

import eu.benayoun.mymusicbrainz.data.model.MusicBrainzArtistSearchAPIResponse

interface MusicBrainzDataSource {
    suspend fun searchArtist(query: String): MusicBrainzArtistSearchAPIResponse
}