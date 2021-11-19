package co.kr.naverblog_andoid.view.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.databinding.ItemRecommentBinding
import com.bumptech.glide.Glide

class RecommentAdapter(): RecyclerView.Adapter<RecommentAdapter.RecommentViewHolder>() {
    val recomment_userList = mutableListOf<RecommentData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommentAdapter.RecommentViewHolder {
        val binding = ItemRecommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return RecommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommentViewHolder, position: Int) {
        holder.onBind(recomment_userList[position])

    }

    override fun getItemCount(): Int {
        return recomment_userList.size
    }


    class RecommentViewHolder(private val binding: ItemRecommentBinding)
        : RecyclerView.ViewHolder(binding.root)  {
        fun onBind(data : RecommentData){

            Glide.with(binding.imageViewIcTagColor.context)
                .load(data.imageUrl)
                .into(binding.imageViewIcTagColor)

            binding.textViewHeartCount.text =  data.heartCount.toString()// 좋아요 개수
            binding.imageButtonReHeart.isSelected = data.isHeartClick //좋아요 버튼
            binding.textViewReDateTime.text = "${data.date}  ${data.time}" // 날짜
            binding.textViewReComment.text = data.comment // 댓글 내용
            binding.textViewReName.text = data.name

            binding.imageButtonReHeart.setOnClickListener{
                var heartCount = Integer.parseInt(binding.textViewHeartCount.text as String)
                with(binding.imageButtonReHeart){
                    this.isSelected = !this.isSelected

                    if (this.isSelected){
                        heartCount += 1
                        binding.imageButtonReHeart.setImageResource(R.drawable.ic_bigheart_selected)
                    }else {
                        heartCount -= 1
                        binding.imageButtonReHeart.setImageResource(R.drawable.ic_bigheart_default)
                    }
                }
                binding.textViewHeartCount.text = heartCount.toString()
            }

        }
    }
}

