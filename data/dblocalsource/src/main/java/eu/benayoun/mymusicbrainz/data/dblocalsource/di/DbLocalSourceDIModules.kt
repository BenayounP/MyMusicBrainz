package eu.benayoun.mymusicbrainz.data.dblocalsource.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.benayoun.mymusicbrainz.data.dblocalsource.artist.ArtistCache
import eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.RoomArtistCache
import eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal.RoomArtistDatabase
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ArtistRoomCacheProvider

@Module
@InstallIn(SingletonComponent::class)
class DbLocalSourceDIModules {
    @ArtistRoomCacheProvider
    @Singleton
    @Provides
    fun providesRoomArtistCache(@ApplicationContext context: Context): ArtistCache {
        val database = RoomArtistDatabase.create(context)
        return RoomArtistCache(database.artistDao())
    }
}