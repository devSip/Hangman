import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Code {

    Scanner sc = new Scanner(System.in);
    Random ran = new Random();

    Words list = new Words();
    String[] words = list.words;

    Graphics print = new Graphics();
    String[] hangman = print.gallows;

    String word = randomWord();
    char[] placeholder;
    char guess;

    int misses = 0;
    char[] missedGuesses = new char[6];

    public void run() {
        System.out.println("Let's play hangman!\n");
        initializePlaceholders();
        while (misses < 6) {
            printHangman();
            printPlaceholders();
            handleGuess();
        }
    }

    private void handleGuess() {
        System.out.print("Guess: ");
        guess = sc.nextLine().charAt(0);
        System.out.print("\n");

        if (checkGuess(word, guess)) {
            updatePlaceholders(word, placeholder, guess);
        } else {
            missedGuesses[misses] = guess;
            misses++;
        }

        if (Arrays.equals(placeholder, word.toCharArray())) {
            System.out.print(hangman[misses]);
            System.out.println("\nGOOD WORK!");
            System.out.print("\nWord: " + word + "\n");
            System.exit(0);
        }

        if (misses == 6) {
            System.out.print(hangman[6]);
            System.out.println("\nRIP!");
            System.out.println("\nThe word was: " + word);
        }
    }

    private boolean checkGuess(String word, char guess) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                return true;
            }
        }
        return false;
    }

    private void updatePlaceholders(String word, char[] placeholder, char guess) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                placeholder[i] = guess;
            }
        }
    }

    public void printHangman() {
        System.out.println(hangman[misses]);
    }

    private String randomWord() {
        int randomIndex = ran.nextInt(words.length);
        String randomWord = words[randomIndex];
        // System.out.println("Random word is " + randomWord);
        return randomWord;
    }

    private void initializePlaceholders() {
        placeholder = new char[word.length()];
        Arrays.fill(placeholder, '_');
    }

    private void printPlaceholders() {
        System.out.print("Word: ");
        for (int i = 0; i < placeholder.length; i++) {
            System.out.print(" " + placeholder[i]);
        }
        System.out.print("\n\nMisses:   ");
        printMissedGuesses(missedGuesses);
        System.out.print("\n\n");
    }

    private void printMissedGuesses(char[] misses) {
        for (int i = 0; i < misses.length; i++) {
            System.out.print(misses[i] + " ");
        }
    }
}