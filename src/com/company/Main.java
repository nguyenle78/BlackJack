/*
Note TODO: - to simplify midGame method, divide it into playersTurn and dealerTurns method
           - include the function to let player choose ether Ace has 1 or 11 score?
           - Exception Handle
*/

package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---Welcome to BlackJack---");
        System.out.print("Enter your name: ");
        Player player = new Player(scanner.nextLine());
        Player dealer = new Player("Dealer", 10000);
        Game newGame = new Game(player, dealer);
        while (true) {
            newGame.preRound();
            if (!newGame.roundFinished) {
                newGame.midRound();
            }
            System.out.println("Continue? [y] or [n]");
            String input = scanner.next();
            if (input.equalsIgnoreCase("n")) {
                System.out.println("Game is finish, your money: " + player.getFormattedMoney());
                break;
            }
        }
    }//main method

}//Main