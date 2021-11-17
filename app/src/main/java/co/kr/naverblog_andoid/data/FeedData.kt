package co.kr.naverblog_andoid.data

data class FeedData(
    val img : Int,
    val title : String,
    val content : String,
    val date : String,
    val isHeartPressed : Boolean,
    val heartNum : Int,
    val commentNum : Int
)
