package co.kr.naverblog_andoid.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.api.ApiService
import co.kr.naverblog_andoid.data.comment.comment.Reply
import co.kr.naverblog_andoid.databinding.ItemCommentFeedbackBinding
import co.kr.naverblog_andoid.util.enqueueUtil
import com.bumptech.glide.Glide

class CommentFeedbackAdapter: RecyclerView.Adapter<CommentFeedbackAdapter.CommentFeedbackViewHolder>() {
    val itemList = mutableListOf<Reply>()

    class CommentFeedbackViewHolder(private val binding: ItemCommentFeedbackBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(data: Reply) {
            Glide.with(binding.imageviewCommentFeedbackProfile)
                .load(R.drawable.ic_color_tag)
                .into(binding.imageviewCommentFeedbackProfile)

            binding.textviewCommentFeedbackUsername.text = data.userName
            binding.textviewCommentFeedbackText.text = data.content
            binding.textviewCommentFeedbackHeartCount.text = data.heartNum.toString()
            binding.imageviewCommentFeedbackHeart.isSelected = data.isLike
            binding.textviewCommentFeedbackDate.text = data.createdAt

            when(data.isOwner) {
                true -> binding.imageviewCommentFeedbackOwner.visibility = View.VISIBLE
                false -> binding.imageviewCommentFeedbackOwner.visibility = View.GONE
            }

            binding.constraintlayoutCommentFeedbackHeart.setOnClickListener {
                val call = ApiService.commentService.patchLike(data.commentId.toString(), data.isLike)

                call.enqueueUtil(
                    onSuccess = {
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
                    },
                    onError = {
                        Log.d("Reply PatchLike", "failed")
                    }
                )
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