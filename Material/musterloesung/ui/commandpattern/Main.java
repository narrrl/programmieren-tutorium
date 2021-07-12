package musterloesung.ui.commandpattern;

import musterloesung.entity.Result;
import musterloesung.game.BasicCalculator;
import musterloesung.ui.commandenum.Command;

import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Main {

    public static final String ERROR_PREFIX = "Error, ";

    private static final Set<ICommand> commands = Set.of(
            new Sum(),
            new Exit()
    );

    private Main() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static void main(String[] args) {
        final BasicCalculator calculator = new BasicCalculator();
        final Scanner scanner = new Scanner(System.in);

        do {
            final String input = scanner.nextLine();

            final Result<String> result = executeCommand(input, calculator);

            if (result.isError()) {
                System.err.println(ERROR_PREFIX + result.getOutput());
            } else if (!result.getOutput().isEmpty()) {
                System.out.println(result.getOutput());
            }
        } while (!calculator.isExited());

    }

    public static Result<String> executeCommand(final String input, final BasicCalculator calculator) {
        for(final ICommand cmd : commands) {
            final Pattern pattern = Pattern.compile(cmd.getPattern());
            final Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                return cmd.execute(matcher, calculator);
            }
        }

        return Result.asError(Command.COMMAND_NOT_FOUND);
    }
}
