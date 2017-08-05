package cn.haohao.dbbook.di

import android.content.Context
import cn.haohao.dbbook.BuildConfig
import cn.haohao.dbbook.data.BookService
import cn.haohao.dbbook.data.DmzjService
import cn.haohao.dbbook.data.datasource.BookDataSource
import cn.haohao.dbbook.data.datasource.cloud.CloudBookDataSource
import cn.haohao.dbbook.data.datasource.cloud.CloudDmzjDataSource
import cn.haohao.dbbook.data.net.RequestInterceptor
import cn.haohao.dbbook.data.net.ResponseInterceptor
import cn.haohao.dbbook.di.qualifier.ApplicationQualifier
import cn.haohao.dbbook.di.qualifier.CloudDataQualifier
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by HaohaoChang on 2017/6/10.
 */
@Module
class DataModule {

    @Provides @Singleton
    fun provideCache(@ApplicationQualifier context: Context) = Cache(context.cacheDir, 10 * 1024 * 1024.toLong())

    @Provides @Singleton
    fun provideRequestInterceptor() = RequestInterceptor()

    @Provides @Singleton
    fun provideResponseInterceptor() = ResponseInterceptor()

    @Provides @Singleton
    fun provideOkHttpClient(cache: Cache,
                            requestInterceptor: RequestInterceptor,
                            responseInterceptor: ResponseInterceptor): OkHttpClient =
            OkHttpClient().newBuilder()
                    .cache(cache)
                    .addInterceptor(requestInterceptor)
                    .addNetworkInterceptor(responseInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    }).build()

//    @Provides @Singleton
//    fun provideRestAdapter(client: OkHttpClient): Retrofit =
//            retrofit(client,BookService.BASE_URL)

    @Provides @Singleton
    fun provideBookService(client: OkHttpClient): BookService =
            retrofit(client,BookService.BASE_URL)
                    .create(BookService::class.java)

    @Provides @Singleton @CloudDataQualifier
    fun provideBookDataSource(bookService: BookService): BookDataSource =
            CloudBookDataSource(bookService)

    @Provides @Singleton
    fun provideDmzjService(dmzjClient: OkHttpClient): DmzjService =
            retrofit(dmzjClient,DmzjService.BASE_URL)
                    .create(DmzjService::class.java)
//
    @Provides @Singleton @CloudDataQualifier
    fun provideDmzjDataSource(dmzjService: DmzjService): CloudDmzjDataSource =
            CloudDmzjDataSource(dmzjService)

    private fun retrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

}