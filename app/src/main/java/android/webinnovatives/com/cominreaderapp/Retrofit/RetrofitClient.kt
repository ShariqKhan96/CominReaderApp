package android.webinnovatives.com.cominreaderapp.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var staticRef: Retrofit? = null

    val instance: Retrofit
        get() {
            if (staticRef == null) {
                staticRef = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://192.168.100.24/ComicReader/")
                    .build()
                return staticRef!!
            }
            return staticRef!!
        }


}