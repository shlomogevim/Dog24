package com.example.dog24.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dog24.R

fun getProgressDrawable(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=10f
        centerRadius=50f
        start()
    }
}

fun ImageView.loadImage(uri:String?,progressDrawable: CircularProgressDrawable){
    val options=RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.dog)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}






/*
fun Image.loadImage(uri:String?,progressDrawable: CircularProgressDrawable){
   val option=RequestOptions()
       .placeholder(progressDrawable)
       .error(R.drawable.dog)

   // Glide.with(context)
}
*/

/*
fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.dog)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)

}*/
