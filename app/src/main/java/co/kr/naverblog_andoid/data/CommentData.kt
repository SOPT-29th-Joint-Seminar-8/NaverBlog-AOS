package co.kr.naverblog_andoid.data

data class CommentData(
    val commentId: Int,
    val imageUrl: Int,
    val username: String,
    val isOwner: Boolean,
    val text: String,
    val heartCount: Int,
    val isHeartPressed: Boolean,
    val date: String,
    val time: String,
    val feedbackCount: Int,
    val feedbackList: List<CommentFeedbackData>
)
