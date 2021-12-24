package com.example.android.unscramble.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var _score = MutableLiveData(0)
    val score: MutableLiveData<Int> get() = _score

    private var _currentWordCount = MutableLiveData(0)
    val currentWordCount: MutableLiveData<Int> get() = _currentWordCount

    private var _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord: MutableLiveData<String> get() = _currentScrambledWord

    private var wordsList: MutableList<String> = mutableListOf()
    lateinit var currentWord: String

    init { getNextWord() }

    fun getNextWord() {
        currentWord = allWordsList.random()
        if (wordsList.contains(currentWord)) { getNextWord() }
        else {
            val tempWord = currentWord.toCharArray()
            while (String(tempWord) == currentWord) { tempWord.shuffle() }
            wordsList.add(currentWord)
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = _currentWordCount.value?.inc() //todo check?
        }
    }

    fun nextWord(): Boolean {
        return if (currentWordCount.value!! < MAX_NO_OF_WORDS) { getNextWord();true }
        else { false }
    }

    fun increaseScore() { _score.value = _score.value!!.plus(SCORE_INCREASE) }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord,true)) {
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData(){
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }
}

