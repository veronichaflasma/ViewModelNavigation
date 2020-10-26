package com.veronicha.viewmodelku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.veronicha.viewmodelku.databinding.FragmentGameOverBinding
import kotlinx.android.synthetic.main.fragment_game_over.*


class GameOverFragment : Fragment() {
    lateinit var binding: FragmentGameOverBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game_over,container,false)

        binding.btnPlayAgain.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_gameOverFragment_to_startFragment)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val args = GameOverFragmentArgs.fromBundle(it)
            text_final_score.text = args.score.toString()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This callback will only be called when MyFragment is at least Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            Navigation.findNavController(requireView()).navigate(R.id.action_gameOverFragment_to_startFragment)
        }

    }
}