package android.webinnovatives.com.cominreaderapp.adapter

import android.webinnovatives.com.cominreaderapp.model.Banner
import android.webinnovatives.com.cominreaderapp.model.Comic
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class MySliderAdapter(var banner:List<Banner>) : SliderAdapter() {
    override fun getItemCount(): Int {
       return banner.size
    }

    override fun onBindImageSlide(position: Int, imageSlideViewHolder: ImageSlideViewHolder?) {
        imageSlideViewHolder!!.bindImageSlide(banner[position].Link)

    }
}