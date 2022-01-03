package com.techand.daggerapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.techand.daggerapp.R
import com.techand.daggerapp.data.network.Resource
import com.techand.daggerapp.databinding.FragmentHomeBinding
import com.techand.daggerapp.util.handleApiError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImageViewModel by viewModels()
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv()
    }

    private fun setUpRv() {
        imageAdapter = ImageAdapter()
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            setHasFixedSize(true)
            adapter =imageAdapter
        }

        viewModel.imageLiveData.observe(viewLifecycleOwner, {

            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        imageAdapter.images = it.value
                    }
                }
                is Resource.Failure -> handleApiError(it)
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}