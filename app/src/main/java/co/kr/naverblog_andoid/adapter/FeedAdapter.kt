package co.kr.naverblog_andoid.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.api.ApiService
import co.kr.naverblog_andoid.data.feed.main.Post
import co.kr.naverblog_andoid.databinding.ItemFeedBoardBinding
import co.kr.naverblog_andoid.util.enqueueUtil
import com.bumptech.glide.Glide

class FeedAdapter(private val itemClick: (Post) -> Unit) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    val itemList = mutableListOf<Post>()

    class FeedViewHolder(
        private val binding: ItemFeedBoardBinding,
        val itemClick: (Post) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Post) {
            // // 서버에서 받아온 데이터 bind 하는 과정
            Glide.with(binding.imageviewFeedBoardThumbnail)
                .load(R.drawable.img_post)
                .into(binding.imageviewFeedBoardThumbnail)
            binding.textviewFeedBoardTitle.text = data.title
            binding.textviewFeedBoardText.text = data.content
            binding.textviewFeedBoardDate.text = data.createdAt
            binding.textviewFeedBoardHeartCount.text = data.heartNum.toString()
            binding.textviewFeedBoardCommentCount.text = data.commentNum
            binding.constraintlayoutFeedHeart.isSelected = data.isLike

            // util 함수 사용한 서버통신(좋아요)
            binding.constraintlayoutFeedHeart.setOnClickListener {
                val call = ApiService.feedService.patchLike(
                    data.postId.toString(),
                    state = data.isLike
                )

                call.enqueueUtil(
                    onSuccess = {
                        var heartCount =
                            Integer.parseInt(binding.textviewFeedBoardHeartCount.text as String)
                        binding.constraintlayoutFeedHeart.apply {
                            this.isSelected = !this.isSelected
                            if (this.isSelected) {
                                heartCount += 1
                            } else {
                                heartCount -= 1
                            }
                            binding.textviewFeedBoardHeartCount.text = heartCount.toString()
                        }
                    },
                    onError = {
                        Log.d("PatchLike", "failed")
                    }
                )
            }

            binding.root.setOnClickListener {
                itemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding =
            ItemFeedBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}