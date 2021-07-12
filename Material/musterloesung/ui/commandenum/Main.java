package musterloesung.ui.commandenum;

import musterloesung.entity.Result;
import musterloesung.game.BasicCalculator;

import java.util.Scanner;

public final class Main {

    public static final String ERROR_PREFIX = "Error, ";

    private Main() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static void main(String[] args) {
        final BasicCalculator calculator = new BasicCalculator();
        final Scanner scanner = new Scanner(System.in);

        do {
            final String input = scanner.nextLine();

            final Result<String> result = Command.executeCommand(input, calculator);

            if (result.isError()) {
                System.err.println(ERROR_PREFIX + result.getOutput());
            } else if (!result.getOutput().isEmpty()) {
                System.out.println(result.getOutput());
            }
        } while(!calculator.isExited());



    }
}
