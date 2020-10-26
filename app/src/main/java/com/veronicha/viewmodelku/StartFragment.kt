package com.veronicha.viewmodelku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.veronicha.viewmodelku.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    lateinit var binding : FragmentStartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_start,container,false)

        binding.btnPlay.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToPuzzleFragment()
            requireView().findNavController().navigate(action)
        }

        return binding.root
    }
}