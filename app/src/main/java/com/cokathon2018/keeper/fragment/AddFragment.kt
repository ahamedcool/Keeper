package com.cokathon2018.keeper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cokathon2018.keeper.R
import com.cokathon2018.keeper.util.PreferencesUtils
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class AddFragment : Fragment(){
    private var v : View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_add, container, false)
        v!!.search.onClick { cardView.visibility = View.VISIBLE; name.text = name_et.text.toString() }

        v!!.add.onClick { PreferencesUtils(activity!!).saveData("user", PreferencesUtils(activity!!).getData("user")+",${name.text}" ) }
        return v
    }
}