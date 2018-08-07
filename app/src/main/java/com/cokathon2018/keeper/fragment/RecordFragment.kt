package com.cokathon2018.keeper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cokathon2018.keeper.R
import com.cokathon2018.keeper.`interface`.OnClickListener
import com.cokathon2018.keeper.adapter.RecordAdapter
import com.cokathon2018.keeper.adapter.RecordItem
import com.cokathon2018.keeper.util.FuelUtils
import com.cokathon2018.keeper.util.PreferencesUtils
import kotlinx.android.synthetic.main.fragment_manage.view.*
import kotlinx.android.synthetic.main.fragment_record.*
import org.json.JSONArray

class RecordFragment : Fragment(), OnClickListener {

    override fun onClick(pos: Int) {

    }

    private var adapter: RecordAdapter? = null
    private var mItems = ArrayList<RecordItem>()
    private var v: View? = null
    private var fuelUtils: FuelUtils? = null
    private var userCount: Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_record, container, false)
        fuelUtils = FuelUtils(activity!!)
        initRecyclerView()
        v!!.swiperefresh.setOnRefreshListener {
            val strUsrlist = PreferencesUtils(activity!!).getData("user")
            val usrlist = strUsrlist.split(",")
            if (strUsrlist.isNotEmpty()) {
                mItems.clear()
                userCount = strUsrlist.length
                for (usr: String in usrlist) {
                    fuelUtils!!.getOutDate(usr)
                }
            }
            v!!.swiperefresh.isRefreshing = false
        }

        val strUsrlist = PreferencesUtils(activity!!).getData("user")
        val usrlist = strUsrlist.split(",")


        if (strUsrlist.isNotEmpty()) {
            mItems.clear()
            userCount = strUsrlist.length
            for (usr: String in usrlist) {
                fuelUtils!!.getOutDate(usr)
            }
        }

        return v
    }

    private fun initRecyclerView() {
        // 변경될 가능성 o : false 로 , 없다면 true.
        v!!.recyclerView.setHasFixedSize(false)

        adapter = RecordAdapter(mItems, this)
        v!!.recyclerView.adapter = adapter
        v!!.recyclerView.layoutManager = LinearLayoutManager(activity!!)
    }

    fun notifyFinish(result: String) {
        val arr = JSONArray(result)
        val length = arr.length()

        if (length != 0)
            for (i in 0 until length) {
                arr.getJSONObject(i).let {
                    if (it.getString("username") == "error")
                        return
                    mItems.add(RecordItem(it.getInt("idx"), it.getInt("isout"), it.getString("username"), it.getString("time").substring(0,16)))
                }
                //is out name recent

            }
        adapter!!.notifyDataSetChanged()
    }
}
