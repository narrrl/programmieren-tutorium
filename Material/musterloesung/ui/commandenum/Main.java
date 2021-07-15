package musterloesung.ui.commandenum;

import musterloesung.entity.Result;
import musterloesung.game.BasicCalculator;

import java.util.Scanner;

/**
 * This class is the main entry point for our interactive {@link BasicCalculator}.
 *
 * The {@link #main(String[])} takes user input 
 * which then gets processed by {@link Command#execute(java.util.regex.Matcher, BasicCalculator)}
 * and depending on the return value {@link Result} we print out the {@link Result#getOutput()} as an error or normally
 *
 */
public final class Main {

    public static final String ERROR_PREFIX = "Error, ";

    private Main() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static void main(String[] args) {
        // we create our basic calculator
        // this instance of our calculator gets passed down
        // to our Command-enum
        final BasicCalculator calculator = new BasicCalculator();

        // we create a scanner to get our user input
        final Scanner scanner = new Scanner(System.in);

        do {
            // read user input
            final String input = scanner.nextLine();

            // try to execute user input
            final Result<String> result = Command.executeCommand(input, calculator);

            // take the result and check if it's an error
            if (result.isError()) {
                // we indicate an error with "Error, ..." in the console
                System.err.println(ERROR_PREFIX + result.getOutput());
            } else if (!result.getOutput().isEmpty()) {
                System.out.println(result.getOutput());
            }
        } while(!calculator.isExited()); // exit the do-while loop if the calculator exited



    }
}
