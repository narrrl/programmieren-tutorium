package musterloesung.ui.commandpattern;

import musterloesung.entity.Result;
import musterloesung.game.BasicCalculator;
import musterloesung.ui.commandenum.Command;

import java.util.regex.Matcher;

public class Exit implements ICommand {
    @Override
    public Result<String> execute(Matcher matcher, BasicCalculator calculator) {
        calculator.exit();
        return Result.asSuccess(Command.EMPTY_STRING);
    }

    @Override
    public String getPattern() {
        return "exit";
    }
}
