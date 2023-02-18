package eu.benayoun.mymusicbrainz.data.model.apiresponse

import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.model.apiresponse.global.MusicBrainzAPIError

sealed class MusicBrainzArtistSearchAPIResponse : MusicBrainzAPIResponse<Artist>() {
    // special case when no request have been made
    class Empty : MusicBrainzArtistSearchAPIResponse()

    // Success -> the list of artist found
    class Success(val artists: List<Artist>) : MusicBrainzArtistSearchAPIResponse()

    // Error with explanation of origin
    class Error(val musicBrainzAPIError: MusicBrainzAPIError) : MusicBrainzArtistSearchAPIResponse()
}
