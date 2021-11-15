package co.kr.naverblog_andoid.view.main.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.kr.naverblog_andoid.R

class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 혜빈 작업공간
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }
}