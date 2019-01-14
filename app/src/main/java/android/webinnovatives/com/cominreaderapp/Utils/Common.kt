package android.webinnovatives.com.cominreaderapp.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.webinnovatives.com.cominreaderapp.Retrofit.RetrofitClient
import android.webinnovatives.com.cominreaderapp.interfaces.Api
import android.webinnovatives.com.cominreaderapp.model.Comic

object Common {
    var selected_manga: Comic? = null

    val api: Api
        get() {
            val retrofit = RetrofitClient.instance
            return retrofit.create(Api::class.java)
        }


    fun isConnected(baseContext: Context?): Boolean {
        val cm = baseContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT < 23) {

            val ni = cm.activeNetworkInfo as NetworkInfo
            if (ni != null) {
                return ni.isConnected && (ni.type == ConnectivityManager.TYPE_WIFI
                        || ni.type == ConnectivityManager.TYPE_MOBILE)

            }
        } else {
            val n = cm.activeNetwork
            if (n != null) {
                val nc = cm.getNetworkCapabilities(n)
                return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
        }

        return false
    }
}