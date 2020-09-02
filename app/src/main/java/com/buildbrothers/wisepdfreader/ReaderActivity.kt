package com.buildbrothers.wisepdfreader

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.barteksc.pdfviewer.util.FitPolicy
import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.android.synthetic.main.activity_reader.*
import java.io.File

class ReaderActivity : AppCompatActivity() {

    var lastPage: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader)

        val intent = intent

        if (intent != null) {
            val filePath = intent.getStringExtra("file")
            lastPage = intent.getIntExtra("page", 0)
            pdfView.fromUri(Uri.fromFile(File(filePath!!)))
                .spacing(8)
                .pageFitPolicy(FitPolicy.WIDTH)
                .defaultPage(lastPage)
                .load()

        }

    }

}
