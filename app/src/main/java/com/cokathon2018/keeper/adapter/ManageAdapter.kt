package com.narsha2018.usicmusic.adapter

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cokathon2018.keeper.R
import com.cokathon2018.keeper.`interface`.OnClickListener
import kotlinx.android.synthetic.main.item_manage.view.*
import java.util.*

/**
 * Created by hyunjin on 2018. 5. 11..
 */
class ManageAdapter(private var mItems: ArrayList<ManageItem>, context: Context, private var listener: OnClickListener) : RecyclerView.Adapter<ManageAdapter.ItemViewHolder>() {

    private val contexts: Context = context
    var id: String? = null
    // 새로운 뷰 홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_manage, parent, false)
        val sharedPreferences: SharedPreferences = contexts.getSharedPreferences("user", Activity.MODE_PRIVATE)
        id = sharedPreferences.getString("id", null)

        return ItemViewHolder(view)
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.name.text = mItems[position].name
        holder.recent.text = mItems[position].recent
        holder.itemView.setOnClickListener { listener.onClick(position) }
    }

    // 데이터 셋의 크기를 리턴
    override fun getItemCount(): Int {
        return mItems.size
    }

    // 커스텀 뷰홀더
    // binding widgets on item layout
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
        val recent: TextView = itemView.recent
    }
}

class ManageItem(val name: String, val recent: String)