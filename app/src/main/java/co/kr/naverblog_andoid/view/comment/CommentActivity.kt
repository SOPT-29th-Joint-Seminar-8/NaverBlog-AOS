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
            groupId = it.groupId
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

    private fun imageBackOnClickEvent() {
        binding.imageviewCommentBack.setOnClickListener {
            super.onBackPressed()
        }
    }

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

    private fun goUpScrollView() {
        binding.buttonCommentGoup.setOnClickListener {
            binding.nestedscrollviewComment.scrollTo(0, 0)
        }
    }

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

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val inputMethodManager: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun openKeyBoard() {
        binding.edittextComment.requestFocus()
        val inputMethodManager: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}