package com.example.newsapi.presentation.fragments

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapi.R
import com.example.newsapi.databinding.FragmentSavedBinding
import com.example.newsapi.presentation.NewsFragmentViewModel
import com.example.newsapi.presentation.recyclers.News
import com.example.newsapi.presentation.recyclers.NewsAdapter
import com.example.newsapi.presentation.recyclers.OnNewsClickListener
import com.example.newsapi.utils.openLink
import org.koin.android.viewmodel.ext.android.viewModel

class SavedFragment : Fragment(R.layout.fragment_saved) {
    private val adapter by lazy { NewsAdapter(newsClickListener) }
    private val binding: FragmentSavedBinding by viewBinding(FragmentSavedBinding::bind)
    private val viewModel: NewsFragmentViewModel by viewModel()

    private val newsClickListener: OnNewsClickListener = object : OnNewsClickListener {

        override fun onIconClickListener(position: Int) {
            viewModel.onNewsItemClicked(position, true)
        }

        override fun onItemClickListener(news: News) {
            openLink(news.articleUrl)
        }
    }

    override fun onStart() {
        super.onStart()
        initRecycler()

        viewModel.newsSaved.observe(viewLifecycleOwner) { news ->
            adapter.submitList(news)
        }
    }

    private fun initRecycler() {
        binding.recycleListSave.adapter = adapter
        binding.recycleListSave.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        binding.floatingActionBackNewsFragment.setOnClickListener {
            findNavController().navigate(SavedFragmentDirections.actionSavedFragmentToMainFragment())
        }
    }
}