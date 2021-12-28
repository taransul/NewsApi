package com.example.newsapi.utils

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment

fun Fragment.openLink(link: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    startActivity(requireContext(), intent, Bundle())
}