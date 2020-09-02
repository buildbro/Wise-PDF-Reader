package com.buildbrothers.wisepdfreader.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.buildbrothers.wisepdfreader.R
import com.buildbrothers.wisepdfreader.model.Document
import kotlinx.android.synthetic.main.recent_list_item.view.*
import java.text.SimpleDateFormat

class RecentListAdapter internal constructor(context: Context, var listener: RecyclerViewClickListener) : RecyclerView.Adapter<RecentListAdapter.RecentViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var documents = emptyList<Document>()

    inner class RecentViewHolder(itemView: View, listener: RecyclerViewClickListener) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.file_name_text_view
        val pagesTextView: TextView = itemView.pages_text_view
        val dateTextView: TextView = itemView.date_text_view

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val itemView = inflater.inflate(R.layout.recent_list_item, parent, false)
        return RecentViewHolder(itemView, listener)
    }

    override fun getItemCount() = documents.size

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        val currentDocument = documents[position]

        holder.titleTextView.text = currentDocument.filePath
        holder.pagesTextView.text = currentDocument.id.toString()
        holder.dateTextView.text = SimpleDateFormat("dd/MM/yyyy hh:mm").format(currentDocument.lastRead)
        holder.titleTextView.setOnClickListener { it ->
            if (listener != null) {
                listener.onViewClicked(it, position)
            }
        }
    }

    internal fun setDocuments(documents: List<Document>) {
        this.documents = documents
        notifyDataSetChanged()
    }

    fun getCurrentDocument(position: Int): Document {
        return documents[position]
    }
}