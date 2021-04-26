package com.example.sqlite

import android.widget.Toast
import com.example.sqlite.utilite.APP_ACTIVITY

fun showToast(message: String){
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}