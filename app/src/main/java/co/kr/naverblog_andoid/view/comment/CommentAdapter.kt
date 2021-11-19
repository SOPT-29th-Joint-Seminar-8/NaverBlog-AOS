package co.kr.naverblog_andoid.view.comment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.databinding.ActivityCommentBinding
import co.kr.naverblog_andoid.databinding.ItemCommentBinding

class CommentAdapter() : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){
   val userList = mutableListOf<CommentData>()


   override fun onCreateViewHolder(
       parent: ViewGroup,
       viewType: Int
   ): CommentAdapter.CommentViewHolder {
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

           binding.imageButtonHeart.setOnClickListener{
               var heartCount = Integer.parseInt(binding.textViewHeartCounter.text as String)
               with(binding.imageButtonHeart){
                   this.isSelected = !this.isSelected

                   if (this.isSelected){
                       heartCount += 1
                       binding.imageButtonHeart.setImageResource(R.drawable.ic_bigheart_selected)
                   }else {
                       heartCount -= 1
                       binding.imageButtonHeart.setImageResource(R.drawable.ic_bigheart_default)
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

