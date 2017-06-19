package com.google.engedu.wordladder;

/**
 * Created by Ajacob2609 on 6/19/2017.
 */

public class WordNode {
    String word;
    int numSteps;
    WordNode pre;

    public WordNode(String word, int numSteps, WordNode pre){
        this.word = word;
        this.numSteps = numSteps;
        this.pre = pre;
    }
}
