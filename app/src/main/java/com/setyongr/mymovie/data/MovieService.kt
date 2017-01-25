package com.setyongr.mymovie.data

import com.setyongr.mymovie.data.models.DiscoverResponse
import com.setyongr.mymovie.data.models.MovieResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface MovieService {
    companion object {
        val ENDPOINT = "https://api.themoviedb.org/3/"
    }

    @GET("discover/movie")
    fun getDiscover(@Query("sort_by") sort_by: String,
                    @Query("page") page: Int,
                    @Query("primary_release_date.lte") lte: String? = null): Observable<DiscoverResponse>

    @GET("movie/{id}")
    fun getMovie(@Path("id") id: Int): Observable<MovieResponse>

    object Creator {
        fun newService(): MovieService {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor {
                chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()

                val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", "1b2f29d43bf2e4f3142530bc6929d341")
                        .build()

                val requestBuilder = original.newBuilder().url(url)
                val request = requestBuilder.build()
                chain.proceed(request)

            }

            val retrofit = Retrofit.Builder()
                    .baseUrl(MovieService.ENDPOINT)
                    .client(httpClient.build())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()

            return retrofit.create(MovieService::class.java)
        }
    }

}