package co.kr.naverblog_andoid.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.naverblog_andoid.data.CommentFeedbackData
import co.kr.naverblog_andoid.databinding.ItemCommentFeedbackBinding
import com.bumptech.glide.Glide

class CommentFeedbackAdapter: RecyclerView.Adapter<CommentFeedbackAdapter.CommentFeedbackViewHolder>() {
    val itemList = mutableListOf<CommentFeedbackData>()

    class CommentFeedbackViewHolder(private val binding: ItemCommentFeedbackBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(data: CommentFeedbackData) {
            Glide.with(binding.imageviewCommentFeedbackProfile.context)
                .load(data.imageUrl)
                .into(binding.imageviewCommentFeedbackProfile)

            binding.textviewCommentFeedbackUsername.text = data.username
            binding.textviewCommentFeedbackText.text = data.text
            binding.textviewCommentFeedbackHeartCount.text = data.heartCount.toString()
            binding.imageviewCommentFeedbackHeart.isSelected = data.isHeartPressed
            binding.textviewCommentFeedbackDate.text = "${data.date} ${data.time}"

            when(data.isOwner) {
                true -> binding.imageviewCommentFeedbackOwner.visibility = View.VISIBLE
                false -> binding.imageviewCommentFeedbackOwner.visibility = View.GONE
            }

            binding.constraintlayoutCommentFeedbackHeart.setOnClickListener {
                var heartCount = Integer.parseInt(binding.textviewCommentFeedbackHeartCount.text as String)
                with(binding.imageviewCommentFeedbackHeart) {
                    this.isSelected = !this.isSelected
                    if(this.isSelected) {
                        heartCount+=1
                    } else {
                        heartCount-=1
                    }
                }
                binding.textviewCommentFeedbackHeartCount.text = heartCount.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentFeedbackViewHolder {
        val binding = ItemCommentFeedbackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentFeedbackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentFeedbackViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}