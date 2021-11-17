package co.kr.naverblog_andoid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.data.FeedData
import co.kr.naverblog_andoid.databinding.ItemFeedListBinding
import com.bumptech.glide.Glide

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>(){
    val feedList = mutableListOf<FeedData>()

    class FeedViewHolder(private val binding: ItemFeedListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FeedData) {

            Glide.with(binding.imageviewFeedListMainImage.context)
                .load(R.drawable.img_post)
                .into(binding.imageviewFeedListMainImage)

            binding.apply {
                textviewFeedListTitle.text = data.title
                textviewFeedListContent.text = data.content
                textviewFeedListDate.text = data.date
                imageviewFeedListHeart.isSelected = data.isHeartPressed
                textviewFeedListHeartNum.text = data.heartNum.toString()
                textviewFeedListCommentNum.text = data.commentNum.toString()

                constraintlayoutFeedHeart.setOnClickListener {
                    val parseInt = data.heartNum
                    if(imageviewFeedListHeart.isSelected == false) {
                        imageviewFeedListHeart.isSelected = true
                        textviewFeedListHeartNum.text = (parseInt+1).toString()
                    }
                    else {
                        imageviewFeedListHeart.isSelected = false
                        textviewFeedListHeartNum.text = parseInt.toString()
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = ItemFeedListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.onBind(feedList[position])
    }

    override fun getItemCount(): Int = feedList.size
}