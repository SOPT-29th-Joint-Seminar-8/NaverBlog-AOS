package co.kr.naverblog_andoid.data

data class CommentData(
    val id : Int,
    val name : String,
    val imageUrl : Int,
    val comment : String,
    val isOwner : Boolean,
    val date : String,
    val time : String,
    val isHeartClick : Boolean,
    val heartCount : Int,
    val recommentCount : Int,
    val recommentList : List<RecommentData>,

    )
