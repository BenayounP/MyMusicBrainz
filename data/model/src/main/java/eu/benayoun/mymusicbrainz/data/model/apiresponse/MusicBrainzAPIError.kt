package eu.benayoun.mymusicbrainz.data.model.apiresponse

sealed class MusicBrainzAPIError() {
    object NoInternet : MusicBrainzAPIError()
    object ToolError : MusicBrainzAPIError() // for retrofit errors for example
    object NoData : MusicBrainzAPIError() // there is no data in the response
    class Exception(val localizedMessage: String) :
        MusicBrainzAPIError() // if there was some Kotlin exception

    override fun toString(): String {
        val stringBuffer = StringBuffer()
        stringBuffer.append(this.javaClass.simpleName)
        if (this is Exception) {
            stringBuffer.append(": message: $localizedMessage")
        }
        return stringBuffer.toString()
    }
}