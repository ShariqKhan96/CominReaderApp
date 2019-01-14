package android.webinnovatives.com.cominreaderapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webinnovatives.com.cominreaderapp.R
import android.webinnovatives.com.cominreaderapp.interfaces.RecyclerClickListener
import android.webinnovatives.com.cominreaderapp.model.Comic
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MyComicAdpater(
    internal var comicList: List<Comic>,
    internal var recyclerClickListener: RecyclerClickListener
):
    RecyclerView.Adapter<MyComicAdpater.MyVH>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyVH {
        return MyVH(
            LayoutInflater.from(p0.context).inflate(R.layout.comic_item_view, p0, false))
    }

    override fun getItemCount(): Int {
        return comicList.size
    }

    override fun onBindViewHolder(p0: MyVH, p1: Int) {
        Picasso.get().load(comicList.get(p1).Image).into(p0.comicImage)
        p0.comicName.text = comicList.get(p1).Name
        p0.itemView.setOnClickListener({
            recyclerClickListener.onItemClick(comicList.get(p1))
        })
    }

    class MyVH(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal var comicName: TextView
        internal var comicImage: ImageView

        init {
            comicImage = itemView.findViewById(R.id.comic_image);
            comicName = itemView.findViewById(R.id.comic_name)
        }



    }
}