package co.kr.naverblog_andoid.data.comment.comment

data class Comment(
    val commentId: Int,
    val content: String,
    val createdAt: String,
    val groupId: Int,
    val heartNum: Int,
    val isLike: Boolean,
    val isOwner: Boolean,
    val reply: List<Reply>,
    val userName: String
)