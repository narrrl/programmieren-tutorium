package musterloesung.ui.commandenum;

import musterloesung.entity.Result;
import musterloesung.game.BasicCalculator;
import musterloesung.ui.commandpattern.ICommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the interface between the ui and the logic. <br>
 * <br>
 * Where "the logic" is our {@link BasicCalculator} <br>
 * and "the ui" is our Main function {@link Main#main(String[])} that does all the in/out stuffy <br>
 *
 * The main concept behind a shell is ... <br>
 * ... that the user can input different commands with custom arguments <br>
 * ... depending on the input a command gets executed and represents the output textual on the console <br>
 * ... the shell runs in a loop until the user exits the shell
 *
 * Each command is an entry in this enum thus being constant and exists globally at compile time.
 * This is important when we need for example multiple instances. If you're interested in a more common and modern solution
 * for parsing/executing user input then take a look at the Command-Pattern {@link ICommand}. <br>
 * <br>
 *
 * That said each "command" has an unique identifier which is a String that represents the input pattern of the command.
 * This string gets compiled by {@link Pattern#compile(String)} and stored as an attribute for each entry {@link Command#pattern}.
 *
 * To find the command the user wants to execute we just need to match all Commands to the user input and execute the command that
 * covers 100% of the user input.
 *
 * This happens in {@link Command#executeCommand(String, BasicCalculator)} and is basically a for loop that looks for the right command
 *
 * The actual code of each command gets implemented in our abstract/virtual function {@link Command#execute(Matcher, BasicCalculator)}
 * which has to be implemented by each command.
 *
 *
 * */
public enum Command {

    // this is our sum command to add two numbers together
    SUM("sum (" + BasicCalculator.NUMBER_REGEX + ") (" + BasicCalculator.NUMBER_REGEX + ")") {
        @Override
        public Result<String> execute(final Matcher matcher, final BasicCalculator calculator) {
            final int augend;
            final int addend;
            try {
                // a (...) in a regex represents a group which we can access with matcher#group(int), starting with 1
                // the user doesn't have to write 'sum (1) (6)'
                augend = Integer.parseInt(matcher.group(1));
                addend = Integer.parseInt(matcher.group(2));
            } catch (NumberFormatException e) {
                return Result.asError(e.getMessage());
            }

            final int sum = calculator.sum(augend, addend);

            return Result.asSuccess(String.valueOf(sum));
        }
    },
    // this is our exit command to exit the calculator
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
        // here gets the pattern compiled
        pattern = Pattern.compile(regex);
    }

    // this is our abstract execute function that each command overrides
    public abstract Result<String> execute(final Matcher matcher, final BasicCalculator calculator);

    public static Result<String> executeCommand(final String input, final BasicCalculator calculator) {
        // we iterate over every entry in our enum
        for( final Command cmd : Command.values()) {
            // we match the commands pattern to that of the users input
            final Matcher matcher = cmd.pattern.matcher(input);

            // if we get a total match, we can execute the command and return the result
            if (matcher.matches()) {
                return cmd.execute(matcher, calculator);
            }
        }

        // if we don't find a matching command we return an error that no command was found
        return Result.asError(COMMAND_NOT_FOUND);
    }
    
}
