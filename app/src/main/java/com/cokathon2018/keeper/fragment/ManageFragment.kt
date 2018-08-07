package com.cokathon2018.keeper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cokathon2018.keeper.util.PreferencesUtils
import com.cokathon2018.keeper.R
import com.cokathon2018.keeper.`interface`.OnClickListener
import com.cokathon2018.keeper.adapter.ManageAdapter
import com.cokathon2018.keeper.adapter.ManageItem
import kotlinx.android.synthetic.main.fragment_manage.*
import kotlinx.android.synthetic.main.fragment_manage.view.*

class ManageFragment : Fragment(), OnClickListener{

    override fun onClick(pos: Int) {

    }
    private var adapter : ManageAdapter? = null
    private var mItems = ArrayList<ManageItem>()
    private var v : View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_manage, container, false)

        initRecyclerView()
        v!!.swiperefresh.setOnRefreshListener {
            val strUsrlist = PreferencesUtils(activity!!).getData("user")
            val usrlist = strUsrlist.split(",")
            if(strUsrlist.isNotEmpty()) {
                mItems.clear()
                for (usr: String in usrlist) {
                    mItems.add(ManageItem(usr, "18.8.7 18:15"))
                }
                adapter!!.notifyDataSetChanged()
            }
            v!!.swiperefresh.isRefreshing = false
        }
        val strUsrlist = PreferencesUtils(activity!!).getData("user")
        val usrlist = strUsrlist.split(",")
        if(strUsrlist.isNotEmpty()) {
            mItems.clear()
            for (usr: String in usrlist) {
                mItems.add(ManageItem(usr, "18.8.7 18:15"))
            }
            adapter!!.notifyDataSetChanged()
        }
        return v
    }
    private fun initRecyclerView() {
        // 변경될 가능성 o : false 로 , 없다면 true.
        v!!.recyclerView.setHasFixedSize(false)

        adapter = ManageAdapter(mItems, this)
        v!!.recyclerView.adapter = adapter
        v!!.recyclerView.layoutManager = LinearLayoutManager(activity!!)
    }
}