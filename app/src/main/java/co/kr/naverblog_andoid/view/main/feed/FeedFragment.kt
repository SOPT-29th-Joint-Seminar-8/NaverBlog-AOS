package co.kr.naverblog_andoid.view.main.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.adapter.FeedAdapter
import co.kr.naverblog_andoid.data.FeedData
import co.kr.naverblog_andoid.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {
    private lateinit var feedAdapter: FeedAdapter
    private lateinit var binding : FragmentFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 혜빈 작업공간
        binding = FragmentFeedBinding.inflate(layoutInflater)

        feedAdapter = FeedAdapter()
        binding.recyclerviewFeed.adapter = feedAdapter

        feedAdapter.feedList.addAll(
            listOf(
                FeedData(0,"title", "contnet","date",false,6,6),
                FeedData(1,"title1", "contnet1","date1",false,7,7),
                FeedData(2,"title2", "contnet2","date2",false,8,8)
            )
        )

        feedAdapter.notifyDataSetChanged()
        return binding.root
        //return inflater.inflate(R.layout.fragment_feed, container, false)
    }

}