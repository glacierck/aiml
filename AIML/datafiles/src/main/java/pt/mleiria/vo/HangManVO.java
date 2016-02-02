/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.vo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class HangManVO {
    
    private String player1;
    private String player2;
    private String wordToGuess;
    private char[] wordToGuessArr;
    private int wordSize;
    private String tip;

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getWordToGuess() {
        
        return wordToGuess;
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuessArr = wordToGuess.toCharArray();
        this.wordSize = wordToGuessArr.length;
        this.wordToGuess = wordToGuess;
        Logger.getLogger(HangManVO.class.getName()).log(Level.INFO, wordToGuess);
    }

    public char[] getWordToGuessArr() {
        return wordToGuessArr;
    }

    public void setWordToGuessArr(char[] wordToGuessArr) {
        this.wordToGuessArr = wordToGuessArr;
    }

    public int getWordSize() {
        return wordSize+1;
    }

    public void setWordSize(int wordSize) {
        this.wordSize = wordSize;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
    
    
    
}
