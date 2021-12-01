package co.kr.naverblog_andoid.data.comment.write

data class NewComment(
    val commentId: Int,
    val content: String,
    val createdAt: String,
    val groupId: Int,
    val heartNum: Int,
    val isLike: Boolean,
    val isOwner: Boolean,
    val userName: String
)