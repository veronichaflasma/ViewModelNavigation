package com.veronicha.viewmodelku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.veronicha.viewmodelku.databinding.FragmentPuzzleBinding
import com.veronicha.viewmodelku.viewModel.PuzzleViewModel
import kotlinx.android.synthetic.main.fragment_puzzle.*


class PuzzleFragment : Fragment() {

    lateinit var binding : FragmentPuzzleBinding


    private lateinit var puzzleViewModel: PuzzleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_puzzle,container,false)

        puzzleViewModel = ViewModelProvider(this).get(PuzzleViewModel::class.java)
        binding.lifecycleOwner = this

        puzzleViewModel.word.observe(viewLifecycleOwner, Observer {
            binding.textAnswerBox1.text= puzzleViewModel.word?.value?.question_gap_1
            binding.textAnswerBox2.text  = puzzleViewModel.word?.value?.question_gap_2
        })

        puzzleViewModel.word.observe(viewLifecycleOwner, Observer {
            binding.textScore.text = it.toString()
        })

        puzzleViewModel.gameFinish.observe(viewLifecycleOwner, Observer {
            if(it){
                gameOver()
            }
        })


        binding.btnOK.setOnClickListener {
            checkAnswer()

        }
        binding.btnSkip.setOnClickListener {
            puzzleViewModel.onSkip()

        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    private fun gameOver(){
        val action =
                //if this value is use zero and else not null use this value
            PuzzleFragmentDirections.actionPuzzleFragmentToGameOverFragment(puzzleViewModel.score.value?:0)
        findNavController().navigate(action)

        puzzleViewModel.onGameOver()
//        Toast.makeText(activity, "Game Finish", Toast.LENGTH_SHORT)


    }


    fun checkAnswer(){
        if(text_answer_gap.text.toString().toUpperCase() == puzzleViewModel.word?.value?.correctAnswer){
            text_answer_gap.text  = null
            puzzleViewModel.onRightAnswer()
//            puzzleViewModel.nextWord()
        }
        else{
            text_answer_gap.text  = null
            puzzleViewModel.onWrongAnswer()
//            puzzleViewModel.nextWord()
        }
    }


}