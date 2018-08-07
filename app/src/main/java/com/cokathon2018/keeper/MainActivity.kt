package com.cokathon2018.keeper

import com.cokathon2018.keeper.adapter.ViewPagerAdapter
import com.cokathon2018.keeper.fragment.AddFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.cokathon2018.keeper.fragment.ManageFragment
import com.cokathon2018.keeper.fragment.RecordFragment
import com.cokathon2018.keeper.util.PreferencesUtils

class MainActivity : BaseActivity() {
    override var viewId: Int = R.layout.activity_main
    override var toolbarId: Int? = R.id.toolbar

    private val manageFragment = ManageFragment()
    private val addFragment = AddFragment()
    val recordFragment = RecordFragment()

    override fun onCreate() {
        initViewPager()
        //addDummyData() // 1회호출만해도됨
        bottom_navigation.selectedItemId = R.id.manage
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.record -> {
                    viewpager.currentItem = 0
                }
                R.id.manage -> {
                    viewpager.currentItem = 1
                }
                R.id.add -> {
                    viewpager.currentItem = 2
                }
            }
            true
        }
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(recordFragment)
        adapter.addFragment(manageFragment)
        adapter.addFragment(addFragment)
        viewpager.adapter = adapter
        viewpager.currentItem = 1
    }

    private fun addDummyData(){
        PreferencesUtils(this).saveData("user", "user1,user2,user3")
        PreferencesUtils(this).saveData("user1", "user1,user2,user3")
        PreferencesUtils(this).saveData("user2", "user1,user2,user3")
        PreferencesUtils(this).saveData("user3", "user1,user2,user3")
    }
}