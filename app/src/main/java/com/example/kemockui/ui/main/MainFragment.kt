package com.example.kemockui.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kemockui.R
import com.example.kemockui.databinding.MainFragmentBinding
import com.example.kemockui.databinding.MainFragmentOnePageBinding
import com.example.kemockui.model.repository.RepositoryRvHomework
import com.example.kemockui.ui.main.pages.MainFragmentOnePage

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private val adapter: RvAdapterCommon by lazy { RvAdapterCommon () }
    // Тут BINDING for fragments (для активити все проще)
    // 1. Сделать запись в gradle
    // 2. Создаем вспомогательный объект
    private var _binding: MainFragmentBinding? = null

    // 3. Создаем объект
    private val binding get() = _binding!!

    // 4. занулить вспомогательный binding здесь
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    // 5. см. ниже добавить строки в onCreateView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 5. изменение для binding здесь:
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        _binding = MainFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        childFragmentManager.beginTransaction()
            .replace(R.id.bottom_container, MainFragmentOnePage())
            .commitAllowingStateLoss()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener() { item ->
            when (item.itemId) {
                R.id.bottom_item_one -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.bottom_container, MainFragmentOnePage())
                        .commitAllowingStateLoss()
                    true
                }
//                R.id.bottom_item_two -> {
//                    childFragmentManager.beginTransaction()
//                        .replace(R.id.bottom_container, ItemTwoFragment())
//                        .commitAllowingStateLoss()
//                    true
//                }
//                R.id.bottom_item_three -> {
//                    childFragmentManager.beginTransaction()
//                        .replace(R.id.bottom_container, ItemThreeFragment())
//                        .commitAllowingStateLoss()
//                    true
//                }
                else -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.bottom_container, MainFragmentOnePage())
                        .commitAllowingStateLoss()
                    true
                }
            }
        }
    }

}