package co.kr.naverblog_andoid.data.feed.main

data class Banner(
    val bannerImage: String,
    val blogCategory: String,
    val blogId: Int,
    val blogName: String,
    val neighborNum: Int,
    val profileName: String,
    val todayCount: Int,
    val totalCount: Int
)