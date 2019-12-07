package me.raghu.raghunandan_kavi

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

const val URL = "https://truecaller.blog/2018/01/22/life-as-an-android-engineer/"


 fun Context.checkConnection():Boolean{
     val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
     val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
     return activeNetwork?.isConnectedOrConnecting == true
 }