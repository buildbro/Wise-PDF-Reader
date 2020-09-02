package com.buildbrothers.wisepdfreader

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.buildbrothers.wisepdfreader.adapter.RecentListAdapter
import com.buildbrothers.wisepdfreader.adapter.RecyclerViewClickListener
import com.buildbrothers.wisepdfreader.model.Document
import com.buildbrothers.wisepdfreader.utils.RealPathUtil
import com.buildbrothers.wisepdfreader.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModels()
    lateinit var adapter: RecentListAdapter

    private val listener = object: RecyclerViewClickListener {
        override fun onRowClicked(position: Int) {
            TODO("Not yet implemented")
        }

        override fun onViewClicked(v: View?, position: Int) {
            if (v?.id == R.id.file_name_text_view) {
                val current = adapter.getCurrentDocument(position)
                openReader(current)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tool_bar)

        val recyclerView = recyclerView
        adapter = RecentListAdapter(this, listener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        initializeUi()
        viewModel.recentDocuments.observe(this, Observer { documents ->
            documents.let { adapter.setDocuments(it) }
        })
    }

    private fun initializeUi() {
        open_file_fab.setOnClickListener {
            openFile()
        }
    }


    private fun openFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"
        }

        startActivityForResult(intent, PICK_PDF_FILE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings_menu)
            startActivity(Intent(applicationContext, SettingsActivity::class.java))
        return true
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PDF_FILE && resultCode == Activity.RESULT_OK) {
            data?.data.also { uri ->
                val fullPath: String? = RealPathUtil.getRealPath(applicationContext, uri!!)
                Toast.makeText(applicationContext, fullPath, Toast.LENGTH_LONG).show()
                val document =
                    Document(id = 0, filePath = fullPath!!, lastRead = System.currentTimeMillis())
                viewModel.insert(document)
                openReader(document)
            }
        }
    }

    companion object {
        const val PICK_PDF_FILE = 2
    }


    private fun openReader(document: Document) {
        val readerIntent = Intent(applicationContext, ReaderActivity::class.java)
        readerIntent.putExtra("file", document.filePath)
        startActivity(readerIntent)
    }
}
