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
import com.example.kemockui.model.repository.RepositoryRv

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private val adapterLessons: RvAdapterLesson by lazy { RvAdapterLesson () }
    private val adapterHomework: RvAdapterHW by lazy { RvAdapterHW () }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        _binding = MainFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.liveDataExamTime.observe(viewLifecycleOwner, Observer {examDate ->
            binding.areYouReady.text = "are you ready to exam date -\n${examDate.take(16)}?"
        })

        viewModel.liveDataPeriodToExam.observe(viewLifecycleOwner, Observer { periodToExam ->
            binding.count1.text = periodToExam.substring(8,9)
            binding.count2.text = periodToExam.substring(9,10)
            binding.count3.text = periodToExam.substring(11,12)
            binding.count4.text = periodToExam.substring(12,13)
            binding.count5.text = periodToExam.substring(14,15)
            binding.count6.text = periodToExam.substring(15,16)
            binding.count7sec.text = periodToExam.substring(17,18)
            binding.count8sec.text = periodToExam.substring(18,19)
        })
        viewModel.updateCurrentTime()

//rv lessons
        binding.rvLessons.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvLessons.adapter = adapterLessons
        val listLessons = RepositoryRv.getListRvLessons()
        adapterLessons.adapterList = RepositoryRv.getListRvLessons()
        // set on position
        var positionInTheAdapter = 0
        for (index in listLessons.indices) {
            if (listLessons[index].isGreenAdditionalLesson) positionInTheAdapter = index
        }
        binding.rvLessons.getLayoutManager()?.scrollToPosition(positionInTheAdapter)

//rv homework
        binding.rvHomework.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvHomework.adapter = adapterHomework
        adapterHomework.adapterList = RepositoryRv.getListRvHomework()

    }
}