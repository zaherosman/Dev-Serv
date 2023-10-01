package com.example.teste2.utils

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.teste2.ListaServicoActivity

class ActivityNavigationClickListener(val activityClass: Class<out AppCompatActivity>) : View.OnClickListener {
    override fun onClick(view: View?) {
        val intent = Intent(view!!.context, activityClass).apply {
        }
        startActivity(view.context, intent, null)
    }
}