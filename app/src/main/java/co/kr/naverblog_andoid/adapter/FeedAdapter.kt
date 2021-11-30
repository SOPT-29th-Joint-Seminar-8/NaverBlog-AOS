package co.kr.naverblog_andoid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.data.FeedData
import co.kr.naverblog_andoid.data.feed.main.Post
import co.kr.naverblog_andoid.databinding.ItemFeedBoardBinding
import com.bumptech.glide.Glide

class FeedAdapter(private val itemClick: (Post) -> Unit) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    val itemList = mutableListOf<Post>()

    class FeedViewHolder(
        private val binding: ItemFeedBoardBinding,
        val itemClick: (Post) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Post) {
            Glide.with(binding.imageviewFeedBoardThumbnail)
                .load(R.drawable.img_post)
                .into(binding.imageviewFeedBoardThumbnail)
            binding.textviewFeedBoardTitle.text = data.title
            binding.textviewFeedBoardText.text = data.content
            binding.textviewFeedBoardDate.text = data.createdAt
            binding.textviewFeedBoardHeartCount.text = data.heartNum.toString()
            binding.textviewFeedBoardCommentCount.text = data.commentNum
            binding.constraintlayoutFeedHeart.isSelected = data.isLike

            binding.constraintlayoutFeedHeart.setOnClickListener {
                var heartCount = Integer.parseInt(binding.textviewFeedBoardHeartCount.text as String)
                it.isSelected = !it.isSelected
                if(it.isSelected) {
                    heartCount += 1
                } else {
                    heartCount -= 1
                }
                binding.textviewFeedBoardHeartCount.text = heartCount.toString()
            }

            binding.root.setOnClickListener {
                itemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = ItemFeedBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}