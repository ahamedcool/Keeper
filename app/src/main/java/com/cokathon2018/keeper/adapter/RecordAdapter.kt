package com.cokathon2018.keeper.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cokathon2018.keeper.R
import com.cokathon2018.keeper.`interface`.OnClickListener
import kotlinx.android.synthetic.main.item_record.view.*
import org.jetbrains.anko.backgroundColor
import java.util.*

/**
 * Created by hyunjin on 2018. 5. 11..
 */
class RecordAdapter(private var mItems: ArrayList<RecordItem>, private var listener: OnClickListener) : RecyclerView.Adapter<RecordAdapter.ItemViewHolder>() {

    var id: String? = null
    // 새로운 뷰 홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false)
        return ItemViewHolder(view)
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if(mItems[position].isOut==0)
            holder.tag.backgroundColor = Color.RED
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
        val tag: View = itemView.colortag
        val name: TextView = itemView.name
        val recent: TextView = itemView.time
    }
}

class RecordItem(val idx: Int, val isOut: Int, val name: String, val recent: String)