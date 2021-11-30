package co.kr.naverblog_andoid.data

data class RecommentData(
    val id : Int,
    val name : String,
    val imageUrl : Int,
    val comment : String,
    val isOwner : Boolean,
    val date : String,
    val time : String,
    val isHeartClick : Boolean,
    val heartCount : Int,
)
