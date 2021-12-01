package co.kr.naverblog_andoid.data.comment.write

data class ResponsePostData(
    val `data`: Data,
    val message: String,
    val success: Boolean
)