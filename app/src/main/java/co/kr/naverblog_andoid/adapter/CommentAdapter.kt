package co.kr.naverblog_andoid.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.naverblog_andoid.data.CommentData
import co.kr.naverblog_andoid.databinding.ItemCommentBinding
import com.bumptech.glide.Glide

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    val itemList = mutableListOf<CommentData>()

    class CommentViewHolder(
        private val binding: ItemCommentBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun onBind(data: CommentData) {
            Glide.with(binding.imageviewCommentProfile.context)
                .load(data.imageUrl)
                .into(binding.imageviewCommentProfile)

            binding.textviewCommentUsername.text = data.username
            binding.textviewCommentText.text = data.text
            binding.textviewCommentHeartCount.text = data.heartCount.toString()
            binding.imageviewCommentHeart.isSelected = data.isHeartPressed
            binding.textviewCommentDate.text = "${data.date} ${data.time}"
            binding.textviewCommentFeedbackVisibility.text = "답글 ${data.feedbackCount}개 보기"

            // recyclerview 처리 필요
            val adapter = CommentFeedbackAdapter()
            adapter.itemList.addAll(data.feedbackList)
            binding.recyclerviewCommentFeedback.adapter = adapter
            adapter.notifyDataSetChanged()

            when(data.isOwner) {
                true -> binding.imageviewCommentOwner.visibility = View.VISIBLE
                false -> binding.imageviewCommentOwner.visibility = View.GONE
            }

            binding.constraintlayoutCommentHeart.setOnClickListener {
                var heartCount = Integer.parseInt(binding.textviewCommentHeartCount.text as String)
                with(binding.imageviewCommentHeart) {
                    this.isSelected = !this.isSelected
                    if(this.isSelected) {
                        heartCount+=1
                    } else {
                        heartCount-=1
                    }
                }
                binding.textviewCommentHeartCount.text = heartCount.toString()
            }

            binding.textviewCommentFeedbackVisibility.setOnClickListener {
                if(binding.recyclerviewCommentFeedback.visibility == View.GONE) {
                    binding.textviewCommentFeedbackVisibility.text = "답글 숨기기"
                    binding.recyclerviewCommentFeedback.visibility = View.VISIBLE
                } else if(binding.recyclerviewCommentFeedback.visibility == View.VISIBLE) {
                    binding.textviewCommentFeedbackVisibility.text = "답글 ${data.feedbackCount}개 보기"
                    binding.recyclerviewCommentFeedback.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}