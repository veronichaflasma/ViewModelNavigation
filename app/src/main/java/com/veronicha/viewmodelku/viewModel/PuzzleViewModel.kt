package com.veronicha.viewmodelku.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.veronicha.viewmodelku.model.WordPuzzleData
import androidx.lifecycle.MutableLiveData

class PuzzleViewModel : ViewModel() {
    //unit block will run just after the class initialization
    var score = MutableLiveData<Int>()
    var word  = MutableLiveData<WordPuzzleData>()
    val gameFinish = MutableLiveData<Boolean>()
    lateinit var words : ArrayList<WordPuzzleData>

    init {
//        Log.i("PuzzleViewModel", "PuzzleViewModel created!")
        loadData()
        nextWord()
        score.value = 0
        gameFinish.value = false
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
            gameFinish.value = true
        }
        else{
            word.value = words.removeAt(0)
        }
    }

    fun onSkip(){
        score.value = score.value?.minus(1)
        nextWord()
    }

    fun onWrongAnswer(){
        score.value = score.value?.minus(1)
        nextWord()
    }

    fun onRightAnswer(){
        score.value = score.value?.plus(1)
        nextWord()
    }

    fun onGameOver(){
        gameFinish.value = false
    }
}