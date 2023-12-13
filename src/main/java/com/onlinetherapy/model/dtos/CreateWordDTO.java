package com.onlinetherapy.model.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CreateWordDTO {

    @Size(min = 2, max = 40)
    @NotNull
    private String term;

    @Size(min = 2, max = 80)
    @NotNull
    private String translation;

    @Size(min = 2, max = 200)
    private String example;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate inputDate;

    @NotNull
    private String language;

    public CreateWordDTO() {
    }

    public CreateWordDTO(String term, String translation, String example, LocalDate inputDate, String language) {
        this.term = term;
        this.translation = translation;
        this.example = example;
        this.inputDate = inputDate;
        this.language = language;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
