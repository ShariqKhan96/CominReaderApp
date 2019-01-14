package android.webinnovatives.com.cominreaderapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.webinnovatives.com.cominreaderapp.Retrofit.RetrofitClient
import android.webinnovatives.com.cominreaderapp.Service.PicassoImageLoadingService
import android.webinnovatives.com.cominreaderapp.Utils.Common
import android.webinnovatives.com.cominreaderapp.adapter.MyComicAdpater
import android.webinnovatives.com.cominreaderapp.adapter.MySliderAdapter
import android.webinnovatives.com.cominreaderapp.interfaces.Api
import android.webinnovatives.com.cominreaderapp.interfaces.RecyclerClickListener
import android.webinnovatives.com.cominreaderapp.model.Banner
import android.webinnovatives.com.cominreaderapp.model.BannerObject
import android.webinnovatives.com.cominreaderapp.model.Comic
import android.webinnovatives.com.cominreaderapp.model.ComicObject
import android.widget.TextView
import android.widget.Toast
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import ss.com.bannerslider.Slider

class MainActivity : AppCompatActivity(), RecyclerClickListener {

    lateinit var api: Api


    override fun onItemClick(comic: Comic) {
        Toast.makeText(baseContext, comic.Name, Toast.LENGTH_SHORT).show()
        Common.selected_manga = comic
        startActivity(Intent(this, ChapterActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        api = Common.api

        Slider.init(PicassoImageLoadingService(this))

        comic_list.layoutManager = GridLayoutManager(this, 2)
        refresh_layout.setColorSchemeResources(
            R.color.colorPrimaryDark,
            android.R.color.holo_orange_dark,
            android.R.color.black
        )

        refresh_layout.setOnRefreshListener {
            if (Common.isConnected(baseContext)) {
                fetchBanners();
                fetchComics();
            } else {
                Toast.makeText(baseContext, "Please check your connection", Toast.LENGTH_SHORT).show();
            }


        }
        refresh_layout.post {
            if (Common.isConnected(baseContext)) {
                fetchBanners();
                fetchComics();
            } else {
                Toast.makeText(baseContext, "Please check your connection", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private fun fetchComics() {

        val dialog = SpotsDialog.Builder()
            .setContext(this)
            .setMessage("Please wait..")
            .build()

        if (!refresh_layout.isRefreshing)
            dialog.show()


        val call: Call<ComicObject> = api.getComics()
        call.enqueue(object : Callback<ComicObject> {
            override fun onFailure(call: Call<ComicObject>, t: Throwable) {
                Toast.makeText(baseContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ComicObject>, response: Response<ComicObject>) {
                comic_counts.text = StringBuilder()
                    .append("NEW COMICS(")
                    .append(response.body()!!.comics!!.size)
                    .append(")")

                comic_list.adapter = MyComicAdpater(response.body()!!.comics!!, this@MainActivity)

                if (!refresh_layout.isRefreshing) {
                    refresh_layout.isRefreshing = false
                    dialog.dismiss()
                }
            }

        })


    }

    private fun fetchBanners() {
        val dialog = SpotsDialog.Builder()
            .setContext(this)
            .setMessage("Please wait..")
            .build()

        if (!refresh_layout.isRefreshing)
            dialog.show()
        var call: Call<BannerObject> = api.getBanners()
        call.enqueue(object : Callback<BannerObject> {
            override fun onFailure(call: Call<BannerObject>, t: Throwable) {
                Toast.makeText(baseContext, t.localizedMessage, Toast.LENGTH_LONG).show();
            }

            override fun onResponse(call: Call<BannerObject>, response: Response<BannerObject>) {

                slider.setAdapter(MySliderAdapter(response.body()?.banners!!))
                Log.e("BANNERS", response.body()!!.banners?.size.toString())

                if (!refresh_layout.isRefreshing) {
                    refresh_layout.isRefreshing = false
                    dialog.dismiss()
                }
            }

        })

    }
}
