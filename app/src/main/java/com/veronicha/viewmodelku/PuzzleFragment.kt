package com.veronicha.viewmodelku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

        binding.btnOK.setOnClickListener {
            checkAnswer()
            updateScore()
            updateWord()
        }
        binding.btnSkip.setOnClickListener {
            puzzleViewModel.onSkip()
            updateScore()
            updateWord()
        }
        updateScore()
        updateWord()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }




    private fun gameOver(){
        val action =
            PuzzleFragmentDirections.actionPuzzleFragmentToGameOverFragment(puzzleViewModel.score)
        findNavController().navigate(action)

    }

    fun updateWord(){

        binding.textAnswerBox1.text= puzzleViewModel.word?.question_gap_1
        binding.textAnswerBox2.text  = puzzleViewModel.word?.question_gap_2
    }

    fun updateScore(){
        binding.textScore.text = puzzleViewModel.score?.toString()
    }

    fun checkAnswer(){
        if(text_answer_gap.text.toString().toUpperCase() == puzzleViewModel.word?.correctAnswer){
            text_answer_gap.text  = null
            puzzleViewModel.onRightAnswer()
            puzzleViewModel.nextWord()
        }
        else{
            text_answer_gap.text  = null
            puzzleViewModel.onWrongAnswer()
            puzzleViewModel.nextWord()
        }
    }


}