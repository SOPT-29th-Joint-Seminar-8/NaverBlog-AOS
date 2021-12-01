package co.kr.naverblog_andoid.view.main.feed

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import co.kr.naverblog_andoid.adapter.FeedAdapter
import co.kr.naverblog_andoid.api.ApiService
import co.kr.naverblog_andoid.databinding.FragmentFeedBinding
import co.kr.naverblog_andoid.util.enqueueUtil
import co.kr.naverblog_andoid.view.comment.CommentActivity
import com.bumptech.glide.Glide

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
    ): View {
        _binding = FragmentFeedBinding.inflate(layoutInflater, container, false)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNetwork()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView() {
        binding.recyclerviewFeedBoard.adapter = adapter
        binding.recyclerviewFeedBoard.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initNetwork() {
        val call = ApiService.feedService.getMain()

        call.enqueueUtil(
            onSuccess = {
                // Banner
                it.data.banner.elementAt(0).apply {
                    Glide.with(binding.imageviewFeedMain)
                        .load(this.bannerImage)
                        .into(binding.imageviewFeedMain)
                    binding.textviewFeedTodayVisitorCount.text = this.todayCount.toString()
                    binding.textviewFeedTotalVisitorCount.text = this.totalCount.toString()
                    binding.textviewFeedBlogName.text = this.blogName
                    binding.textviewFeedOwner.text = this.profileName
                    binding.textviewFeedCategory.text = this.blogCategory
                    binding.textviewFeedNeighbor.text = this.neighborNum.toString()
                }

                // Post
                adapter.itemList.addAll(it.data.posts)
                adapter.notifyDataSetChanged()
            },
            onError = {
                Log.d("GetMain", "Failed")
            }
        )
    }
}