package com.example.nhdoan.doanapp.model.serverResponse;

public class Choice {
    private String choice;
    private Integer votes;
    private String color;

    public String getChoice() {
        return choice;
    }

    public Choice setChoice(String choice) {
        this.choice = choice;
        return this;
    }

    public Integer getVotes() {
        return votes;
    }

    public Choice setVotes(Integer votes) {
        this.votes = votes;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Choice setColor(String color) {
        this.color = color;
        return this;
    }
}
