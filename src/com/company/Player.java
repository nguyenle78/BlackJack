package com.company;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Player {
    private String name;
    private double money;
    private final ArrayList<Card> hand;
    private int handScore;
    public boolean isBusted;

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setHandScore(int handScore) {
        this.handScore = handScore;
    }

    public int getHandScore() {
        return handScore;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public Player() {
        this("Player1", 1000);
    }

    public Player(String name) {
        this(name, 1000);
        this.name = name;
        this.handScore = 0;
        this.isBusted = false;
    }


    public String getHandAsString() {
        StringBuilder playerHand = new StringBuilder();
        for (Card card : hand) {
            playerHand.append(card.getCardFullName());
        }
        return playerHand.toString();
    }

    public Player(String name, double money) {
        this.money = money;
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

    public void setBusted(boolean busted) {
        isBusted = busted;
    }


    public boolean isBusted() {
        return isBusted;
    }

    public String getFormattedMoney() {
        NumberFormat formatObj = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        return formatObj.format(money);
    }

    public void drawCard(Card card) {
        this.hand.add(card);
        this.handScore += card.getScore();
        if (handScore > 21) {
            System.out.println(name + " BUSTED!");
            isBusted = true;
        }
    }
}

