package co.kr.naverblog_andoid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.databinding.ItemCommentBinding
import co.kr.naverblog_andoid.data.CommentData
import com.bumptech.glide.Glide
class CommentAdapter() : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){
    val userList = mutableListOf<CommentData>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return CommentViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int{
        return userList.size
    }

    class CommentViewHolder(private val binding: ItemCommentBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun onBind(data : CommentData){
            Glide.with(binding.imageViewIcColorTag.context)
                .load(data.imageUrl)
                .override(40,40)
                .into(binding.imageViewIcColorTag)

            binding.textViewName.text = data.name
            binding.textViewComment.text = data.comment
            binding.imageButtonHeart.isSelected = data.isHeartClick
            binding.textViewDateTime.text = "${data.date} ${data.time} "
            binding.textViewHeartCounter.text = data.heartCount.toString()
            binding.textViewFoldUnfoldRecomment.text = "답글 ${data.recommentCount}개 보기 "

            val adapter = RecommentAdapter()
            adapter.recomment_userList.addAll(data.recommentList)
            binding.recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()

            when(data.isOwner){
                true -> binding.imageViewIsOwner.visibility = View.VISIBLE
                false -> binding.imageViewIsOwner.visibility = View.GONE
            }


            binding.imageButtonHeart.setOnClickListener{
                var heartCount = Integer.parseInt(binding.textViewHeartCounter.text as String)
                with(binding.imageButtonHeart){
                    this.isSelected = !isSelected

                    if (this.isSelected) {
                        heartCount += 1
                    } else {
                        heartCount -= 1
                    }
                }
                binding.textViewHeartCounter.text = heartCount.toString()
            }

            binding.textViewFoldUnfoldRecomment.setOnClickListener{
                if(binding.recyclerView.visibility == View.GONE){
                    binding.textViewFoldUnfoldRecomment.text = "답글 숨기기"
                    binding.recyclerView.visibility = View.VISIBLE
                }else if (binding.recyclerView.visibility == View.VISIBLE){
                    binding.textViewFoldUnfoldRecomment.text = "답글 ${data.recommentCount}개 보기"
                    binding.recyclerView.visibility = View.GONE
                }
            }
        }
    }
}

