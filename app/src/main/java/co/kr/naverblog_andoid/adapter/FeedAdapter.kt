package co.kr.naverblog_andoid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.naverblog_andoid.data.FeedData
import co.kr.naverblog_andoid.databinding.ItemFeedBoardBinding
import com.bumptech.glide.Glide

class FeedAdapter(val itemClick: (FeedData) -> Unit) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    val itemList = mutableListOf<FeedData>()

    class FeedViewHolder(
        private val binding: ItemFeedBoardBinding,
        val itemClick: (FeedData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FeedData) {
            Glide.with(binding.imageviewFeedBoardThumbnail.context)
                .load(data.imageUrl)
                .into(binding.imageviewFeedBoardThumbnail)

            binding.textviewFeedBoardTitle.text = data.title
            binding.textviewFeedBoardText.text = data.text
            binding.textviewFeedBoardDate.text = data.date
            binding.textviewFeedBoardHeartCount.text = data.heartCount.toString()
            binding.textviewFeedBoardCommentCount.text = data.commentCount.toString()
            binding.constraintlayoutFeedHeart.isSelected = data.isHeartPressed

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