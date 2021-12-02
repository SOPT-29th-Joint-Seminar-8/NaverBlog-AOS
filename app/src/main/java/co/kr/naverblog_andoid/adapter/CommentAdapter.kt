package co.kr.naverblog_andoid.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.api.ApiService
import co.kr.naverblog_andoid.data.comment.comment.Comment
import co.kr.naverblog_andoid.databinding.ItemCommentBinding
import co.kr.naverblog_andoid.util.enqueueUtil
import com.bumptech.glide.Glide

class CommentAdapter(private val itemClick: (Int) -> Unit)
    : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    val itemList = mutableListOf<Comment>()

    class CommentViewHolder(
        private val binding: ItemCommentBinding,
        val itemClick: (Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun onBind(data: Comment) {
            Glide.with(binding.imageviewCommentProfile)
                .load(R.drawable.ic_color_tag)
                .into(binding.imageviewCommentProfile)

            binding.textviewCommentUsername.text = data.userName
            binding.textviewCommentText.text = data.content
            binding.textviewCommentHeartCount.text = data.heartNum.toString()
            binding.imageviewCommentHeart.isSelected = data.isLike
            binding.textviewCommentDate.text = data.createdAt
            binding.textviewCommentFeedbackVisibility.text = "답글 ${data.reply.size}개 보기"

            // recyclerview 처리 필요
            val adapter = CommentFeedbackAdapter()
            adapter.itemList.addAll(data.reply)
            binding.recyclerviewCommentFeedback.adapter = adapter
            adapter.notifyDataSetChanged()

            when(data.isOwner) {
                true -> binding.imageviewCommentOwner.visibility = View.VISIBLE
                false -> binding.imageviewCommentOwner.visibility = View.GONE
            }

            binding.constraintlayoutCommentHeart.setOnClickListener {
                val call = ApiService.commentService.patchLike(data.commentId.toString(), data.isLike)

                call.enqueueUtil(
                    onSuccess = {
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
                    },
                    onError = {
                        Log.d("Comment PatchLike", "failed")
                    }
                )
            }

            binding.textviewCommentFeedbackVisibility.setOnClickListener {
                if(data.reply.size != 0) {
                    if (binding.recyclerviewCommentFeedback.visibility == View.GONE) {
                        binding.textviewCommentFeedbackVisibility.text = "답글 숨기기"
                        binding.recyclerviewCommentFeedback.visibility = View.VISIBLE
                    } else if (binding.recyclerviewCommentFeedback.visibility == View.VISIBLE) {
                        binding.textviewCommentFeedbackVisibility.text =
                            "답글 ${data.reply.size}개 보기"
                        binding.recyclerviewCommentFeedback.visibility = View.GONE
                    }
                }
            }

            // 답글 달기
            binding.textviewCommentFeedback.setOnClickListener {
                itemClick(data.groupId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}