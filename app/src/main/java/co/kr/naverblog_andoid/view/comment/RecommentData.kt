package co.kr.naverblog_andoid.view.comment

data class RecommentData(
    val id : Int,
    val name : String,
    val imageUrl : Int,
    val comment : String,
    val date : String,
    val time : String,
    val isHeartClick : Boolean,
    val heartCount : Int,
    )
