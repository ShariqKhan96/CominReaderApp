package android.webinnovatives.com.cominreaderapp.adapter

import android.webinnovatives.com.cominreaderapp.model.Chapter
import  android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webinnovatives.com.cominreaderapp.R
import android.widget.TextView

class ChapterAdapter(internal var chapters: List<Chapter>) : RecyclerView.Adapter<ChapterAdapter.MyVH>() {
    class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var chapterName: TextView

        init {
            chapterName = itemView.findViewById<TextView>(R.id.chapter_name)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ChapterAdapter.MyVH {
        return MyVH(LayoutInflater.from(parent.context).inflate(R.layout.chapter_itemview, parent, false))

    }

    override fun getItemCount(): Int {
        return chapters.size
    }

    override fun onBindViewHolder(p0: ChapterAdapter.MyVH, pos: Int) {
        p0.chapterName.setText(chapters.get(pos).Name)
    }
}