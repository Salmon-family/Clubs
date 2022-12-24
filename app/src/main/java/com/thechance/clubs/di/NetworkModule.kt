package com.thechance.clubs.di

import android.content.Context
import com.devfalah.remote.AuthInterceptor
import com.devfalah.remote.ClubService
import com.thechance.clubs.BuildConfig
import com.thechance.identity.remote.IdentityService
import com.thechance.remote.api.ChatService
import com.thechance.remote.api.CloudMessagingService
import com.thechance.local.DataStorePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideClubsService(retrofit: Retrofit): ClubService {
        return retrofit.create(ClubService::class.java)
    }

    @Singleton
    @Provides
    fun provideChatService(retrofit: Retrofit): ChatService {
        return retrofit.create(ChatService::class.java)
    }

    @Singleton
    @Provides
    fun provideIdentityService(retrofit: Retrofit): IdentityService {
        return retrofit.create(IdentityService::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebaseCloudMessagingApi(factory: GsonConverterFactory): CloudMessagingService =
        Retrofit.Builder()
            .baseUrl(CloudMessagingService.BASE_URL)
            .addConverterFactory(factory)
            .build()
            .create(CloudMessagingService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideDataStorePreferences(@ApplicationContext context: Context) =
        DataStorePreferences(context)
}