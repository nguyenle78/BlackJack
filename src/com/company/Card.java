package com.company;

public class Card {
    // Card should have random number from 1-13 represent it value and  Ace to J,Q,K
    // Card should belong to 1 of the 4 Suit
    //score represent value of the card in game. Move to Main class perhaps?

    private String suit;
    private int number;
    private int score;
    private String cardName;
    private String cardFullName;

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCardFullName() {
        return cardName + suit;
    }

    // placebo for the temp Card, use to shuffle, or just call it Joker card
    public Card() {
        this(0, "0");
    }

    public Card(int number, String suit) {
        this.number = number;
        this.suit = suit;
        if (number >= 11) {
            this.score = 10;
        } else {
            this.score = number;
        }
        switch (number) {
            case 1 -> this.cardName = "A";
            case 11 -> this.cardName = "J";
            case 12 -> this.cardName = "Q";
            case 13 -> this.cardName = "K";
            default -> this.cardName = String.valueOf(number);
        }
    }

}
