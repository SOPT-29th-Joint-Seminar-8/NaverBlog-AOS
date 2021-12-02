package co.kr.naverblog_andoid.view.comment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import co.kr.naverblog_andoid.adapter.CommentAdapter
import co.kr.naverblog_andoid.api.ApiService
import co.kr.naverblog_andoid.data.comment.write.RequestPostData
import co.kr.naverblog_andoid.databinding.ActivityCommentBinding
import co.kr.naverblog_andoid.util.enqueueUtil
import java.lang.Integer.parseInt

class CommentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommentBinding
    private var groupId = -1
    private val adapter by lazy {
        CommentAdapter() {
            // itemClick 구현 및 전달
            groupId = it
            openKeyBoard()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postId: String = intent.getIntExtra("postId", 0).toString()

        initRecyclerView()
        initNetwork(postId)
        imageBackOnClickEvent()
        commentTextWatcher()
        goUpScrollView()
        postComment(postId)
    }

    private fun initRecyclerView() {
        binding.recyclerviewComment.adapter = adapter
    }

    // util 함수 사용한 서버통신(댓글 불러오기)
    @SuppressLint("NotifyDataSetChanged")
    private fun initNetwork(postId: String) {
        val call = ApiService.commentService.getComment(postId)

        call.enqueueUtil(
            onSuccess = {
                // CommentCount
                binding.textviewCommentCount.text = it.data.commentNum.toString()
                // SecretCommentCount
                binding.textviewCommentSecretCount.text = it.data.secretCommentNum.toString()
                // List<Comment>
                adapter.itemList.addAll(it.data.comments)
                adapter.notifyDataSetChanged()
            },
            onError = {
                Log.d("getComment", "failed")
            }
        )
    }

    // 좌상단 back icon 클릭 시 FeedFragment로 이동
    private fun imageBackOnClickEvent() {
        binding.imageviewCommentBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    // 댓글 입력 창에 텍스트가 있는 경우 버튼 초록색으로 만들어줌
    private fun commentTextWatcher() {
        binding.edittextComment.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                with(binding.buttonCommentPost) {
                    when (s?.length) {
                        0 -> this.isEnabled = false
                        else -> this.isEnabled = true
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                with(binding.buttonCommentPost) {
                    when (s?.length) {
                        0 -> this.isEnabled = false
                        else -> this.isEnabled = true
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                with(binding.buttonCommentPost) {
                    when (s?.length) {
                        0 -> this.isEnabled = false
                        else -> this.isEnabled = true
                    }
                }
            }
        })
    }

    // 맨 위로 아이콘 클릭 시 댓글 최상단으로 이동
    private fun goUpScrollView() {
        binding.buttonCommentGoup.setOnClickListener {
            binding.nestedscrollviewComment.scrollTo(0, 0)
        }
    }

    // util 함수 사용한 서버통신(댓글 달기)
    @SuppressLint("NotifyDataSetChanged")
    private fun postComment(postId: String) {
        binding.buttonCommentPost.setOnClickListener {
            if (!binding.edittextComment.text.isNullOrBlank()) {
                val content = binding.edittextComment.text.toString()
                val call = ApiService.commentService.postComment(
                    RequestPostData(
                        parseInt(postId),
                        "Android Team",
                        content,
                        groupId
                    )
                )

                call.enqueueUtil(
                    onSuccess = {
                        Toast.makeText(this@CommentActivity, "댓글 작성 완료", Toast.LENGTH_SHORT).show()
                        binding.textviewCommentCount.text = it.data.commentNum.toString()
                        adapter.itemList.clear()
                        initNetwork(postId)
                    },
                    onError = {
                        Log.d("PostComment", "failed")
                    }
                )
            }
            closeKeyboard()
            binding.edittextComment.text = null
        }
    }

    // 댓글 달기 버튼 클릭 시 키보드를 닫아주는 함수
    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val inputMethodManager: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    // 답글 달기 클릭 시 키보드가 자동으로 띄워지지 않기 때문에 인위적으로 키보드를 띄워주는 함수
    private fun openKeyBoard() {
        binding.edittextComment.requestFocus()
        val inputMethodManager: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}