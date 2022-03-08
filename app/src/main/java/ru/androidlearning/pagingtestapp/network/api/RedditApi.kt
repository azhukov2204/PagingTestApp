package ru.androidlearning.pagingtestapp.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.androidlearning.pagingtestapp.network.response.RedditResponse

interface RedditApi {
    @GET("r/aww/hot.json")
    suspend fun getTopPosts(
        @Query("limit") limit: Int = 0,
        @Query("after") after: String = "",
    ): Response<RedditResponse>
}
