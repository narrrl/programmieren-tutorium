package musterloesung.ui.commandpattern;

import musterloesung.entity.Result;
import musterloesung.game.BasicCalculator;

import java.util.regex.Matcher;

public interface ICommand {
   Result<String> execute(final Matcher matcher, final BasicCalculator calculator);
   String getPattern();
}
