package eu.benayoun.mymusicbrainz.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.benayoun.mymusicbrainz.data.repository.DefaultRepository
import eu.benayoun.mymusicbrainz.data.repository.Repository
import eu.benayoun.mymusicbrainz.data.source.network.MusicBrainzDataSource
import eu.benayoun.mymusicbrainz.data.source.network.di.MusicBrainzDataSourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RepositoryProvider

@Module
@InstallIn(SingletonComponent::class)
class RepositoryDiModule {
    @RepositoryProvider
    @Singleton
    @Provides
    internal fun providesRepository(
        @MusicBrainzDataSourceProvider musicBrainzDataSource: MusicBrainzDataSource
    ): Repository = DefaultRepository(musicBrainzDataSource, MainScope(), Dispatchers.IO)
}