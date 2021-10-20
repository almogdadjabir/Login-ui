package com.almogdadjabir.com.other

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import com.almogdadjabir.com.model.BottomNavItem

object Constants {
    const val URL = "https://newsapi.org/v2/top-headlines?country=ae&apiKey=e74ef58d62ab46328e2f94d8d8c6cad2"
    const val quran_URL = "https://api.quran.com/api/v4/chapters?language=en"
    var verses_URL = "https://api.quran.com/api/v4/quran/verses/uthmani?chapter_number=@"
    var translation_URL = "https://api.quran.com/api/v4/quran/translations/131?chapter_number=@"

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }


    fun createToast(msg: String, context: Context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun shareData(url: String, context: Context) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }


    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home News",
            icon = Icons.Filled.Home,
            route = "HomeNews"
        ),
        BottomNavItem(
            label = "quran",
            icon = Icons.Filled.Book,
            route = "Quran"
        ),

    )
}
