package com.onlinetherapy.model.dtos;

import java.util.List;

public class WordsHomeDTO {

    private List<CreateWordDTO> germanWords;

    private List<CreateWordDTO> spanishWords;

    private List<CreateWordDTO> frenchWords;

    private List<CreateWordDTO> italianWords;

    private long totalCountWords;

    public WordsHomeDTO(List<CreateWordDTO> germanWords, List<CreateWordDTO> spanishWords,
                        List<CreateWordDTO> frenchWords, List<CreateWordDTO> italianWords, long totalCountWords) {
        this.germanWords = germanWords;
        this.spanishWords = spanishWords;
        this.frenchWords = frenchWords;
        this.italianWords = italianWords;
        this.totalCountWords = totalCountWords;
    }

    public List<CreateWordDTO> getGermanWords() {
        return germanWords;
    }

    public void setGermanWords(List<CreateWordDTO> germanWords) {
        this.germanWords = germanWords;
    }

    public List<CreateWordDTO> getSpanishWords() {
        return spanishWords;
    }

    public void setSpanishWords(List<CreateWordDTO> spanishWords) {
        this.spanishWords = spanishWords;
    }

    public List<CreateWordDTO> getFrenchWords() {
        return frenchWords;
    }

    public void setFrenchWords(List<CreateWordDTO> frenchWords) {
        this.frenchWords = frenchWords;
    }

    public List<CreateWordDTO> getItalianWords() {
        return italianWords;
    }

    public void setItalianWords(List<CreateWordDTO> italianWords) {
        this.italianWords = italianWords;
    }

    public long getTotalCountWords() {
        return totalCountWords;
    }

    public void setTotalCountWords(long totalCountWords) {
        this.totalCountWords = totalCountWords;
    }


//    Below the language sections is located a button
//    "Remove all words {total count of words}" with an info that shows the total count of all words.
//
//Below each word is located an info bar that shows the username of the user who added the word and the input date
// (in the format yyyy-MM-dd).
}
