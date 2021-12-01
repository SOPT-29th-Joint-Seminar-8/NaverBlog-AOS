package co.kr.naverblog_andoid.data.comment.comment

data class Data(
    val commentNum: Int,
    val comments: List<Comment>,
    val secretCommentNum: Int
)