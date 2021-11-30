package co.kr.naverblog_andoid.api

import co.kr.naverblog_andoid.data.feed.like.RequestLikeData
import co.kr.naverblog_andoid.data.feed.like.ResponseLikeData
import co.kr.naverblog_andoid.data.feed.main.ResponseFeedData
import retrofit2.Call
import retrofit2.http.*

interface FeedService {
    @GET("post/main")
    fun getMain(): Call<ResponseFeedData>

    @PATCH("post/{post-id}/like")
    fun patchLike(
        @Path("post-id") postId:String,
        @Query("state") state:Boolean,
    ): Call<ResponseLikeData>
}