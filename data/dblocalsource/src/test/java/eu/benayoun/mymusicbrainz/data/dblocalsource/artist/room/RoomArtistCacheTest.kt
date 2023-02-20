package eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room

import androidx.room.Room
import com.google.common.truth.Truth
import eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal.ArtistDao
import eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal.RoomArtistDatabase
import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.model.Release
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
class RoomArtistCacheTest : AndroidLocalTest() {
    private lateinit var roomArtistDatabase: RoomArtistDatabase
    private lateinit var artistDao: ArtistDao
    private lateinit var roomArtistCache: RoomArtistCache

    @Before
    fun setup() {
        roomArtistDatabase =
            Room.inMemoryDatabaseBuilder(applicationContext, RoomArtistDatabase::class.java)
                .allowMainThreadQueries().build()
        artistDao = roomArtistDatabase.artistDao()
        roomArtistCache = RoomArtistCache(artistDao)
    }

    @After
    fun cleanup() {
        roomArtistDatabase.close()
    }

    @Test
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun testInstantiations() = runTest {
        // ARRANGE
        // nothing to do

        // ACT
        // nothing to do

        //ASSERT
        Truth.assertWithMessage("RoomArtistDatabase instantiation: ").that(roomArtistDatabase)
            .isNotNull()
        Truth.assertWithMessage("ArtistDao instantiation: ").that(artistDao).isNotNull()
        Truth.assertWithMessage("RoomArtistCache instantiation: ").that(roomArtistCache).isNotNull()
    }

    @Test
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun testListInDBAfterMultipleInsertions() = runTest {
        // ARRANGE
        val release = Release("42", "vive le graves", "2023")
        val artist1 = Artist("1", "1", "France", "Group", listOf(release))
        val artist2 = Artist("2", "2", "France", "Group")
        val artist3 = Artist("3", "3", "France", "Group")
        val artist4 = Artist("4", "4", "France", "Group")


        // ACT
        roomArtistCache.saveArtist(artist1)
        roomArtistCache.saveArtist(artist1)
        roomArtistCache.saveArtist(artist2)
        roomArtistCache.saveArtist(artist3)
        roomArtistCache.saveArtist(artist4)

        //ASSERT
        val actualList = roomArtistCache.getLast3ArtistsConsultedFlow().first()
        // size first
        val actualDBSize = actualList.size
        Truth.assertWithMessage("userActivityDatabase size: ").that(actualDBSize).isEqualTo(3)

        // content after
        Truth.assertThat(actualList[0]).isEqualTo(artist4)
        Truth.assertThat(actualList[1]).isEqualTo(artist3)
        Truth.assertThat(actualList[2]).isEqualTo(artist2)
    }

    @Test
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun testSaveAndRetrieve() = runTest {
        // ARRANGE
        val release1 = Release("42", "vive le graves", "2023")
        val release2 = Release("43", "vive le graves!!", "2023")
        val release3 = Release("44", " super le graves", "2023")
        val artist1 = Artist("1", "1", "France", "Group", listOf(release1, release2, release3))

        // ACT
        roomArtistCache.saveArtist(artist1)

        //ASSERT
        val foundArtist = roomArtistCache.getArtist(artist1.id)
        Truth.assertThat(foundArtist).isEqualTo(artist1)
    }
}