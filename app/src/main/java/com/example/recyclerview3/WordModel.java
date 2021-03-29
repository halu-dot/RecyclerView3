package com.example.recyclerview3;

import java.io.Serializable;

public class WordModel implements Serializable {
    private String word; //username sa vid

    public WordModel(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
