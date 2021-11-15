package co.kr.naverblog_andoid.view.main.feed

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.adapter.FeedAdapter
import co.kr.naverblog_andoid.data.FeedData
import co.kr.naverblog_andoid.databinding.FragmentFeedBinding
import co.kr.naverblog_andoid.databinding.ItemFeedBoardBinding
import co.kr.naverblog_andoid.view.comment.CommentActivity

class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding ?: error("binding not initialized")
    private val adapter by lazy {
        FeedAdapter() {
            val intent = Intent(context, CommentActivity::class.java)
            intent.putExtra("postId", it.postId)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(layoutInflater, container, false)
        initRecyclerView()
        heartClickEvent()
        return binding.root
    }

    private fun initRecyclerView() {
        adapter.itemList.addAll(
            listOf(
                FeedData(0, R.drawable.img_post, "Title", "Text", "2021.11.16", 0, false, 0),
                FeedData(0, R.drawable.img_post, "Title", "Text", "2021.11.16", 1, true, 0),
                FeedData(0, R.drawable.img_post, "Title", "Text", "2021.11.16", 0, false, 3),
                FeedData(0, R.drawable.img_post, "Title", "Text", "2021.11.16", 0, false, 0),
                FeedData(0, R.drawable.img_post, "Title", "Text", "2021.11.16", 0, false, 0)
            )
        )

        binding.recyclerviewFeedBoard.adapter = adapter
        binding.recyclerviewFeedBoard.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()
    }

    private fun heartClickEvent() {

    }
}