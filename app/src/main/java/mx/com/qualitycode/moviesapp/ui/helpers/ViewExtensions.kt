package mx.com.qualitycode.moviesapp.ui.helpers

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Extension functions to help to reduce the boilerplate when setting up visibility to views.
 * Feel free to add extensions functions as you need.
 */

fun View.hideKeyBoard(){
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

/**
 * Set visibility of a view to visible.
 * Usage: myView.show()
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Set visibility of a view to invisible.
 * Usage: myView.invisible()
 */

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

/**
 * Extension function to load images into a view from a given url.
 */
fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url?:"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSuBizh_BlT_hCoVStNF1xk7H8UrJ8TQ0M8jw&usqp=CAU")
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun TextView.loadText(text: String) {
    this.text = text
}