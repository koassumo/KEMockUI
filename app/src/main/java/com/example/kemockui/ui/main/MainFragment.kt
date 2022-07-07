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
import com.example.kemockui.model.repository.CalendarRepository.currentDateTime
import com.example.kemockui.model.repository.RepositoryRvHomework

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

        binding.rvHomework.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val adapterHomework = RvAdapterCommon()
        binding.rvHomework.adapter = adapterHomework

        adapterHomework.adapterList = RepositoryRvHomework.getListRvHomework()

        viewModel.liveDataCurrentTime.observe(viewLifecycleOwner, Observer { time ->
            binding.areYouReady.text = time
            binding.count1.text = time.substring(8,9)
            binding.count2.text = time.substring(9,10)
            binding.count3.text = time.substring(11,12)
            binding.count4.text = time.substring(12,13)
            binding.count5.text = time.substring(14,15)
            binding.count6.text = time.substring(15,16)
            binding.count7sec.text = time.substring(17,18)
            binding.count8sec.text = time.substring(18,19)
        })

        viewModel.updateCurrentTime()

    }

}