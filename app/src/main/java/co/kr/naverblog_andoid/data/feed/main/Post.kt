package co.kr.naverblog_andoid.data.feed.main

data class Post(
    val commentNum: String,
    val content: String,
    val createdAt: String,
    val heartNum: Int,
    val isLike: Boolean,
    val postId: Int,
    val secretCommentNum: Int,
    val title: String
)