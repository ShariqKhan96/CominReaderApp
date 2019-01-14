package android.webinnovatives.com.cominreaderapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.webinnovatives.com.cominreaderapp.Utils.Common
import android.webinnovatives.com.cominreaderapp.adapter.ChapterAdapter
import android.webinnovatives.com.cominreaderapp.interfaces.Api
import android.webinnovatives.com.cominreaderapp.model.ChapterObject
import android.widget.Toast
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_chapter.*
import kotlinx.android.synthetic.main.chapter_itemview.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class ChapterActivity : AppCompatActivity() {

    lateinit var api: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
        api = Common.api
        toolbar_text.text = "Chapters"
        getChapters()

    }

    private fun getChapters() {
        val dialog = SpotsDialog.Builder()
            .setContext(this)
            .setMessage("Please Wait..")
            .build()
        dialog.show()

        val call: Call<ChapterObject> = api.getChapter(Common.selected_manga?.ID.toString())
        call.enqueue(object : Callback<ChapterObject> {
            override fun onFailure(call: Call<ChapterObject>, t: Throwable) {
                Toast.makeText(baseContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            override fun onResponse(call: Call<ChapterObject>, response: Response<ChapterObject>) {
                chapters_list.layoutManager = LinearLayoutManager(this@ChapterActivity)
                chapters_list.addItemDecoration(DividerItemDecoration(this@ChapterActivity, LinearLayoutManager.HORIZONTAL))
                Log.e("Chapters", response.body()?.chapters?.size.toString());
                chapters_list.adapter = ChapterAdapter(response.body()?.chapters!!)
                chapter_count.text = StringBuilder()
                    .append("CHAPTERS(")
                    .append(response.body()!!.chapters!!.size)
                    .append(")")
                dialog.dismiss()

            }

        })
    }
}
