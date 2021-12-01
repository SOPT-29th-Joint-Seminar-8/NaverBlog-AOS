package co.kr.naverblog_andoid.api

import co.kr.naverblog_andoid.data.comment.comment.ResponseCommentData
import co.kr.naverblog_andoid.data.comment.like.ResponseLikeData
import co.kr.naverblog_andoid.data.comment.write.RequestPostData
import co.kr.naverblog_andoid.data.comment.write.ResponsePostData
import retrofit2.Call
import retrofit2.http.*

interface CommentService {
    @GET("comment/{post_id}")
    fun getComment(
        @Path("post_id") postId: String
    ): Call<ResponseCommentData>

    @PATCH("comment/{comment_id}/like")
    fun patchLike(
        @Path("comment_id") commentId: String,
        @Query("state") state:Boolean,
    ): Call<ResponseLikeData>

    @POST("comment")
    fun postComment(
        @Body body: RequestPostData
    ): Call<ResponsePostData>
}