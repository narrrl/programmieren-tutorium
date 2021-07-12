package musterloesung.ui.commandpattern;

import musterloesung.entity.Result;
import musterloesung.game.BasicCalculator;

import java.util.regex.Matcher;

public class Sum implements ICommand {
    @Override
    public Result<String> execute(Matcher matcher, final BasicCalculator calculator) {
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

    @Override
    public String getPattern() {
        return "sum (" + BasicCalculator.NUMBER_REGEX + ") (" + BasicCalculator.NUMBER_REGEX + ")";
    }
}
