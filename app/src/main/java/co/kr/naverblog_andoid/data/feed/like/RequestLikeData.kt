package co.kr.naverblog_andoid.data.feed.like

import com.google.gson.annotations.SerializedName

data class RequestLikeData(
    @SerializedName("post-id")
    val postId: String,
)
