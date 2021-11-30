package co.kr.naverblog_andoid.view.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.adapter.CommentAdapter
import co.kr.naverblog_andoid.data.CommentData
import co.kr.naverblog_andoid.data.RecommentData
import co.kr.naverblog_andoid.databinding.ActivityCommentBinding
class CommentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCommentBinding
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        registerButton()
        goBack()

        goUp()

    }
    // go uo

    private fun goUp(){
        binding.imageViewGoUp.setOnClickListener {
            binding.nestedScrollView.scrollTo(0, 0)
        }
    }

    // 뒤로가기 버튼

    private fun goBack(){
        binding.imageViewGoBack.setOnClickListener(){
            super.onBackPressed()
        }
    }

    // 답글 입력 버튼
    private fun registerButton(){
        binding.editTextWriteComment.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding.editTextWriteComment.length() > 0){
                    binding.buttonCommentRegister.isEnabled = true
                    binding.buttonCommentRegister.isClickable = true
                }else {
                    binding.buttonCommentRegister.isEnabled = false
                    binding.buttonCommentRegister.isClickable = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (binding.editTextWriteComment.length() > 0){
                    binding.buttonCommentRegister.isEnabled = true
                    binding.buttonCommentRegister.isClickable = true
                }else {
                    binding.buttonCommentRegister.isEnabled = false
                    binding.buttonCommentRegister.isClickable = false
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.editTextWriteComment.length() > 0){
                    binding.buttonCommentRegister.isEnabled = true
                    binding.buttonCommentRegister.isClickable = true
                }else {
                    binding.buttonCommentRegister.isEnabled = false
                    binding.buttonCommentRegister.isClickable = false
                }
            }
        })
    }


    private fun initAdapter() {
        commentAdapter = CommentAdapter()
        binding.recyclerViewComment.adapter = commentAdapter


        commentAdapter.userList.addAll(

            listOf(
                CommentData(
                    0,
                    "슈카",
                    R.drawable.ic_color_tag,
                    "하락장입니다 여러분 !",
                    true,
                    "2020,05,17",
                    "12:12",
                    false,
                    0,
                    0,
                    listOf()
                ),
                CommentData(
                    0,
                    "슈카",
                    R.drawable.ic_color_tag,
                    "하락장입니다 여러분 !",
                    true,
                    "2020,05,17",
                    "12:12",
                    true,
                    3,
                    0,
                    listOf()
                ),
                CommentData(
                    0,
                    "슈카",
                    R.drawable.ic_color_tag,
                    "하락장입니다 여러분 !",
                    true,
                    "2020,05,17",
                    "12:12",
                    true,
                    4,
                    1,
                    listOf(
                        RecommentData(
                            0,
                            "gogo",
                            R.drawable.ic_color_tag,
                            "gazuaaa",
                            false,
                            "2020,05,17",
                            "12:20",
                            false,
                            0,
                        )
                    )
                )
            )
        )

        commentAdapter.notifyDataSetChanged()
    }
}