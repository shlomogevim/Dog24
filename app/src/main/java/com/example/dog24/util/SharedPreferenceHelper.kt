package com.example.dog24.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPreferenceHelper {
    companion object {
        private var prefs: SharedPreferences? = null
        @Volatile private var instance: SharedPreferenceHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context):SharedPreferenceHelper= instance ?: synchronized(LOCK){
            instance ?: buildHelper(context).also{
                instance=it
            }
        }
private fun buildHelper (context: Context):SharedPreferenceHelper{
    prefs=PreferenceManager.getDefaultSharedPreferences(context)
    return SharedPreferenceHelper()
}



    }



}
