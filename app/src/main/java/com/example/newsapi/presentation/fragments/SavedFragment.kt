package com.example.newsapi.presentation.fragments

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapi.R
import com.example.newsapi.databinding.FragmentSavedBinding
import com.example.newsapi.presentation.NewsFragmentViewModel
import com.example.newsapi.presentation.recyclers.news.OnNewsClickListener
import com.example.newsapi.presentation.recyclers.saved.News
import com.example.newsapi.presentation.recyclers.saved.NewsSavedAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class SavedFragment : Fragment(R.layout.fragment_saved) {
    private val adapter by lazy { NewsSavedAdapter(newsClickListener) }
    private val binding: FragmentSavedBinding by viewBinding(FragmentSavedBinding::bind)
    private val viewModel: NewsFragmentViewModel by viewModel()

    private val newsClickListener: OnNewsClickListener = object : OnNewsClickListener {

        override fun onIconClickListener(position: Int, activated: Boolean) {
            viewModel.onNewsItemClicked(position, activated)
        }

        override fun <T> onItemClickListener(news: T) {
            news as News
            Toast.makeText(
                context,
                news.title,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    override fun onStart() {
        super.onStart()
        initRecycler()

        viewModel.newsSaved.observe(viewLifecycleOwner) { news ->
            adapter.submitList2(news)
        }
    }

    private fun initRecycler() {
        binding.recycleListSave.adapter = adapter
        binding.recycleListSave.layoutManager = LinearLayoutManager(context)
    }
}