package co.kr.naverblog_andoid.data.comment.write

data class RequestPostData(
    val postId: Int,
    val userName: String,
    val content: String,
    val groupId: Int
)
