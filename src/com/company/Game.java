
package com.company;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Player player;
    private final Player dealer;
    private Card[] deck;
    private double bet;
    private double pot;
    private final Scanner scanner;
    public boolean roundFinished;

    // Constructor of Game class including a new Scanner Object inside Game class
    // Scanner class later will be refer as this.scanner, or simply scanner
    public Game(Player player, Player dealer) {
        this.scanner = new Scanner(System.in);
        // Player-Constructor in Player class, with Player(name,money)
        this.player = player;
        this.dealer = dealer;
        this.deck = deckGenerate();
        this.pot = 0;
        this.bet = 0;
    }

    public Card[] deckGenerate() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String[] suits = {"♠", "♥", "♣", "♦"};
        // declare an Array of Cards, length 52
        Card[] deck = new Card[52];
        byte numberOfCard = 0;
        for (String suit : suits) {
            for (int number : numbers) {
                Card drawnCard = new Card(number, suit);
                deck[numberOfCard] = drawnCard;
                numberOfCard++;
            }
        }
        return deck;
    }

    private static void shuffle(Card[] deck) {
        Random random = new Random();
        for (int i = 0; i < deck.length; i++) {
            int randomShuffle = random.nextInt(52);
            Card temp = deck[i];
            deck[i] = deck[randomShuffle];
            deck[randomShuffle] = temp;
        }
    }

    private static boolean hasBlackJack(Player player) {
        boolean hasBlackJack = false;
        if (player.getHandScore() == 11 && player.getHandAsString().contains("A")) {
            hasBlackJack = true;
        }
        return hasBlackJack;
    }

    public void preRound() {
        // Clear off result of previous round
        shuffle(this.deck);
        player.setHandScore(0);
        player.getHand().clear();
        player.setBusted(false);

        dealer.setHandScore(0);
        dealer.getHand().clear();
        dealer.setBusted(false);
        roundFinished = false;

        // Begin to bet
        System.out.println("You have: " + player.getFormattedMoney());
        System.out.print("Please enter your bet: ");
        if (this.player.getMoney() <= 0) {
            System.out.println("You don't have enough money to continue!");
        } else {
            this.bet = scanner.nextDouble();
            while (bet > player.getMoney()) {
                System.out.println("You cannot bet more than what you have");
                System.out.print("Enter your bet: ");
                bet = scanner.nextDouble();
            }
            player.setMoney(player.getMoney() - bet);
            System.out.println("You bet: " + bet);
            pot = bet * 2;
            //First round of drawing card
            for (int i = 0; i < 4; ) {
                dealer.drawCard(deck[i]);
                i++;
                player.drawCard(deck[i]);
                i++;
            }
            //System.out.println("Dealer drawn " + deck[0].getCardFullName() + " and one hidden card");
            System.out.println("Dealer drawn " + dealer.getHandAsString());
            System.out.println("You drawn " + player.getHandAsString());
            if (hasBlackJack(player)) {
                System.out.println("You have Black Jack!");
                player.setMoney(player.getMoney() + pot);
                roundFinished = true;
            } else if (hasBlackJack(dealer)) {
                System.out.println("Dealer has Black Jack!");
                dealer.setMoney(dealer.getMoney() + pot);
                roundFinished = true;
            }
        }
    }

    public void midRound() {
        //cardIndex = 4 for next card in Card[]
        int cardIndex = 4;
        // Loop, player choose to stand or hit
        while (true) {
            System.out.print("Player's turn, what would you like to do, [h]it or [s]tand:  ");
            String input = scanner.next();
            if (input.equalsIgnoreCase("h")) {
                player.drawCard(deck[cardIndex]);
                System.out.println(player.getHandAsString());
                cardIndex++;
                System.out.println("Player's Score " + player.getHandScore());
                if (player.isBusted()) {
                    System.out.println("Dealer win aaa");
                    dealer.setMoney(player.getMoney() + pot);
                    roundFinished = true;
                    break;
                }
            } else {
                System.out.println("You choose to stand");
                System.out.println(player.getHandAsString());
                break;
            }
        }// while(true)

        if (!roundFinished) {
            System.out.println("Dealer's turn");
            while (dealer.getHandScore() < 17) {
                dealer.drawCard(deck[cardIndex]);
                System.out.println("Dealer drawn:" + deck[cardIndex].getCardFullName());
                System.out.println("Dealer's hand :" + dealer.getHandAsString());
                System.out.println("Dealer's Score " + dealer.getHandScore());
                if (dealer.isBusted()) {
                    System.out.println("Player Win aaa");
                    player.setMoney(player.getMoney() + pot);
                    roundFinished = true;
                    break;
                }
                cardIndex++;
            }
        }// if(!roundFinished)

        if (!roundFinished) {
            if (player.getHandScore() > dealer.getHandScore()) {
                System.out.println("Player win");
                System.out.println("Player's score: " + player.getHandScore());
                player.setMoney(player.getMoney() + pot);
            } else if (dealer.getHandScore() == player.getHandScore()) {
                System.out.println("Draw");
                player.setMoney(player.getMoney() + (pot / 2));
                dealer.setMoney(dealer.getMoney() + (pot / 2));
            } else {
                System.out.println("Dealer win");
                System.out.println("Dealer's score: " + dealer.getHandScore());
                dealer.setMoney(dealer.getMoney() + pot);
            }
        }
    }// public void midGame
}// Class game
