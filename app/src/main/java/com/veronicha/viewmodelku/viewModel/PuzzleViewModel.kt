package com.veronicha.viewmodelku.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.veronicha.viewmodelku.model.WordPuzzleData

class PuzzleViewModel : ViewModel() {
    //unit block will run just after the class initialization
    var score = 0
    var word: WordPuzzleData? = null
    lateinit var words : ArrayList<WordPuzzleData>

    init {
        loadData()
        nextWord()
    }

     fun loadData(){
        words = arrayListOf(
            WordPuzzleData("AND","OID","R"),
            WordPuzzleData("STU","IO","D"),
            WordPuzzleData("POLIN","MA","E"),
            WordPuzzleData("MALA","G","N"),
            WordPuzzleData("JA","A","W")
        )
        words.shuffle()
    }

     fun nextWord(){
        if(words.isEmpty()){
//            gameOver()
        }
        else{
            word = words.removeAt(0)

        }
    }

    fun onSkip(){
        score--
        nextWord()
    }

    fun onWrongAnswer(){
        score--
        nextWord()
    }

    fun onRightAnswer(){
        score++
        nextWord()
    }
}