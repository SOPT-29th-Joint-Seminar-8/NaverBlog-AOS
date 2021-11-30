package co.kr.naverblog_andoid.view.main.feed

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.adapter.FeedAdapter
import co.kr.naverblog_andoid.data.FeedData
import co.kr.naverblog_andoid.databinding.FragmentFeedBinding
import co.kr.naverblog_andoid.view.comment.CommentActivity

class FeedFragment : Fragment() {
    private lateinit var feedAdapter: FeedAdapter
    private var _binding : FragmentFeedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 혜빈 작업공간
        _binding = FragmentFeedBinding.inflate(layoutInflater)

        feedAdapter = FeedAdapter() {
            val intent = Intent(context, CommentActivity::class.java)
            intent.putExtra("postId", it.postId)
            startActivity(intent)
        }
        binding.recyclerviewFeed.adapter = feedAdapter

        feedAdapter.feedList.addAll(
            listOf(
                FeedData(0, 0,"title", "contnet","date",false,true,6, 6),
                FeedData(1, 1,"title1", "contnet1","date1",false,true,7, 8),
                FeedData(2, 2,"title2", "contnet2","date2",false,true,6, 6)
            )
        )

        feedAdapter.notifyDataSetChanged()
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}