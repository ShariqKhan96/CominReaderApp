package android.webinnovatives.com.cominreaderapp.interfaces

import android.webinnovatives.com.cominreaderapp.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {
    @GET("getBanners.php")
    fun getBanners():Call<BannerObject>

    @GET("getComics.php")
    fun getComics(): Call<ComicObject>

   @GET("getChapters.php")
   fun getChapter(@Query("MangaID") mangaID:String):Call<ChapterObject>
}