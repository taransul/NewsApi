package com.example.newsapi.utils

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment

fun Fragment.webOpen(web: String?) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(web))
    startActivity(requireContext(), intent, Bundle())
}