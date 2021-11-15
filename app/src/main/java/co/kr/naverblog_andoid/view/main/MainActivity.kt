package co.kr.naverblog_andoid.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import co.kr.naverblog_andoid.R
import co.kr.naverblog_andoid.adapter.MainViewPagerAdapter
import co.kr.naverblog_andoid.databinding.ActivityMainBinding
import co.kr.naverblog_andoid.view.main.alarm.AlarmFragment
import co.kr.naverblog_andoid.view.main.feed.FeedFragment
import co.kr.naverblog_andoid.view.main.profile.ProfileFragment
import co.kr.naverblog_andoid.view.main.recommend.RecommendFragment
import co.kr.naverblog_andoid.view.main.write.WriteFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: MainViewPagerAdapter
    private val feedFragment: FeedFragment by lazy { FeedFragment() }
    private val recommendFragment: RecommendFragment by lazy { RecommendFragment() }
    private val writeFragment: WriteFragment by lazy { WriteFragment() }
    private val alarmFragment: AlarmFragment by lazy { AlarmFragment() }
    private val profileFragment: ProfileFragment by lazy { ProfileFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPagerAdapter()
        initBottomNavigation()
    }

    private fun initViewPagerAdapter() {
        val fragmentList = listOf(feedFragment, recommendFragment, writeFragment, alarmFragment, profileFragment)
        viewPagerAdapter = MainViewPagerAdapter(this)
        viewPagerAdapter.fragmentList.addAll(fragmentList)
        binding.viewpagerMain.adapter = viewPagerAdapter
    }

    private fun initBottomNavigation() {
        binding.viewpagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNavigationMain.menu.getItem(position).isChecked = true
            }
        })

        binding.bottomNavigationMain.setOnItemSelectedListener {
            binding.viewpagerMain.currentItem = when(it.itemId) {
                R.id.menu_feed -> FEED_FRAGMENT
                R.id.menu_recommend -> RECOMMEND_FRAGMENT
                R.id.menu_write -> WRITE_FRAGMENT
                R.id.menu_alarm -> ALARM_FRAGMENT
                else -> PROFILE_FRAGMENT
            }
            return@setOnItemSelectedListener true
        }

        binding.imageviewMainWrite.setOnClickListener {
            binding.viewpagerMain.currentItem = WRITE_FRAGMENT
        }

        binding.bottomNavigationMain.itemIconTintList = null
    }

    private companion object {
        const val FEED_FRAGMENT = 0
        const val RECOMMEND_FRAGMENT = 1
        const val WRITE_FRAGMENT = 2
        const val ALARM_FRAGMENT = 3
        const val PROFILE_FRAGMENT = 4
    }
}