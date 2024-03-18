import java.util.Scanner;

public class WarRunner {

    public static void main(String[] args) {
        // Initialize ranks, suits, and values for a standard deck of cards
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        // Create a deck and shuffle it
        Deck playerDeck = new Deck(ranks, suits, values);
        Deck computerDeck = new Deck(ranks, suits, values);

        // Start the game
        System.out.println("Welcome to the game of War!");
        System.out.println("Deck sizes: " + playerDeck.size() + " (yours) vs. " + computerDeck.size() + " (computer's)");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPress 'ENTER' to fight another battle or 'S' to shuffle your deck!");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("s")) {
                playerDeck.shuffle();
                System.out.println("Your deck has been shuffled");
                continue;
            } else if (!input.isEmpty()) {
                // If the input is not empty, continue the loop
                continue;
            }

            // Draw cards
            Card playerCard = playerDeck.deal();
            Card computerCard = computerDeck.deal();

            System.out.println("You drew a " + playerCard);
            System.out.println("The computer drew a " + computerCard);

            // Compare ranks and determine the winner
            if (playerCard.pointValue() > computerCard.pointValue()) {
                System.out.println("You won 2 cards!");
                playerDeck.addCard(playerCard);
                playerDeck.addCard(computerCard);
            } else if (playerCard.pointValue() < computerCard.pointValue()) {
                System.out.println("The computer won 2 cards!");
                computerDeck.addCard(playerCard);
                computerDeck.addCard(computerCard);
            } else {
                // War: Draw three more cards each
                System.out.println("It's a tie! Battle again!");
                for (int i = 0; i < 3; i++) {
                    playerDeck.deal();
                    computerDeck.deal();
                }
            }

            // Print updated deck sizes
            System.out.println("Deck sizes: " + playerDeck.size() + " (yours) vs. " + computerDeck.size() + " (computer's)");

            // Check for game end conditions
            if (playerDeck.isEmpty()) {
                System.out.println("You lost the game!");
                break;
            } else if (computerDeck.isEmpty()) {
                System.out.println("You won the game!!");
                break;
            }
        }

        scanner.close();
    }
}

