package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.Scanner;

public class Main {
    private static final int FIRST_CHAR_IDX = 0;

    public static void main(final String[] args) {
        char input;

        do {
            input = getInput();

            switch(input) {
                case 'w':
                    playWordGame();
                    break;
                case 'n':
                    playNumberGame();
                    break;
                case 'm':
                    playMyGame();
                    break;
                default:
                    System.out.println("You shouldn't be here.");
            }
        } while(input != 'q');

        System.out.println("Thank you for playing one of Marcus' studios trademark games!");
    }

    private static void playWordGame() {

    }

    private static void playNumberGame() {

    }

    private static void playMyGame() {
    }

    private static char getInput() {
        char input;
        final Scanner sc;

        sc = new Scanner(System.in);

        do {
            System.out.print("-----------------------\n" +
                    "Please choose a game to play:\n" +
                    "w/W for Word Game\n" +
                    "n/N for Number Game\n" +
                    "m/M for MyGame\n" +
                    "q/Q to Quit" +
                    "Game: ");

            input = sc.next().charAt(FIRST_CHAR_IDX);
            input = Character.toLowerCase(input);

            switch(input) {
                case 'w', 'n', 'm', 'q':
                    break;
                default:
                    System.out.println("-----------------------\n" +
                            "Invalid input.\n" +
                            "-----------------------");
                    input = ' ';
                    break;
            }
        } while(input == ' ');

        return input;
    }

}