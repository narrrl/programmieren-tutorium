package musterloesung.ui.commandenum;

import musterloesung.entity.Result;
import musterloesung.game.BasicCalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {

    SUM("sum (" + BasicCalculator.NUMBER_REGEX + ") (" + BasicCalculator.NUMBER_REGEX + ")") {
        @Override
        public Result<String> execute(final Matcher matcher, final BasicCalculator calculator) {
            final int augend;
            final int addend;
            try {
                augend = Integer.parseInt(matcher.group(1));
                addend = Integer.parseInt(matcher.group(2));
            } catch (NumberFormatException e) {
                return Result.asError(e.getMessage());
            }

            final int sum = calculator.sum(augend, addend);

            return Result.asSuccess(String.valueOf(sum));
        }
    },
    EXIT("exit") {
        @Override
        public Result<String> execute(final Matcher matcher, final BasicCalculator calculator) {
            calculator.exit();
            return Result.asSuccess(EMPTY_STRING);
        }
    };

    public static final String COMMAND_NOT_FOUND = "command not found";
    public static final String SUCCESS = "OK";
    public static final String EMPTY_STRING = "";
    private final Pattern pattern;

    Command(final String regex) {
        pattern = Pattern.compile(regex);
    }

    public abstract Result<String> execute(final Matcher matcher, final BasicCalculator calculator);

    public static Result<String> executeCommand(final String input, final BasicCalculator calculator) {
        for( final Command cmd : Command.values()) {
            final Matcher matcher = cmd.pattern.matcher(input);

            if (matcher.matches()) {
                return cmd.execute(matcher, calculator);
            }
        }

        return Result.asError(COMMAND_NOT_FOUND);
    }
    
}
