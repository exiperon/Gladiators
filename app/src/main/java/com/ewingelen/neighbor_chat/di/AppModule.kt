package com.ewingelen.neighbor_chat.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.ewingelen.neighbor_chat.AppSettings
import com.ewingelen.neighbor_chat.AppSettingsSerializer
import com.ewingelen.neighbor_chat.data.repository.NeighborChatRepositoryImpl
import com.ewingelen.neighbor_chat.domain.repository.NeighborChatRepository
import com.ewingelen.neighbor_chat.domain.use_cases.LogIn
import com.ewingelen.neighbor_chat.domain.use_cases.NeighborChatUseCases
import com.ewingelen.neighbor_chat.domain.use_cases.SendMessage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Singleton
//    @Provides
//    fun provideNeighborChatService(): NeighborChatService {
//
//    }
    private val Context.dataStore: DataStore<AppSettings>
            by dataStore("application.settings.json", AppSettingsSerializer)

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext app: Context) =
        app.dataStore

    @Singleton
    @Provides
    fun provideNeighborChatRepository(): NeighborChatRepository =
        NeighborChatRepositoryImpl()

    @Singleton
    @Provides
    fun provideUseCases(repository: NeighborChatRepository) =
        NeighborChatUseCases(
            logIn = LogIn(repository),
            sendMessage = SendMessage(repository)
        )
}