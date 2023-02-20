package eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ArtistEntity::class, ReleaseEntity::class], version = 1)
internal abstract class RoomArtistDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "RoomArtistDatabase"

        fun create(context: Context): RoomArtistDatabase {
            return Room.databaseBuilder(
                context,
                RoomArtistDatabase::class.java,
                DB_NAME
            ).build()
        }
    }

    abstract fun artistDao(): ArtistDao
}