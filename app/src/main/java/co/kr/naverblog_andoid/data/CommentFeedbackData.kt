package co.kr.naverblog_andoid.data

data class CommentFeedbackData(
    val commentId: Int,
    val imageUrl: Int,
    val username: String,
    val isOwner: Boolean,
    val text: String,
    val heartCount: Int,
    val isHeartPressed: Boolean,
    val date: String,
    val time: String,
)
