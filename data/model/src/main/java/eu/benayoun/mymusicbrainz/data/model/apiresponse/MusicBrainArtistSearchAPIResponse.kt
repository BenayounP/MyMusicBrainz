package eu.benayoun.mymusicbrainz.data.model.apiresponse

import eu.benayoun.mymusicbrainz.data.model.Artist

sealed class MusicBrainArtistSearchAPIResponse : MusicBrainzAPIResponse<Artist>() {
    // special case when no search have been made
    class Empty : MusicBrainArtistSearchAPIResponse()

    // Success -> the list of artist found
    class Success(val artists: List<Artist>) : MusicBrainArtistSearchAPIResponse()

    // Error with explanation of origin
    class Error(val musicBrainzAPIError: MusicBrainzAPIError) : MusicBrainArtistSearchAPIResponse()
}
