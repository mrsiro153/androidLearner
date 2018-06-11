package com.example.nhdoan.doanapp.model.serverResponse;

import java.util.List;

public class LanguageResponse {
    private String question;
    private String published_at;
    private List<Choice> choices;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public String toStringLanguage() {
        return "LanguageResponse{" +
                "question='" + question + '\'' +
                ", published_at='" + published_at + '\'' +
                ", choices=" + choices +
                '}';
    }
}
