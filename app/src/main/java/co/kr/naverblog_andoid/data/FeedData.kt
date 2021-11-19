package co.kr.naverblog_andoid.data

data class FeedData(
    val postId : Int,
    val img : Int,
    val title : String,
    val content : String,
    val date : String,
    val isHeartPressed : Boolean,
    val heartNum : Int,
    val commentNum : Int
)
