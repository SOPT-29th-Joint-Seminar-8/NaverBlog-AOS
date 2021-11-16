package co.kr.naverblog_andoid.view.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.adapter.CommentAdapter
import co.kr.naverblog_andoid.data.CommentData
import co.kr.naverblog_andoid.data.CommentFeedbackData
import co.kr.naverblog_andoid.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommentBinding
    private val adapter by lazy { CommentAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addItemList()
        initRecyclerView()
        imageBackOnClickEvent()
    }

    private fun initRecyclerView() {
        binding.recyclerviewComment.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun imageBackOnClickEvent() {
        binding.imageviewCommentBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun addItemList() {
        adapter.itemList.addAll(
            listOf(
                CommentData(
                    0,
                    R.drawable.ic_color_tag,
                    "userName",
                    true,
                    "text",
                    0,
                    false,
                    "2021.11.16",
                    "15:50",
                    0,
                    listOf()
                ), CommentData(
                    0,
                    R.drawable.ic_color_tag,
                    "userName",
                    false,
                    "text",
                    3,
                    true,
                    "2021.11.16",
                    "16:00",
                    5,
                    listOf(
                        CommentFeedbackData(
                            0,
                            R.drawable.ic_color_tag,
                            "userName",
                            false,
                            "text",
                            0,
                            false,
                            "2021.11.16",
                            "16:00",
                        ),
                        CommentFeedbackData(
                            0,
                            R.drawable.ic_color_tag,
                            "userName",
                            false,
                            "text",
                            0,
                            false,
                            "2021.11.16",
                            "16:00",
                        ),
                        CommentFeedbackData(
                            0,
                            R.drawable.ic_color_tag,
                            "userName",
                            false,
                            "text",
                            0,
                            false,
                            "2021.11.16",
                            "16:00",
                        ),
                        CommentFeedbackData(
                            0,
                            R.drawable.ic_color_tag,
                            "userName",
                            false,
                            "text",
                            0,
                            false,
                            "2021.11.16",
                            "16:00",
                        ),
                        CommentFeedbackData(
                            0,
                            R.drawable.ic_color_tag,
                            "userName",
                            false,
                            "text",
                            0,
                            false,
                            "2021.11.16",
                            "16:00",
                        ),
                    )
                ),
            )
        )
    }
}