package com.buildbrothers.wisepdfreader.adapter

import android.view.View

interface RecyclerViewClickListener {
    fun onRowClicked(position: Int)
    fun onViewClicked(v: View?, position: Int)
}