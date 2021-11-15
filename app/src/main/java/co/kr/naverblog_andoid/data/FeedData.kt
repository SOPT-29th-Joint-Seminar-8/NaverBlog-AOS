package co.kr.naverblog_andoid.data

data class FeedData(
    val postId: Int,
    val imageUrl: Int,
    val title: String,
    val text: String,
    val date: String,
    val heartCount: Int,
    val isHeartPressed: Boolean,
    val commentCount: Int,
)