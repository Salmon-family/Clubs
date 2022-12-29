package com.thechance.clubs.di

import com.devfalah.remote.AuthInterceptor
import com.devfalah.remote.ClubService
import com.devfalah.remote.DdosInterceptor
import com.google.firebase.firestore.FirebaseFirestore
import com.thechance.clubs.BuildConfig
import com.thechance.identity.remote.IdentityService
import com.thechance.remote.api.ChatService
import com.thechance.remote.api.CloudMessagingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideClubsService(
        @RetrofitClubService retrofit: Retrofit,
    ): ClubService {
        return retrofit.create(ClubService::class.java)
    }

    @Singleton
    @Provides
    fun provideChatService(
        @RetrofitClubService retrofit: Retrofit,
    ): ChatService {
        return retrofit.create(ChatService::class.java)
    }

    @Singleton
    @Provides
    fun provideIdentityService(
        @RetrofitClubService retrofit: Retrofit,
    ): IdentityService {
        return retrofit.create(IdentityService::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebaseCloudMessagingApi(
        @RetrofitCloudMessagingService retrofit: Retrofit,
    ): CloudMessagingService =
        retrofit.create(CloudMessagingService::class.java)


    @RetrofitCloudMessagingService
    @Singleton
    @Provides
    fun provideRetrofitCloudMessagingService(
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CloudMessagingService.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @RetrofitClubService
    @Singleton
    @Provides
    fun provideRetrofitClubService(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        ddosInterceptor: DdosInterceptor,
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(ddosInterceptor)
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RetrofitClubService

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RetrofitCloudMessagingService

    @Singleton
    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

}